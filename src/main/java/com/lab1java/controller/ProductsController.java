package com.lab1java.controller;

import com.lab1java.Main;
import com.lab1java.controller.form.ProductUpdateFormController;
import com.lab1java.dao.ProductDAO;
import com.lab1java.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class ProductsController {
    private ProductDAO productDAO;
    public TableColumn name;
    public TableColumn type;
    public TableColumn price;
    public TableView<Product> productsTable;


    public void initialize() {
        productDAO = new ProductDAO();
        reload();
    }

    public void create(ActionEvent actionEvent) throws IOException {
        Stage formStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/form/product-form.fxml"));
        Scene formScene = new Scene(fxmlLoader.load());
        formStage.setScene(formScene);
        formStage.setTitle("Create new product");
        formStage.initModality(Modality.APPLICATION_MODAL);
        formStage.showAndWait();
        reload();
    }

    public void reload() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setItems(productDAO.getTableProduct());
    }

    public void delete() {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if(product == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No item was selected!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        productDAO.deleteProduct(product.getName());
        reload();
    }

    public void update() throws IOException {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if(product == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No item was selected!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Stage formStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/form/product-update-form.fxml"));
        Scene formScene = new Scene(fxmlLoader.load());
        Node name = formScene.lookup("#name");
        Node type = formScene.lookup("#type");
        Node price = formScene.lookup("#price");
        Node description = formScene.lookup("#description");
        if(name instanceof TextField) {
            TextField nameText = (TextField) name;
            nameText.setText(product.getName());
        }
        if(type instanceof TextField) {
            TextField typeText = (TextField) type;
            typeText.setText(product.getType());
        }
        if(price instanceof TextField) {
            TextField priceText = (TextField) price;
            priceText.setText(String.valueOf(product.getPrice()));
        }
        if(description instanceof TextArea) {
            TextArea descriptionText = (TextArea) description;
            descriptionText.setText(product.getDescription());
        }
        formStage.setScene(formScene);
        formStage.setTitle("Update a product");
        formStage.initModality(Modality.APPLICATION_MODAL);
        formStage.showAndWait();
        reload();
    }
}
