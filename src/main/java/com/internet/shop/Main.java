package com.internet.shop;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.dao.jdbc.ProductDaoJdbcImpl;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ProductDao productDaoJdbc = new ProductDaoJdbcImpl();

        Product phantom = new Product("DJI Phantom 4 PRO", 49_470d);
        Product mavic = new Product("DJI Mavic 2 PRO", 50_720d);
        Product mavicPlatinum = new Product("DJI Mavic PRO Platinum", 42_710d);
        Product inspire = new Product("DJI Inspire 2", 142_760d);
        productDaoJdbc.create(phantom);
        productDaoJdbc.create(mavic);
        productDaoJdbc.create(mavicPlatinum);
        productDaoJdbc.create(inspire);

        System.out.println("All created products:");
        productDaoJdbc.getAll().forEach(System.out::println);

        System.out.println("Product with id 2:");
        System.out.println(productDaoJdbc.get(2L));

        System.out.println("Update product with id 3, set price 39 999.00");
        System.out.println("Before updating:");
        System.out.println(productDaoJdbc.get(3L));
        mavicPlatinum.setPrice(39_999.00);
        productDaoJdbc.update(mavicPlatinum);
        System.out.println("After updating:");
        System.out.println(productDaoJdbc.get(3L));

        System.out.println("Deleted product by id 1. All products after deleting:");
        productDaoJdbc.delete(1L);
        productDaoJdbc.getAll().forEach(System.out::println);

        productDaoJdbc.delete(2L);
        productDaoJdbc.delete(3L);
        productDaoJdbc.delete(4L);

        System.out.println("Products in DB after deleting other products: "
                + productDaoJdbc.getAll().size());

        try (Connection connection = ConnectionUtil.getConnection()) {
            connection.prepareStatement("TRUNCATE products;").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
