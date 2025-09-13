package com.planittesting.cloud.jupiter.model;

import java.math.BigDecimal;

public class CartItem {

    Integer quantity;
    Product product;
    BigDecimal subtotal;

    public CartItem(Integer quantity, Product product, BigDecimal subtotal) {
        this.quantity = quantity;
        this.product = product;
        this.subtotal = subtotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public Product getProduct() {
        return product;
    }
}
