package com.internet.shop.security;

import com.internet.shop.exception.AuthenticationException;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;
import com.internet.shop.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> user = userService.findByLogin(login);
        if (user.isPresent() && isPassValid(password, user.get())) {
            return user.get();
        }
        throw new AuthenticationException("Incorrect user login or password");
    }

    private boolean isPassValid(String password, User user) {
        return HashUtil.hashPassword(password, user.getSalt())
                .equals(user.getPassword());
    }
}
