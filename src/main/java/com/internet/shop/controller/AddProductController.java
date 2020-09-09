package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product/addition")
public class AddProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/product/addition.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        if (name.length() > 0 && price.length() > 0) {
            Product newProduct = new Product(name, Double.parseDouble(price));
            productService.create(newProduct);
            req.setAttribute("message", String.format("Product %s was added!",
                    newProduct.getName()));
        } else {
            req.setAttribute("invalidDataMessage", "Fill all fields correctly!");
        }
        req.getRequestDispatcher("/WEB-INF/view/product/addition.jsp")
                .forward(req, resp);
    }
}
