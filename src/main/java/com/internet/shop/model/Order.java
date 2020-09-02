package com.internet.shop.model;

import lombok.Data;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Long userId;
    private List<Product> products;
}
