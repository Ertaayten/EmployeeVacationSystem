package db.controller;

import db.model.Account;
import db.model.Employee;
import db.model.Validation;
import db.model.DataAccess;
import db.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginWindowController extends BaseController{

    @FXML
    public TextField emailField;

    @FXML
    public Label errorLabel;

    @FXML
    public PasswordField passwordField;

    public LoginWindowController(DataAccess dataAccess, ViewFactory viewFactory, String fxmlName) {
        super(dataAccess,viewFactory, fxmlName );
    }
    static Account account;
    public static Employee employee;

    @FXML
    void loginWindowAction() throws SQLException {
        Validation validation = new Validation();
        String email = emailField.getText();
        String password = passwordField.getText();
        if(validation.validateEmailAndPassword(email,password).result && validation.validateEmailAndPassword(email,password).result1){
            account = dataAccess.selectAccountUsingEmail(email);
            employee = dataAccess.selectEmployeeUsingEmployeeId(account.employeeId);
            viewFactory.showRedirectAdminController();
            Stage stage =(Stage) errorLabel.getScene().getWindow();
            viewFactory.closeStage(stage);
            System.out.println("admin page is opening");
        }else if(validation.validateEmailAndPassword(email,password).result && !validation.validateEmailAndPassword(email,password).result1){
            account = dataAccess.selectAccountUsingEmail(email);
            employee = dataAccess.selectEmployeeUsingEmployeeId(account.employeeId);
            viewFactory.showEmployeeWindow();
            Stage stage =(Stage) errorLabel.getScene().getWindow();
            viewFactory.closeStage(stage);
            System.out.println(employee.firstName);
            System.out.println("kullanici sayfasi");
        }else {
            errorLabel.setText("Email or Password is wrong, Please try again");
        }
    }
    @FXML
    void signupWindowAction() {
        viewFactory.showSignUpWindow();
    }
}
