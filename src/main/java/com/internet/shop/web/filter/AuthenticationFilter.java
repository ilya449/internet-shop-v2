package com.internet.shop.web.filter;

import com.internet.shop.controller.user.LoginController;
import com.internet.shop.lib.Injector;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService = (UserService) injector
            .getInstance(UserService.class);
    private final Set<String> availableUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        availableUrls.add("/user/login");
        availableUrls.add("/user/registration");
        availableUrls.add("/");
        availableUrls.add("/product/all");
        availableUrls.add("/data-inject");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getServletPath();

        if (availableUrls.contains(url)) {
            chain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login");
            return;
        }
        try {
            userService.get(userId);
        } catch (NoSuchElementException e) {
            resp.sendRedirect(req.getContextPath() + "/user/registration");
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
