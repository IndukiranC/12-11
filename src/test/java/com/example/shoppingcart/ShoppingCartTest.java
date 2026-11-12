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
    public void testDiscountCalculation() {
        ShoppingCart sc = new ShoppingCart();
        sc.addProduct("Monitor", 1, 500.0);
        sc.applyCoupon("SAVE10");

        assertEquals(400.0, sc.calculateTotal(), 0.001);
    }
}