module DBHomework {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires java.sql;

    opens db;
    opens db.view;
    opens db.controller;
}