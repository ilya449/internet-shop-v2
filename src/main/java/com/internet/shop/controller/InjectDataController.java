package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

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

        req.getRequestDispatcher("/WEB-INF/view/injectData.jsp").forward(req, resp);
    }
}
