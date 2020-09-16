package com.internet.shop.web.filter;

import com.internet.shop.controller.user.LoginController;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService = (UserService) injector
            .getInstance(UserService.class);
    private Map<String, List<Role.RoleName>> protectedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls = new HashMap<>();
        protectedUrls.put("/user/all", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/user/delete", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/products", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/main", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/orders", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/product/add", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/product/delete", List.of(Role.RoleName.ADMIN));
        protectedUrls.put("/order/delete", List.of(Role.RoleName.ADMIN));

        protectedUrls.put("/user/orders", List.of(Role.RoleName.USER));
        protectedUrls.put("/product/buy", List.of(Role.RoleName.USER));
        protectedUrls.put("/order/create", List.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/products", List.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/delete", List.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestUrl = req.getServletPath();

        if (protectedUrls.get(requestUrl) == null) {
            chain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login");
            return;
        }

        User user = userService.get(userId);

        if (isAuthorized(user, protectedUrls.get(requestUrl))) {
            chain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/view/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, List<Role.RoleName> authorizedRoles) {
        return authorizedRoles.stream()
                .anyMatch(r -> user.getRoles().stream()
                        .map(Role::getRoleName)
                        .anyMatch(userRole -> userRole.equals(r)));
    }
}
