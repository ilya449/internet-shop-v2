package com.internet.shop.model;

import lombok.Data;
import java.util.List;

@Data
public class ShoppingCart {
    private Long id;
    private Long userId;
    private List<Product> products;
}
