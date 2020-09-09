package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/injectData")
public class InjectDataController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User bob = new User("Bob", "bob123", "passBob");
        User alice = new User("Alice", "alice123", "passAlice");
        User userDave = new User("Dave", "dave3459", "passDave");
        User userCharlie = new User("Charlie", "charlie777", "passCharlie");
        userService.create(bob);
        userService.create(alice);
        userService.create(userDave);
        userService.create(userCharlie);

        Product phantom = new Product("DJI Phantom 4 PRO", 49_470d);
        Product mavic = new Product("DJI Mavic 2 PRO", 50_720d);
        Product mavicPlatinum = new Product("DJI Mavic PRO Platinum", 42_710d);
        Product inspire = new Product("DJI Inspire 2", 142_760d);
        productService.create(phantom);
        productService.create(mavic);
        productService.create(mavicPlatinum);
        productService.create(inspire);

        shoppingCartService.create(new ShoppingCart(USER_ID, new ArrayList<>()));

        req.getRequestDispatcher("/WEB-INF/view/injectData.jsp").forward(req, resp);
    }
}
