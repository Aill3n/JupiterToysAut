package com.planittesting.cloud.jupiter.model;


import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cart {

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    List<CartItem> cartItems;
    BigDecimal total;

    public Cart(List<CartItem> cartItems, BigDecimal total) {
        this.cartItems = cartItems;
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<CartItem> filterItems(Predicate<CartItem> filter) {
        return cartItems.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }
}