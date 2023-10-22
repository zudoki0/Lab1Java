package com.lab1java.model;

import java.util.List;

public class Warehouse {
    private String name;
    private String address;
    private float capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public Warehouse(String name, String address, float capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }
}
