package com.internet.shop.service.impl;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.ShoppingCartService;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        if (shoppingCart.getProducts().stream()
                .noneMatch(p -> p.getId().equals(product.getId()))) {
            return false;
        }
        IntStream.range(0, shoppingCart.getProducts().size()-1)
                .filter(i -> shoppingCart.getProducts().get(i).getId()
                        .equals(product.getId()))
                .forEach(i -> shoppingCart.getProducts().remove(i));
        shoppingCartDao.update(shoppingCart);
        return true;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getAll().stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String
                        .format("There is no shopping carts for user with id:%d", userId)));
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return shoppingCartDao.delete(shoppingCart.getId());
    }
}
