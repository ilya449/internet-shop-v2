package com.internet.shop.db;

import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Storage {
    private static Long orderId = 0L;
    private static Long productId = 0L;
    private static Long shoppingCartId = 0L;
    private static Long userId = 0L;
    @Getter
    private static final List<Order> orders = new ArrayList<>();
    @Getter
    private static final List<Product> products = new ArrayList<>();
    @Getter
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    @Getter
    private static final List<User> users = new ArrayList<>();

    public static void addOrder(Order order) {
        order.setId(++orderId);
        orders.add(order);
    }

    public static void addProduct(Product product) {
        product.setId(++productId);
        products.add(product);
    }

    public static void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(++shoppingCartId);
        shoppingCarts.add(shoppingCart);
    }

    public static void addUser(User user) {
        user.setId(++userId);
        users.add(user);
    }
}
