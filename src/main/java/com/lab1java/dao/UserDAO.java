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
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            stmt.setString(1, user.getType().toString().toLowerCase());
            stmt.setString(2,user.getUsername());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPassword());
            stmt.setString(5,user.getPhone());
            stmt.executeUpdate();
            System.out.println("Added manager: " + user.getUsername());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

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
            System.out.println(e.getMessage());
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return users;
    }

    public List<User> getUsers(int amount) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users LIMIT ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            stmt.setInt(1, amount);
            rs = stmt.executeQuery();

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
            System.out.println(e.getMessage());
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return users;
    }

    public void deleteUser(String username) {
        String query = "DELETE FROM users WHERE username=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.executeUpdate();
            System.out.println("Deleted user: " + username);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public boolean isExisting(String username) {
        String query = "SELECT DISTINCT username FROM users WHERE username=?";
        boolean userExists = false;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()) {
                String temp = rs.getString("username");
                if(!temp.isEmpty()) userExists = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return userExists;
    }

    public boolean authorize(String username, String password) {
        String query = "SELECT username, password FROM users WHERE username=? AND password=?";
        boolean userExists = false;
        String dbUsername = "";
        String dbPassword = "";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while(rs.next()) {
                dbUsername = rs.getString("username");
                dbPassword = rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        if(username.equals(dbUsername) && password.equals(dbPassword)) userExists=true;
        return userExists;
    }

    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT DISTINCT type,username,phone,email FROM users WHERE username=?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
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
            System.out.println(e.getMessage());
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return user;
    }
}
