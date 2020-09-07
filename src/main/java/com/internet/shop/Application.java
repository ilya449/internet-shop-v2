package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.util.ArrayList;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector
                .getInstance(ProductService.class);
        testProductService(productService);

        UserService userService = (UserService) injector
                .getInstance(UserService.class);
        testUserService(userService);

        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        testShoppingCartService(shoppingCartService, productService);

        OrderService orderService = (OrderService) injector
                .getInstance(OrderService.class);
        testOrderService(orderService, productService, shoppingCartService);
    }

    private static void testOrderService(OrderService orderService,
                                         ProductService productService,
                                         ShoppingCartService shoppingCartService) {
        System.out.println("Testing OrderService============================================");
        ShoppingCart aliceShoppingCart = new ShoppingCart(1L,new ArrayList<>());
        ShoppingCart bobShoppingCart = new ShoppingCart(2L,new ArrayList<>());
        Product iPhoneX = new Product("Iphone X",14_999D);
        Product gblCharge3 = new Product("GBL Charge 3", 3_799D);
        productService.create(iPhoneX);
        productService.create(gblCharge3);
        shoppingCartService.addProduct(aliceShoppingCart, productService.get(5L));
        shoppingCartService.addProduct(aliceShoppingCart, productService.get(6L));
        shoppingCartService.addProduct(bobShoppingCart, productService.get(5L));

        System.out.println("Complete order for Alice's and Bob's shopping carts:");
        System.out.println(orderService.completeOrder(aliceShoppingCart));
        System.out.println(orderService.completeOrder(bobShoppingCart));

        System.out.println("Get Alice's orders by ID:");
        orderService.getUserOrders(aliceShoppingCart.getUserId())
                .forEach(System.out::println);

        System.out.println("Get order by ID 2:");
        System.out.println(orderService.get(2L));

        System.out.println("Get All orders:");
        orderService.getAll().forEach(System.out::println);

        System.out.println("Orders after deleting by ID 1:");
        orderService.delete(1L);
        orderService.getAll().forEach(System.out::println);
    }

    private static void testShoppingCartService(ShoppingCartService shoppingCartService,
                                                ProductService productService) {
        System.out.println("Testing ShoppingCartService===================================");
        ShoppingCart aliceShoppingCart = new ShoppingCart(1L,new ArrayList<>());
        ShoppingCart bobShoppingCart = new ShoppingCart(2L,new ArrayList<>());
        shoppingCartService.create(aliceShoppingCart);
        shoppingCartService.create(bobShoppingCart);
        Product iPhoneX = new Product("Iphone X",14_999D);
        Product gblCharge3 = new Product("GBL Charge 3", 3_799D);
        productService.create(iPhoneX);
        productService.create(gblCharge3);

        shoppingCartService.addProduct(aliceShoppingCart,
                productService.get(iPhoneX.getId()));
        shoppingCartService.addProduct(aliceShoppingCart,
                productService.get(gblCharge3.getId()));

        System.out.println("Shopping cart by user(Alice) id 1:");
        shoppingCartService.getByUserId(aliceShoppingCart.getUserId()).getProducts()
                .forEach(System.out::println);

        System.out.println("Shopping cart after deleting iPhone X:");
        shoppingCartService.deleteProduct(aliceShoppingCart, iPhoneX);
        shoppingCartService.getByUserId(aliceShoppingCart.getUserId()).getProducts()
                .forEach(System.out::println);

        System.out.println("Shopping cart before clearing:");
        shoppingCartService.getByUserId(aliceShoppingCart.getUserId()).getProducts()
                .forEach(System.out::println);
        System.out.println("Shopping cart after clearing:");
        shoppingCartService.clear(aliceShoppingCart);
        if (shoppingCartService.getByUserId(aliceShoppingCart.getUserId())
                .getProducts().isEmpty()) {
            System.out.println("Shopping car was successfully clear!");
        }

        System.out.println("Deleting Alice's shopping cart: "
                + shoppingCartService.delete(aliceShoppingCart));
    }

    private static void testUserService(UserService userService) {
        System.out.println("Testing UserService============================================");
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
        System.out.println("Testing ProductService==========================================");
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
