package db.controller;

import db.model.Account;
import db.model.Employee;
import db.model.DataAccess;
import db.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Date;

public class VacationAddController extends BaseController {

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker finishDateDatePicker;

    @FXML
    private Label infoLabel;

    @FXML
    private DatePicker startDateDatePicker;

    @FXML
    public ChoiceBox<String> typeChoiceBox;

    public VacationAddController(DataAccess dataAccess, ViewFactory viewFactory, String fxmlName) {
        super(dataAccess, viewFactory, fxmlName);
    }

    @FXML
    void createVacation()throws SQLException {
        DataAccess dataAccess = new DataAccess();
        Account account;
        Employee employee;
        Date startDate = java.sql.Date.valueOf(startDateDatePicker.getValue());
        Date finishDate = java.sql.Date.valueOf(finishDateDatePicker.getValue());
        if(isNullValues(startDate,finishDate)){
            dataAccess.insertVacationAdd(startDate,finishDate,typeChoiceBox.getValue(), LoginWindowController.account.employeeId,LoginWindowController.employee.departmentId, descriptionTextArea.getText());
            System.out.println("Vacation is added. ");
            viewFactory.showEmployeeWindow();
            Stage stage =(Stage) infoLabel.getScene().getWindow();
            viewFactory.closeStage(stage);
        }else {
            System.out.println("Vacation is NOT added !!!");
        }
    }

    private boolean isNullValues(Date startDate, Date finishDate){
        if(!startDate.toString().isEmpty() && !finishDate.toString().isEmpty()){
            return true;
        }
        return false;
    }

}
