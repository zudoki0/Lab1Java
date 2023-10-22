package com.lab1java.controller.form;

import com.lab1java.dao.WarehouseDAO;
import com.lab1java.model.Warehouse;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class WarehouseUpdateFormController {
    private WarehouseDAO warehouseDAO;
    public TextField name;
    public TextField address;
    public TextField capacity;

    public void submit() {
        if(name.getText() == null || name.getText().isEmpty()
                || address.getText() == null|| address.getText().isEmpty()
                || capacity.getText() == null|| capacity.getText().isEmpty()
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Some fields are empty.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Warehouse warehouse = new Warehouse(name.getText(), address.getText(), Float.parseFloat(capacity.getText()));
        warehouseDAO.updateWarehouse(warehouse);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully updated: " + this.name.getText(), ButtonType.OK);
        alert.showAndWait();
    }

    public void initialize() {
        warehouseDAO = new WarehouseDAO();
        name.setDisable(true);
    }
}
