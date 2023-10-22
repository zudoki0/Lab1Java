package com.lab1java.controller.form;

import com.lab1java.dao.ProductDAO;
import com.lab1java.model.Product;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProductFormController {
    private ProductDAO productDAO;
    public TextField name;
    public TextField type;
    public TextField price;

    public TextArea description;

    public void submit() {
        if(name.getText() == null || name.getText().isEmpty()
                || type.getText() == null|| type.getText().isEmpty()
                || price.getText() == null|| price.getText().isEmpty()
                || description.getText() == null|| description.getText().isEmpty()
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Some fields are empty.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Product product = new Product(type.getText(), name.getText(), description.getText(), Float.parseFloat(price.getText()));
        productDAO.createProduct(product);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully added: " + this.name.getText(), ButtonType.OK);
        alert.showAndWait();
    }

    public void initialize() {
        productDAO = new ProductDAO();
    }
}
