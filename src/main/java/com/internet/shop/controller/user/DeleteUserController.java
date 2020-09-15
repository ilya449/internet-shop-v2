package com.internet.shop.controller.user;

import com.internet.shop.lib.Injector;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/delete")
public class DeleteUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("id"));
        shoppingCartService.delete(shoppingCartService.getByUserId(userId));
        userService.delete(userId);
        HttpSession session = req.getSession();
        if (userId.equals(session.getAttribute(LoginController.USER_ID))) {
            session.setAttribute(LoginController.USER_ID, null);
            resp.sendRedirect(req.getContextPath() + "/user/registration");
        }
        resp.sendRedirect(req.getContextPath() + "/user/all");
    }
}
