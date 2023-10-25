package com.lab1java.dao;

import com.lab1java.Database;
import com.lab1java.model.WarehouseProducts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
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
            stmt.executeUpdate();
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
            stmt.executeUpdate();
            System.out.println("Updated warehouse product: " + warehouseProducts.getProductId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWarehouseProduct(String productName, int amount) {
        int productId = 0;
        String query = "select * from warehouse_products wp JOIN products p ON wp.productId=p.id WHERE p.name=?";
        String query2 = "DELETE FROM warehouse_products WHERE productId=? LIMIT ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            stmt.setString(1, productName);
            rs = stmt.executeQuery();
            while(rs.next()) {
                productId = rs.getInt("productId");
            }
            stmt = con.prepareStatement(query2);
            stmt.setInt(1,productId);
            stmt.setInt(2,amount);
            stmt.executeUpdate();
            System.out.println("Deleted warehouse product " + amount + " " + productId);
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
    }
    public ObservableList<WarehouseProducts> getWarehouseProducts() {
        List<WarehouseProducts> warehouseProductsList = new ArrayList<>();
        String query = "SELECT * FROM warehouse_products";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int productId = rs.getInt("productId");
                int warehouseId = rs.getInt("warehouseId");
                Date createdOn = (rs.getDate("createdOn"));
                WarehouseProducts warehouseProducts = new WarehouseProducts(productId,warehouseId);
                warehouseProductsList.add(warehouseProducts);
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
        return FXCollections.observableArrayList(warehouseProductsList);
    }


}
