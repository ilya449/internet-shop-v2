package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.User;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.UserService;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector
                .getInstance(ProductService.class);
        testProductService(productService);

        UserService userService = (UserService) injector
                .getInstance(UserService.class);
        testUserService(userService);
    }

    private static void testUserService(UserService userService){
        User userAlice = new User("Alice", "alice_328212", "AlicePass2211");
        User userBob = new User("Bob", "bob123", "BobPass111");
        User userDave = new User("Dave", "dave3459", "pass12321pass");
        User userCharlie = new User("Charlie", "1charlie1", "charlie*Pass22");

        userService.create(userAlice);
        userService.create(userBob);
        userService.create(userDave);
        userService.create(userCharlie);

        System.out.println("All created users:");
        userService.getAll().forEach(System.out::println);

        System.out.println("User by id 2:");
        System.out.println(userService.get(2L));

        System.out.println("Delete user by ID 3. All users after deleting:");
        userService.delete(3L);
        userService.getAll().forEach(System.out::println);

        System.out.println("Update user by ID 1:");
        System.out.println("Before updating:");
        System.out.println(userService.get(1L));
        User userLizzy = new User("Lizzy", "liz_328212*Y", "LizzyTheBest");
        userLizzy.setId(1L);
        userService.update(userLizzy);
        System.out.println("After updating:");
        System.out.println(userService.get(1L));
    }

    private static void testProductService(ProductService productService) {
        Product phantom = new Product("DJI Phantom 4 PRO", 49_470d);
        Product mavicPlatinum = new Product("DJI Mavic PRO Platinum", 42_710d);
        Product mavicPlatinum2 = new Product("DJI Mavic PRO Platinum2", 46_600d);
        Product mavic = new Product("DJI Mavic 2 PRO", 50_720d);
        Product inspire = new Product("DJI Inspire 2", 142_760d);

        productService.create(phantom);
        productService.create(mavicPlatinum);
        productService.create(mavicPlatinum2);
        productService.create(mavic);
        productService.create(inspire);

        System.out.println("All products:");
        productService.getAll()
                .forEach(System.out::println);

        System.out.println("Get product by id 3: " + productService.get(3L).toString());

        System.out.println("Products before update:\n" + productService.get(2L)
                + "\n" + productService.get(3L));
        mavicPlatinum.setPrice(39_999d);
        mavicPlatinum2 = new Product("DJI Mavic PRO Platinum2", 42_999d);
        mavicPlatinum2.setId(3L);
        productService.update(mavicPlatinum);
        productService.update(mavicPlatinum2);
        System.out.println("Products after update:\n" + productService.get(2L)
                + "\n" + productService.get(3L));

        System.out.println("Delete product by id 1");
        productService.delete(1L);

        productService.getAll()
                .forEach(System.out::println);
    }
}