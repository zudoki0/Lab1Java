package com.lab1java.dao;

import com.lab1java.Database;
import com.lab1java.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderManagerDAO {
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int orderId = rs.getInt("id");
                String orderedBy = rs.getString("orderedBy");
                Order order = new Order(orderId, orderedBy, null);
                orders.add(order);
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
        return orders;
    }

    public void changeOrderStatus(int id, String statusName) {
        String query = "UPDATE orders SET status=? WHERE id=?";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            stmt.setString(1,statusName);
            stmt.setInt(2,id);
            stmt.executeUpdate();
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
}
