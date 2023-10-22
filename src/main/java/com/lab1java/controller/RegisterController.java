package com.lab1java.controller;

import com.lab1java.dao.UserDAO;
import com.lab1java.model.Customer;
import com.lab1java.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    private UserDAO userDAO;
    public TextField username;
    public TextField email;
    public TextField phone;
    public PasswordField password;

    public void submit() {
        if(username.getText() == null || username.getText().isEmpty()
                || email.getText() == null || email.getText().isEmpty()
                || phone.getText() == null || phone.getText().isEmpty()
                || password.getText() == null || password.getText().isEmpty()
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Some fields are empty.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if(username.getText().length() > 255
        || password.getText().length() > 255
        || email.getText().length() > 255
        || phone.getText().length() > 255) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Some fields have more than 255 characters.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Customer user = new Customer(User.userType.Customer, username.getText(), email.getText(), password.getText(), phone.getText());
        if(userDAO.isExisting(user.getUsername())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "This user already exists. Try a different username.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        userDAO.createUser(user);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "User: " + user.getUsername() + " has successfully been added.", ButtonType.OK);
        alert.showAndWait();
    }

    public void initialize() {
        userDAO = new UserDAO();
    }
}
