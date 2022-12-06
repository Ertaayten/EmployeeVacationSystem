package db.controller;

import db.model.DataAccess;
import db.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class UpdateEmployeeVacationController extends BaseController{

    @FXML
    public TextArea descriptionTextArea;

    @FXML
    public DatePicker finishDateDatePicker;

    @FXML
    public Label infoLabel;

    @FXML
    public DatePicker startDateDatePicker;

    @FXML
    public ChoiceBox<String> typeChoiceBox;

    public UpdateEmployeeVacationController(DataAccess dataAccess, ViewFactory viewFactory, String fxmlName) {
        super(dataAccess, viewFactory, fxmlName);
    }

    @FXML
    void updateVacation(ActionEvent event) throws SQLException {
        boolean isTrue = dataAccess.updateEmployeeVacation(startDateDatePicker.getValue(),finishDateDatePicker.getValue(),typeChoiceBox.getValue(),descriptionTextArea.getText(),viewFactory.vid);
        if (isTrue){
            dataAccess.updateIsApprove(0,viewFactory.vid);
            Stage stage =(Stage) infoLabel.getScene().getWindow();
            viewFactory.closeStage(stage);
            viewFactory.tableView.getItems().clear();
            Stage stage1 = (Stage) viewFactory.tableView.getScene().getWindow();
            viewFactory.closeStage(stage1);
            viewFactory.showEmployeeWindow();
        }
    }

}
