package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product/buy")
public class BuyProductController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);
    private ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        Product product = productService.get(productId);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID);
        shoppingCartService.addProduct(shoppingCart, product);

        req.setAttribute("message", String
                .format("Product %s was added to the shopping cart!", product.getName()));
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/view/product/all.jsp")
                .forward(req, resp);
    }
}
