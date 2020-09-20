package com.internet.shop.dao.impl;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.db.Storage;
import com.internet.shop.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ProductDaoImpl implements ProductDao {
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
        IntStream.range(0, Storage.getProducts().size())
                .filter(i -> Storage.getProducts().get(i).getId().equals(product.getId()))
                .forEach(i -> Storage.getProducts().set(i, product));
        return product;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getProducts()
                .removeIf(p -> p.getId().equals(id));
    }
}
