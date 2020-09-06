package com.internet.shop.service.impl;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.Order;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.OrderService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    OrderDao orderDao;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order(shoppingCart.getUserId(), shoppingCart.getProducts());
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getAll().stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).orElseThrow(() ->
                new NoSuchElementException(String.format("There is no order with id:%d", id)));
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
