package com.internet.shop.security;

import com.internet.shop.exception.AuthenticationException;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String pass) throws AuthenticationException {
        User user = userService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect user login or password"));

        if (user.getPassword().equals(pass)) {
            return user;
        }
        throw new AuthenticationException("Incorrect user login or password");
    }
}
