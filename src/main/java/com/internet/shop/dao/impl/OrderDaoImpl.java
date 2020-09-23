package com.internet.shop.dao.impl;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.db.Storage;
import com.internet.shop.model.Order;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.getOrders().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.getOrders();
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.getOrders().stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.getOrders().size())
                .filter(i -> Storage.getOrders().get(i).getId().equals(order.getId()))
                .forEach(i -> Storage.getOrders().set(i, order));
        return order;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getOrders()
                .removeIf(o -> o.getId().equals(id));
    }
}
