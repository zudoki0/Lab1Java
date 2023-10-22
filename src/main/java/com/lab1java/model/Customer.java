package com.lab1java.model;

import com.lab1java.dao.UserDAO;

public class Customer extends User {
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer(userType type, String username, String email, String password, String phone) {
        super(type, username, email, password, phone);
        cart = new Cart();
    }

    public void submitOrder() {
        Order order = new Order(this.getUsername(), cart.getProductIdList());
        order.submit();
    }
}
