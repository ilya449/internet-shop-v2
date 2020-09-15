package com.internet.shop.controller.order;

import com.internet.shop.controller.user.LoginController;
import com.internet.shop.lib.Injector;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/orders")
public class ShowCurrentUserOrdersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);
    private UserService userService = (UserService) injector
            .getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        req.setAttribute("orders", orderService.getUserOrders(userId));
        req.setAttribute("user", userService.get(userId).getLogin());
        req.getRequestDispatcher("/WEB-INF/view/user/orders.jsp").forward(req, resp);
    }
}
