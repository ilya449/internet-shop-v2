package com.internet.shop.dao.service;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.db.Storage;
import com.internet.shop.model.Product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductDaoService implements ProductDao {

    public static final String EXCEPTION_MESSAGE = "Storage doesn't have product with id: %s";

    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.getProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.getProducts();
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getProducts().remove(get(id).orElseThrow(() ->
                new NoSuchElementException(String.format(EXCEPTION_MESSAGE,id.toString()))));
    }
}
