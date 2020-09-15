package com.internet.shop.controller.cart;

import com.internet.shop.controller.user.LoginController;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/shopping-cart/products")
public class ShoppingCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userIdLong = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userIdLong);
        req.setAttribute("products", shoppingCart.getProducts());
        req.getRequestDispatcher("/WEB-INF/view/shoppingCart/products.jsp")
                .forward(req, resp);
    }
}
