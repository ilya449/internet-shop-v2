package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.exception.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public List<Order> getUserOrders(Long userId) {
        String query = "SELECT * FROM orders WHERE deleted = FALSE AND user_id = ?;";
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderList.add(extractOrder(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract orders from DB for user with ID: "
                    + userId, e);
        }
        for (Order order : orderList) {
            order.setProducts(getProducts(order.getId()));
        }
        return orderList;
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create order: " + order, e);
        }
        addProducts(order.getId(), order.getProducts());
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders WHERE deleted = FALSE AND order_id = ?;";
        Order order = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = extractOrder(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract orders from DB by ID: "
                    + id, e);
        }
        if (order == null) {
            return Optional.empty();
        }
        order.setProducts(getProducts(order.getId()));
        return Optional.of(order);
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders WHERE deleted = FALSE";
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = extractOrder(resultSet);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract users from DB", e);
        }
        for (Order order : orderList) {
            order.setProducts(getProducts(order.getId()));
        }
        return orderList;
    }

    @Override
    public Order update(Order order) {
        String query = "DELETE FROM orders_products WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order: " + order, e);
        }
        addProducts(order.getId(), order.getProducts());
        return order;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE orders SET deleted = TRUE WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order with id: " + id, e);
        }
    }

    private void addProducts(Long orderId, List<Product> products) {
        String query = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            for (Product product : products) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to the order with ID: "
                    + orderId, e);
        }
    }

    private Order extractOrder(ResultSet rs) throws SQLException {
        return new Order(rs.getLong("order_id"),
                rs.getLong("user_id"), new ArrayList<>());
    }

    private List<Product> getProducts(Long orderId) {
        String query = "SELECT product_id, name, price FROM products p JOIN "
                + "orders_products op ON p.product_id = op.product_id "
                + "where op.order_id = ?";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("product_id"),
                        resultSet.getString("name"), resultSet.getDouble("price"));
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract products for order with id:"
                    + " " + orderId, e);
        }
    }
}
