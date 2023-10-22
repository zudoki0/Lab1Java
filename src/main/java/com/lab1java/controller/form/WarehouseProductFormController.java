package com.lab1java.controller.form;

import com.lab1java.dao.ProductDAO;
import com.lab1java.dao.WarehouseDAO;
import com.lab1java.dao.WarehouseProductsDAO;
import com.lab1java.model.Product;
import com.lab1java.model.Warehouse;
import com.lab1java.model.WarehouseProducts;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseProductFormController {
    public Spinner<Integer> amount;
    private ProductDAO productDAO;
    private WarehouseDAO warehouseDAO;
    private WarehouseProductsDAO warehouseProductsDAO;
    public ChoiceBox warehouse;
    public ChoiceBox product;

    public void initialize() {
        warehouseDAO = new WarehouseDAO();
        productDAO = new ProductDAO();
        warehouseProductsDAO = new WarehouseProductsDAO();
        List<String> warehouseNames = warehouseDAO.getWarehouses().stream().map(Warehouse::getName).toList();
        warehouse.getItems().addAll(warehouseNames);
        List<String> productNames = productDAO.getProduct().stream().map(Product::getName).toList();
        product.getItems().addAll(productNames);

    }

    public void submit() {
        int warehouseId = warehouseDAO.getWarehouseId((String) this.warehouse.getValue());
        int productId = productDAO.getProductId((String) this.product.getValue());
        WarehouseProducts warehouseProducts = new WarehouseProducts(productId, warehouseId);
        for(int i = 0; i < (Integer) amount.getValue(); i++) {
            warehouseProductsDAO.createWarehouseProduct(warehouseProducts);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully added: " + this.product.getValue(), ButtonType.OK);
        alert.showAndWait();
    }
}
