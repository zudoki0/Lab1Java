package com.lab1java.dao;

import com.lab1java.Database;
import com.lab1java.model.Product;
import com.lab1java.model.Warehouse;
import com.lab1java.model.WarehouseProducts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class WarehouseProductsDAO {
    public void createWarehouseProduct(WarehouseProducts warehouseProducts) {
        String query = "INSERT INTO warehouse_products(productId, warehouseId, createdOn) VALUES(?,?,?)";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,warehouseProducts.getProductId());
            stmt.setInt(2,warehouseProducts.getWarehouseId());
            stmt.setDate(3, warehouseProducts.getCreatedOn());
            int rs = stmt.executeUpdate();
            System.out.println("Added warehouse product: " + warehouseProducts.getProductId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateWarehouseProduct(WarehouseProducts warehouseProducts, int id) {
        String query = "UPDATE warehouse_products SET productId=?,warehouseId=? WHERE id=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,warehouseProducts.getProductId());
            stmt.setInt(2,warehouseProducts.getWarehouseId());
            int rs = stmt.executeUpdate();
            System.out.println("Updated warehouse product: " + warehouseProducts.getProductId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWarehouseProduct(String productName, int amount) {
        int productId = 0;
        String query = "select * from warehouse_products wp JOIN products p ON wp.productId=p.id WHERE p.name=?";
        String query2 = "DELETE FROM warehouse_products WHERE productId=? LIMIT ?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, productName);
            PreparedStatement stmt2 = con.prepareStatement(query2);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                productId = rs.getInt("productId");
            }
            stmt2.setInt(1,productId);
            stmt2.setInt(2,amount);
            int rs2 = stmt2.executeUpdate();
            System.out.println("Deleted warehouse product " + amount + " " + productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<WarehouseProducts> getWarehouseProducts() {
        List<WarehouseProducts> warehouseProductsList = new ArrayList<>();
        String query = "SELECT * FROM warehouse_products";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int productId = rs.getInt("productId");
                int warehouseId = rs.getInt("warehouseId");
                Date createdOn = (rs.getDate("createdOn"));
                WarehouseProducts warehouseProducts = new WarehouseProducts(productId,warehouseId);
                warehouseProductsList.add(warehouseProducts);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(warehouseProductsList);
    }


}
