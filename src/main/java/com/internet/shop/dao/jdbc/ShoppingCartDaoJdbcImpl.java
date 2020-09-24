package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.exception.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
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
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        String query = "SELECT * FROM shopping_carts WHERE user_id = ?;";
        return getShoppingCart(userId, query);
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_carts(user_id) VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create shopping cart: " + shoppingCart, e);
        }
        addProducts(shoppingCart.getId(), shoppingCart.getProducts());
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        String query = "SELECT * FROM shopping_carts WHERE cart_id = ?;";
        return getShoppingCart(id, query);
    }

    @Override
    public List<ShoppingCart> getAll() {
        String query = "SELECT * FROM shopping_carts WHERE deleted = FALSE";
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shoppingCartList.add(extractShoppingCart(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract users from DB", e);
        }
        for (ShoppingCart shoppingCart : shoppingCartList) {
            shoppingCart.setProducts(getProducts(shoppingCart.getId()));
        }
        return shoppingCartList;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        String query = "DELETE FROM shopping_carts_products WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCart.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete old products from shopping cart"
                    + " with ID: " + shoppingCart, e);
        }
        addProducts(shoppingCart.getId(), shoppingCart.getProducts());
        return shoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE shopping_carts SET deleted = TRUE WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete user with id: " + id, e);
        }
    }

    private Optional<ShoppingCart> getShoppingCart(Long id, String query) {
        ShoppingCart shoppingCart = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                shoppingCart = extractShoppingCart(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get shopping cart from DB by query: "
                    + query + " and id: " + id, e);
        }
        return getShoppingCartWithProducts(shoppingCart);
    }

    private void addProducts(Long cartId, List<Product> products) {
        String query = "INSERT INTO shopping_carts_products (cart_id, product_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            for (Product product : products) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to shopping cart with ID: "
                    + cartId, e);
        }
    }

    private ShoppingCart extractShoppingCart(ResultSet resultSet) throws SQLException {
        return new ShoppingCart(resultSet.getLong("cart_id"),
                resultSet.getLong("user_id"), new ArrayList<>());
    }

    private Optional<ShoppingCart> getShoppingCartWithProducts(ShoppingCart cart) {
        if (cart == null) {
            return Optional.empty();
        }
        cart.setProducts(getProducts(cart.getId()));
        return Optional.of(cart);
    }

    private List<Product> getProducts(Long cartId) {
        String query = "SELECT p.product_id, name, price FROM products p JOIN "
                + "shopping_carts_products cp ON p.product_id = cp.product_id "
                + "where cp.cart_id = ?";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("product_id"),
                        resultSet.getString("name"), resultSet.getDouble("price"));
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract products for shopping cart with id:"
                    + " " + cartId, e);
        }
    }
}
