package com.internet.shop.model;

import java.util.List;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ShoppingCart {
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private List<Product> products;
}
