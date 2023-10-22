package com.lab1java.model;

import com.lab1java.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    public enum userType {
        Admin,
        Manager,
        Customer
    }
    private userType type;
    private String username;
    private String email;
    private String password;
    private String phone;

    public userType getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public User(userType type, String username, String email, String password, String phone) {
        this.type = type;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }


}
