package com.lab1java.dao;

import com.lab1java.Database;
import com.lab1java.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public void createProduct(Product product) {
        String query = "INSERT INTO products(type, name, description, price) VALUES(?,?,?,?)";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,product.getType());
            stmt.setString(2,product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setFloat(4, product.getPrice());
            int rs = stmt.executeUpdate();
            System.out.println("Added product: " + product.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateProduct(Product product, int id) {
        String query = "UPDATE products SET type=?,name=?,description=?,price=? WHERE id=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,product.getType());
            stmt.setString(2,product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setFloat(4, product.getPrice());
            stmt.setInt(5, id);
            int rs = stmt.executeUpdate();
            System.out.println("Updated product: " + product.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteProduct(String name) {
        String query = "DELETE FROM products WHERE name=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            int rs = stmt.executeUpdate();
            System.out.println("Deleted product: " + name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            int rs = stmt.executeUpdate();
            System.out.println("Deleted product: " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Product> getTableProduct() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String type = rs.getString("type");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                Product product = new Product(type, name, description, price);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(productList);
    }

    public List<Product> getProduct() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String type = rs.getString("type");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                Product product = new Product(type, name, description, price);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public List<Product> getProduct(int amount) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products LIMIT ?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, amount);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String type = rs.getString("type");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String user = rs.getString("user");
                Product product = new Product(type, name, description, price);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public ObservableList<Product> getWarehouseProducts(int amountOfProducts) {
        List<Product> warehouseProductsList = new ArrayList<>();
        String query = "select distinct wp.productId, p.name, p.description, p.price, p.type, count(wp.productId) AS amount FROM warehouse_products wp JOIN products p ON wp.productId=p.id GROUP BY wp.productId LIMIT ?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, amountOfProducts);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int amount = rs.getInt("amount");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String type = rs.getString("type");
                Product product = new Product(type,name,description,price,amount);
                warehouseProductsList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(warehouseProductsList);
    }

    public ObservableList<Product> getWarehouseProductsByPrice(boolean descending) {
        List<Product> warehouseProductsList = new ArrayList<>();
        String query = "";
        if(descending) query = "select distinct wp.productId, p.name, p.description, p.price, p.type, count(wp.productId) AS amount FROM warehouse_products wp JOIN products p ON wp.productId=p.id GROUP BY wp.productId ORDER BY p.price DESC";
        else query = "select distinct wp.productId, p.name, p.description, p.price, p.type, count(wp.productId) AS amount FROM warehouse_products wp JOIN products p ON wp.productId=p.id GROUP BY wp.productId ORDER BY p.price ASC";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int amount = rs.getInt("amount");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String type = rs.getString("type");
                Product product = new Product(type,name,description,price,amount);
                warehouseProductsList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(warehouseProductsList);
    }

    public ObservableList<Product> getWarehouseProductsByName(boolean descending) {
        List<Product> warehouseProductsList = new ArrayList<>();
        String query = "";
        if(descending) query = "select distinct wp.productId, p.name, p.description, p.price, p.type, count(wp.productId) AS amount FROM warehouse_products wp JOIN products p ON wp.productId=p.id GROUP BY wp.productId ORDER BY p.name DESC";
        else query = "select distinct wp.productId, p.name, p.description, p.price, p.type, count(wp.productId) AS amount FROM warehouse_products wp JOIN products p ON wp.productId=p.id GROUP BY wp.productId ORDER BY p.name ASC";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int amount = rs.getInt("amount");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String type = rs.getString("type");
                Product product = new Product(type,name,description,price,amount);
                warehouseProductsList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(warehouseProductsList);
    }

    public int getProductId(String name) {
        int productId = 0;
        String query = "SELECT id FROM products WHERE name=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                productId = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productId;
    }
}
