package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector
                .getInstance(ProductService.class);

        Product phantom = new Product("DJI Phantom 4 PRO", 49_470d);
        Product mavicPlatinum = new Product("DJI Mavic PRO Platinum", 42_710d);
        Product mavic = new Product("DJI Mavic 2 PRO", 50_720d);
        Product inspire = new Product("DJI Inspire 2", 142_760d);

        productService.create(phantom);
        productService.create(mavicPlatinum);
        productService.create(mavic);
        productService.create(inspire);

        System.out.println("All products:");
        productService.getAll()
                .forEach(System.out::println);

        System.out.println("Get product by id 3: " + productService.get(3L).toString());

        mavicPlatinum.setPrice(39_999d);
        System.out.println("Updated product: "
                + productService.update(mavicPlatinum).toString());

        System.out.println("Delete product by id 1");
        productService.delete(1L);

        productService.getAll()
                .forEach(System.out::println);
    }
}
