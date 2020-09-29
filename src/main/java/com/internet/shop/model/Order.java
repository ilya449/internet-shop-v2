package com.internet.shop.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private List<Product> products;
}
