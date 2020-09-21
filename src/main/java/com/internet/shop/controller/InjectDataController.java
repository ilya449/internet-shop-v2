package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/data-inject")
public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User bob = new User("Bob", "bob123", "passBob", Set.of(Role.of("USER")));
        User admin = new User("admin", "admin123", "admin123", Set.of(Role.of("ADMIN")));
        User alice = new User("Alice", "alice123", "passAlice", Set.of(Role.of("USER")));
        User userDave = new User("Dave", "dave3459", "passDave", Set.of(Role.of("USER")));
        User userCharlie = new User("Charlie", "charlie777", "passCharlie",
                Set.of(Role.of("USER")));
        userService.create(bob);
        userService.create(admin);
        userService.create(alice);
        userService.create(userDave);
        userService.create(userCharlie);

        shoppingCartService.create(new ShoppingCart(bob.getId(), new ArrayList<>()));
        shoppingCartService.create(new ShoppingCart(alice.getId(), new ArrayList<>()));
        shoppingCartService.create(new ShoppingCart(userDave.getId(), new ArrayList<>()));
        shoppingCartService.create(new ShoppingCart(userCharlie.getId(), new ArrayList<>()));
        req.getRequestDispatcher("/WEB-INF/view/data.jsp").forward(req, resp);
    }
}
