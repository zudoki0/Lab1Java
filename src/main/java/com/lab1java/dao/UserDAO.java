package com.lab1java.dao;

import com.lab1java.Database;
import com.lab1java.model.Administrator;
import com.lab1java.model.Customer;
import com.lab1java.model.Manager;
import com.lab1java.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void createUser(User user) {
        String query = "INSERT INTO users(type,username,email,password,phone) VALUES(?,?,?,?,?)";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, user.getType().toString().toLowerCase());
            stmt.setString(2,user.getUsername());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPassword());
            stmt.setString(5,user.getPhone());
            int rs = stmt.executeUpdate();
            System.out.println("Added manager: " + user.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                switch(type) {
                    case "admin":
                        User admin = new Administrator(User.userType.Admin, username, email, password, phone);
                        users.add(admin);
                        break;
                    case "manager":
                        User manager = new Manager(User.userType.Manager, username, email, password, phone);
                        users.add(manager);
                        break;
                    case "customer":
                        User customer = new Customer(User.userType.Customer, username, email, password, phone);
                        users.add(customer);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<User> getUsers(int amount) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users LIMIT ?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, amount);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                switch(type) {
                    case "admin":
                        User admin = new Administrator(User.userType.Admin, username, email, password, phone);
                        users.add(admin);
                        break;
                    case "manager":
                        User manager = new Manager(User.userType.Manager, username, email, password, phone);
                        users.add(manager);
                        break;
                    case "customer":
                        User customer = new Customer(User.userType.Customer, username, email, password, phone);
                        users.add(customer);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void deleteUser(String username) {
        String query = "DELETE FROM users WHERE username=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            int rs = stmt.executeUpdate();
            System.out.println("Deleted user: " + username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExisting(String username) {
        String query = "SELECT DISTINCT username FROM users WHERE username=?";
        boolean userExists = false;
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String temp = rs.getString("username");
                if(!temp.isEmpty()) userExists = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userExists;
    }

    public boolean authorize(String username, String password) {
        String query = "SELECT username, password FROM users WHERE username=? AND password=?";
        boolean userExists = false;
        String dbUsername = "";
        String dbPassword = "";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                dbUsername = rs.getString("username");
                dbPassword = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(username.equals(dbUsername) && password.equals(dbPassword)) userExists=true;
        return userExists;
    }

    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT DISTINCT type,username,phone,email FROM users WHERE username=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String userName = rs.getString("username");
                String type = rs.getString("type");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                switch(type) {
                    case "admin":
                        user = new Administrator(User.userType.Admin,userName,email,null,phone);
                        break;
                    case "manager":
                        user = new Manager(User.userType.Admin,userName,email,null,phone);
                        break;
                    case "customer":
                        user = new Customer(User.userType.Admin,userName,email,null,phone);
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
