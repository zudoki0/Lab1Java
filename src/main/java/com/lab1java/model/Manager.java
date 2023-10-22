package com.lab1java.model;

import com.lab1java.dao.OrderManagerDAO;
import com.lab1java.dao.ProductDAO;

public class Manager extends User {
    public Manager(userType type, String username, String email, String password, String phone) {
        super(type, username, email, password, phone);
    }
}
