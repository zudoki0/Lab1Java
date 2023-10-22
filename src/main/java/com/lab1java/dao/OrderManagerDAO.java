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
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int orderId = rs.getInt("id");
                String orderedBy = rs.getString("orderedBy");
                Order order = new Order(orderId, orderedBy, null);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public void changeOrderStatus(int id, String statusName) {
        String query = "UPDATE orders SET status=? WHERE id=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,statusName);
            stmt.setInt(2,id);
            int rs = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
