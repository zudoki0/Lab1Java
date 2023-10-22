package com.lab1java.controller;

import com.lab1java.Main;
import com.lab1java.dao.WarehouseDAO;
import com.lab1java.model.Product;
import com.lab1java.model.Warehouse;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WarehouseController {
    private WarehouseDAO warehouseDAO;
    public TableView<Warehouse> warehouseTable;
    public TableColumn name;
    public TableColumn address;
    public TableColumn capacity;
    public void initialize() {
        warehouseDAO = new WarehouseDAO();
        reload();
    }

    public void reload() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        warehouseTable.setItems(warehouseDAO.getWarehouses());
    }

    public void create() throws IOException {
        Stage formStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/form/warehouse-form.fxml"));
        Scene formScene = new Scene(fxmlLoader.load());
        formStage.setScene(formScene);
        formStage.setTitle("Create new warehouse");
        formStage.initModality(Modality.APPLICATION_MODAL);
        formStage.showAndWait();
        reload();
    }

    public void delete() {
        Warehouse warehouse = warehouseTable.getSelectionModel().getSelectedItem();
        if(warehouse == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No item was selected!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        warehouseDAO.deleteWarehouse(warehouse.getName());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully deleted: " + this.name.getText(), ButtonType.OK);
        alert.showAndWait();
        reload();
    }

    public void update() throws IOException {
        Warehouse warehouse = warehouseTable.getSelectionModel().getSelectedItem();
        if(warehouse == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No item was selected!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Stage formStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/form/warehouse-update-form.fxml"));
        Scene formScene = new Scene(fxmlLoader.load());
        Node name = formScene.lookup("#name");
        Node address = formScene.lookup("#address");
        Node capacity = formScene.lookup("#capacity");
        if(name instanceof TextField) {
            TextField nameText = (TextField) name;
            nameText.setText(warehouse.getName());
        }
        if(address instanceof TextField) {
            TextField addressText = (TextField) address;
            addressText.setText(warehouse.getAddress());
        }
        if(capacity instanceof TextField) {
            TextField capacityText = (TextField) capacity;
            capacityText.setText(String.valueOf(warehouse.getCapacity()));
        }
        formStage.setScene(formScene);
        formStage.setTitle("Update a warehouse");
        formStage.initModality(Modality.APPLICATION_MODAL);
        formStage.showAndWait();
        reload();
    }
}
