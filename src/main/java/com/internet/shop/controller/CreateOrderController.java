package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order/create")
public class CreateOrderController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);
    private OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        if (shoppingCart.getProducts().size() > 0) {
            orderService.completeOrder(shoppingCart);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
