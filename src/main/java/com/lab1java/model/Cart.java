package com.lab1java.model;

import java.util.List;

public class Cart {
    private List<Integer> productIdList;

    public List<Integer> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Integer> productIdList) {
        this.productIdList = productIdList;
    }

    public void addProduct(int id) {
        productIdList.add(id);
    }

    public void deleteProduct(int id) {
        productIdList.remove(productIdList.indexOf(id));
    }
}
