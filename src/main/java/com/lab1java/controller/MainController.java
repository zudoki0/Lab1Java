package com.lab1java.controller;

import com.lab1java.Main;
import com.lab1java.model.Administrator;
import com.lab1java.model.Customer;
import com.lab1java.model.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;

public class MainController implements MainControllerInterface{
    @FXML
    public LoginController loginController;
    public TabPane tabPane;
    public Tab homePage;
    public Tab loginPage;
    public Tab registerPage;
    public Text user;
    public Button logoutButton;
    public Tab productPage;
    public Tab employeePage;
    public Tab warehousePage;
    public Tab cartPage;
    public Tab orderPage;
    public Tab warehouseProductsPage;

    @Override
    public void setUser() {
        user.setText("User: "+ Main.user.getUsername());
        if (Main.user instanceof Administrator) {
            employeePage.setDisable(false);
            warehousePage.setDisable(false);
            orderPage.setDisable(false);
        } else if (Main.user instanceof Manager) {
            orderPage.setDisable(false);
        } else if (Main.user instanceof Customer) {
            cartPage.setDisable(false);
        }
        logoutButton.setVisible(true);
    }

    public void initialize() {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        loginController.setParentController(this);
        logoutButton.setVisible(false);
    }

    public void logout() {
        employeePage.setDisable(true);
        warehousePage.setDisable(true);
        orderPage.setDisable(true);
        cartPage.setDisable(true);
        user.setText("No one logged in right now...");
        logoutButton.setVisible(false);
        Main.user = null;
    }
}
