package com.example.rules.promotions;

import java.util.ArrayList;
import java.util.List;

public class Order {

    List<Product> products = new ArrayList<>();
    private Double discount = 0d;

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
