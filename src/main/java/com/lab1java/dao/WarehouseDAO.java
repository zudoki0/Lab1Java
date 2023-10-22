package com.lab1java.dao;

import com.lab1java.Database;
import com.lab1java.model.Warehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {
    public void createWarehouse(Warehouse warehouse) {
        String query = "INSERT INTO warehouses(name, address, capacity) VALUES(?,?,?)";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,warehouse.getName());
            stmt.setString(2,warehouse.getAddress());
            stmt.setFloat(3, warehouse.getCapacity());
            int rs = stmt.executeUpdate();
            System.out.println("Added warehouse: " + warehouse.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteWarehouse(String warehouse) {
        String query = "DELETE FROM warehouses WHERE name=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, warehouse);
            int rs = stmt.executeUpdate();
            System.out.println("Deleted user: " + warehouse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateWarehouse(Warehouse warehouse) {
        String query = "UPDATE warehouses SET address=?, capacity=? WHERE name=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,warehouse.getAddress());
            stmt.setFloat(2, warehouse.getCapacity());
            stmt.setString(3, warehouse.getName());
            int rs = stmt.executeUpdate();
            System.out.println("Added warehouse: " + warehouse.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Warehouse> getWarehouses() {
        List<Warehouse> warehouseList = new ArrayList<>();
        String query = "SELECT * FROM warehouses";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                float capacity = rs.getFloat("capacity");
                Warehouse warehouse = new Warehouse(name,address,capacity);
                warehouseList.add(warehouse);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(warehouseList);
    }

    public int getWarehouseId(String name) {
        int warehouseId = 0;
        String query = "SELECT id FROM warehouses WHERE name=?";
        try {
            Connection con = DriverManager.getConnection(Database.dbURL,Database.dbUsername, Database.dbPassword);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                warehouseId = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return warehouseId;
    }

}
