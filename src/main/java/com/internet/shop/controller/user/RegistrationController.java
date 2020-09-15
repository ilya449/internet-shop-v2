package com.internet.shop.controller.user;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/registration")
public class RegistrationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/user/registration.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String repeatedPass = req.getParameter("repeatedPass");
        if (name.length() > 1 && login.length() > 3
                && pass.length() > 5 && pass.equals(repeatedPass)) {
            User newUser = new User(name, login, pass);
            userService.create(newUser);
            Long newUserIdLong = newUser.getId();
            shoppingCartService.create(new ShoppingCart(newUserIdLong, new ArrayList<>()));
            req.getSession().setAttribute(LoginController.USER_ID, newUserIdLong);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Fill all fields and make sure your passwords are same!");
            req.getRequestDispatcher("/WEB-INF/view/user/registration.jsp")
                    .forward(req, resp);
        }
    }
}
