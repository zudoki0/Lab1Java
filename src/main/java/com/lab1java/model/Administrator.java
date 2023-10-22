package com.lab1java.model;

import com.lab1java.dao.ProductDAO;
import com.lab1java.dao.UserDAO;
import com.lab1java.dao.WarehouseDAO;

public class Administrator extends User {
    public Administrator(userType type, String username, String email, String password, String phone) {
        super(type, username, email, password, phone);
    }

}
