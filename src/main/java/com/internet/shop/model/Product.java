package com.internet.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Double price;
}
