package com.example.shoppingcart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    static class Product {
        String name;
        int quantity;
        double price;

        Product(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }
    }

    private Map<String, Product> cart = new HashMap<>();
    private double discountPercent = 0.0;

    
    public void addProduct(String name, int quantity, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (quantity <= 0 || price <= 0) {
            throw new IllegalArgumentException("Quantity and price must be greater than 0");
        }
        String key = name.toLowerCase();
        if (cart.containsKey(key)) {
            cart.get(key).quantity += quantity;
        } else {
            cart.put(key, new Product(name, quantity, price));
        }
    }


    public void removeProduct(String name) {
        if (name == null || !cart.containsKey(name.toLowerCase())) {
            throw new IllegalArgumentException("Product not found in cart");
        }
        cart.remove(name.toLowerCase());
    }

   
    public void applyPercentageDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        this.discountPercent = percent;
    }


    public void applyCoupon(String coupon) {
        if ("SAVE10".equalsIgnoreCase(coupon)) {
            this.discountPercent = 10.0;
        } else if ("SAVE20".equalsIgnoreCase(coupon)) {
            this.discountPercent = 20.0;
        } else {
            throw new IllegalArgumentException("Invalid coupon code");
        }
    }

   
    public double calculateTotal() {
        double total = 0.0;
        for (Product p : cart.values()) {
            total += p.quantity * p.price;
        }
        return total - (total * discountPercent / 100.0);
    }

    public int getItemCount() {
        return cart.size();
    }

    public static void main(String[] args) {
        ShoppingCart sc = new ShoppingCart();

        sc.addProduct("Laptop", 1, 1000.0);
        sc.addProduct("Mouse", 2, 25.0);
        System.out.println("Total before discount: " + sc.calculateTotal());

        sc.removeProduct("Mouse");
        sc.applyCoupon("SAVE10");
        System.out.println("Total after removing Mouse & applying SAVE10: " + sc.calculateTotal());
    }
}