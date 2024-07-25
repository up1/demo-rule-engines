package com.example.rules.promotions;

public class Product {
    private int id;
    private String name;
    private String category;
    private String brand;

    public Product(int id, String name, String category, String brand) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
