module com.lab1java.lab1java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires java.sql;

    exports com.lab1java.controller;
    exports com.lab1java.dao;
    exports com.lab1java.model;
    exports com.lab1java;
    exports com.lab1java.controller.form;
    opens com.lab1java to javafx.fxml;
}