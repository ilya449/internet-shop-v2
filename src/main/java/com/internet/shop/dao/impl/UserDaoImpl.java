package com.internet.shop.dao.impl;

import com.internet.shop.dao.UserDao;
import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.getUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.getUsers();
    }

    @Override
    public User update(User user) {
        IntStream.range(0, Storage.getUsers().size())
                .filter(i -> Storage.getUsers().get(i).getId().equals(user.getId()))
                .forEach(i -> Storage.getUsers().set(i, user));
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getUsers()
                .removeIf(u -> u.getId().equals(id));
    }
}
