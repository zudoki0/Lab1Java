package com.lab1java.controller;

import com.lab1java.Main;
import com.lab1java.dao.UserDAO;
import com.lab1java.model.Administrator;
import com.lab1java.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private UserDAO userDAO;
    public TextField username;
    public PasswordField password;
    private MainControllerInterface mainController;
    public void setParentController(MainControllerInterface mainController) {
        this.mainController = mainController;
    }

    public void submit() {
        if(username.getText() == null || username.getText().isEmpty() || password.getText() == null || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Some fields are empty.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if(username.getText().length() > 255 || password.getText().length() > 255) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Some fields have more than 255 characters.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if(userDAO.authorize(username.getText(), password.getText())) {
            Main.user = userDAO.getUserByUsername(username.getText());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "This user doesn't exist.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        this.mainController.setUser();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully logged in as " + username.getText() + '.', ButtonType.OK);
        alert.showAndWait();
    }

    public void initialize() {
        userDAO = new UserDAO();
    }
}
