package com.lab1java.model;

public class Product {
    private String type;
    private String name;
    private String description;
    private float price;
    private int amount;

    public Product(String type, String name, String description, float price) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product(String type, String name, String description, float price, int amount) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
