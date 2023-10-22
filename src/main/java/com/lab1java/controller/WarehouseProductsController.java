package com.lab1java.controller;

import com.lab1java.Main;
import com.lab1java.dao.ProductDAO;
import com.lab1java.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WarehouseProductsController {
    private ProductDAO productDAO;
    public TableColumn name;
    public TableColumn type;
    public TableColumn amount;
    public TableColumn price;
    public TableView productsTable;
    public Spinner<Integer> amountOfProducts;

    public void initialize() {
        productDAO = new ProductDAO();
        reload();
    }

    public void create(ActionEvent actionEvent) throws IOException {
        Stage formStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/form/warehouse-product-form.fxml"));
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
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(productDAO.getWarehouseProducts(amountOfProducts.getValue()));
    }

    public void delete() throws IOException {
        Product product = (Product) productsTable.getSelectionModel().getSelectedItem();
        if(product == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No item was selected!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Stage formStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/form/warehouse-product-delete-form.fxml"));
        Scene formScene = new Scene(fxmlLoader.load());
        Node amount = formScene.lookup("#amount");
        if(amount instanceof Spinner) {
            Spinner<Integer> amountSpinner = (Spinner<Integer>) amount;
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, product.getAmount(), 1);
            amountSpinner.setValueFactory(valueFactory);
        }
        Node productName = formScene.lookup("#productName");
        if(productName instanceof Text) {
            Text productNameText = (Text) productName;
            productNameText.setText(product.getName());
        }
        formStage.setScene(formScene);
        formStage.setTitle("Delete a product");
        formStage.initModality(Modality.APPLICATION_MODAL);
        formStage.showAndWait();
        reload();
    }
}
