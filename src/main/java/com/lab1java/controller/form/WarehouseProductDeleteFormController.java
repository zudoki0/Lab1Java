package com.lab1java.controller.form;

import com.lab1java.dao.WarehouseProductsDAO;
import com.lab1java.model.WarehouseProducts;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;

public class WarehouseProductDeleteFormController {
    WarehouseProductsDAO warehouseProductsDAO;
    public Text productName;
    public Spinner<Integer> amount;
    public void submit() {
        warehouseProductsDAO.deleteWarehouseProduct(productName.getText(), amount.getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully deleted "  + amount.getValue() + " " + productName.getText(), ButtonType.OK);
        alert.showAndWait();
    }

    public void initialize() {
        warehouseProductsDAO = new WarehouseProductsDAO();
    }
}
