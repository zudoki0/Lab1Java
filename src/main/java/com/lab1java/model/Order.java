package com.lab1java.model;

import com.lab1java.Database;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Date;

public class Order {
    private int id;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(int id, String orderedBy, List<Integer> warehouseProducts) {
        this.id = id;
        this.date = date;
        this.orderedBy = orderedBy;
        this.warehouseProducts = warehouseProducts;
        this.status = "Unconfirmed";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Date date;
    private String orderedBy;
    private List<Integer> warehouseProducts;

    public Order(String orderedBy, List<Integer> warehouseProducts) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        this.date = Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
        this.orderedBy = orderedBy;
        this.warehouseProducts = warehouseProducts;
    }

    public void addProduct(int warehouseProductId) {
        this.warehouseProducts.add(warehouseProductId);
    }

    public void deleteProduct(int warehouseProductId) {
        this.warehouseProducts.remove(warehouseProductId);
    }

    public void submit() {
        String query = "INSERT INTO orders(date,orderedBy,status) VALUES(?,?,?)";
        String query2 = "SELECT id FROM orders WHERE date=? AND orderedBy=? FIRST";
        String query3 = "INSERT INTO order_products(warehouseProductId, orderId) VALUES(?,?)";
        int orderId = 0;
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            PreparedStatement stmt2 = con.prepareStatement(query2);
            stmt.setDate(1, (java.sql.Date) this.date);
            stmt.setString(2, this.orderedBy);
            stmt.setString(3, this.status);
            stmt2.setDate(1, (java.sql.Date) this.date);
            stmt2.setString(2, this.orderedBy);
            int rs = stmt.executeUpdate();
            ResultSet rs2 = stmt2.executeQuery();
            while(rs2.next()) {
                orderId = rs2.getInt("id");
            }
            System.out.println("Added order: " + orderId);
            for(int p : warehouseProducts) {
                PreparedStatement stmt3 = con.prepareStatement(query3);
                stmt3.setInt(1,p);
                stmt3.setInt(2,orderId);
                int rs3 = stmt3.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
