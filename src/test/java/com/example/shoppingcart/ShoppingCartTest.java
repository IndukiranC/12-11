package com.example.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {

    @Test
    public void testAddAndRemoveProducts() {
        ShoppingCart sc = new ShoppingCart();
        sc.addProduct("Book", 2, 15.0);
        sc.addProduct("Pen", 3, 5.0);
        assertEquals(2, sc.getItemCount());

        sc.removeProduct("Pen");
        assertEquals(1, sc.getItemCount());
    }

    @Test
    public void testTotalCalculation() {
        ShoppingCart sc = new ShoppingCart();
        sc.addProduct("Keyboard", 1, 100.0);
        sc.addProduct("Mouse", 2, 50.0);
        assertEquals(200.0, sc.calculateTotal(), 0.001);
    }

    @Test
    public void testDiscountsAndCoupons() {
        ShoppingCart sc = new ShoppingCart();
        sc.addProduct("Monitor", 1, 500.0);

        sc.applyPercentageDiscount(10.0);
        assertEquals(450.0, sc.calculateTotal(), 0.001);

        sc.applyCoupon("SAVE20");
        assertEquals(400.0, sc.calculateTotal(), 0.001);
    }

    @Test
    public void testInvalidInputs() {
        // Fails only when FAIL_ONE_TEST=true is passed in the CI job
        if ("true".equals(System.getenv("FAIL_ONE_TEST"))) {
            fail("Intentional failure: 3 tests passed, 1 test failed");
        }

        ShoppingCart sc = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> sc.addProduct("Laptop", 0, 500.0));
        assertThrows(IllegalArgumentException.class, () -> sc.addProduct("Laptop", 1, -50.0));
        assertThrows(IllegalArgumentException.class, () -> sc.applyPercentageDiscount(150.0));
        assertThrows(IllegalArgumentException.class, () -> sc.applyCoupon("WRONG_CODE"));
    }
}