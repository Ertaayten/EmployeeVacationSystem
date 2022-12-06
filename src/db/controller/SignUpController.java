package db.controller;

import db.model.BusinessRules;
import db.model.DataAccess;
import db.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Date;

public class SignUpController extends BaseController {

    @FXML
    private DatePicker birthdateDatePicker;

    @FXML
    public ChoiceBox<String> departmentChoiceBox;

    @FXML
    private TextField firstNameTextField;

    @FXML
    public ChoiceBox<String> genderChoiceBox;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField ssnTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void signupButton() throws SQLException {
        BusinessRules businessRules = new BusinessRules();
        DataAccess dataAccess = new DataAccess();
        String department = departmentChoiceBox.getValue();
        int departmentId = 0;
        switch (department){
            case "Marketing": departmentId = 1; break;
            case "Operation": departmentId = 2; break;
            case "Finance" : departmentId = 3; break;
            case "Sales" : departmentId = 4; break;
            case "Human Resources" : departmentId = 5; break;
            case "Secretary" : departmentId = 6; break;
        }

        Date birthdate = java.sql.Date.valueOf(birthdateDatePicker.getValue());
        boolean isAppropriate = businessRules.isAppropriate(firstNameTextField.getText(),lastNameTextField.getText(),emailTextField.getText(),passwordField.getText(), phoneNumberTextField.getText(), ssnTextField.getText());
        if(isAppropriate){
            dataAccess.insertEmployee(ssnTextField.getText(), phoneNumberTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), genderChoiceBox.getValue().toString(),birthdate,departmentId);
            dataAccess.insertAccount(emailTextField.getText(), passwordField.getText(),1002);
            infoLabel.setText("The register was successfully created");
        }else {
            infoLabel.setText("The information ");
        }
    }

    public SignUpController(DataAccess dataAccess, ViewFactory viewFactory, String fxmlName) {
        super(dataAccess, viewFactory, fxmlName);
    }
}
