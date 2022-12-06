package db.controller;

import db.model.EmployeeVacationJoin;
import db.model.DataAccess;
import db.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminWindowController extends BaseController implements Initializable {
    public AdminWindowController(DataAccess dataAccess, ViewFactory viewFactory, String fxmlName) {
        super(dataAccess, viewFactory, fxmlName);
    }

    @FXML
    private TableView<EmployeeVacationJoin> adminTableView;

    @FXML
    private TableColumn<EmployeeVacationJoin, Integer> col_department_id;

    @FXML
    private TableColumn<EmployeeVacationJoin, String> col_description;

    @FXML
    private TableColumn<EmployeeVacationJoin, Date> col_finish_date;

    @FXML
    private TableColumn<EmployeeVacationJoin, String> col_first_name;

    @FXML
    private TableColumn<EmployeeVacationJoin , String> col_last_name;

    @FXML
    private TableColumn<EmployeeVacationJoin, String> col_phone_number;

    @FXML
    private TableColumn<EmployeeVacationJoin, Date> col_start_date;

    @FXML
    private TableColumn<EmployeeVacationJoin, String> col_type;

    @FXML
    private TableColumn<EmployeeVacationJoin,Boolean> col_is_approved;

    @FXML
    void approveButton(ActionEvent event) throws SQLException {
        int eid = adminTableView.getSelectionModel().getSelectedItem().id.getValue();
        boolean isUpdated = dataAccess.updateIsApprove(1,eid);
        if(isUpdated){
            System.out.println("Vacation Update edildi. ");
            Stage stage =(Stage) adminTableView.getScene().getWindow();
            viewFactory.closeStage(stage);
            viewFactory.showAdminPageController();
        }else {
            System.out.println("Vacation update edilmedi. ");
        }
}

    @FXML
    void disapproveButton(ActionEvent event) throws SQLException {
        int eid = adminTableView.getSelectionModel().getSelectedItem().id.getValue();
        boolean isUpdated = dataAccess.updateIsApprove(0,eid);
        if(isUpdated){
            System.out.println("Vacation Update edildi. ");
            Stage stage =(Stage) adminTableView.getScene().getWindow();
            viewFactory.closeStage(stage);
            viewFactory.showAdminPageController();
        }else {
            System.out.println("Vacation update edilmedi. ");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<EmployeeVacationJoin> employeeVacationJoins = new ArrayList<>();
        try {
            employeeVacationJoins = dataAccess.selectEmployeesNotInEmployeeId(LoginWindowController.employee.eid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<EmployeeVacationJoin> employeeVacationJoinObservableList = FXCollections.observableList(employeeVacationJoins);
        col_first_name.setCellValueFactory(cellData->cellData.getValue().firstName);
        col_last_name.setCellValueFactory(cellData->cellData.getValue().lastName);
        col_department_id.setCellValueFactory(cellData->cellData.getValue().departmentId.asObject());
        col_phone_number.setCellValueFactory(cellData->cellData.getValue().phoneNumber);
        col_start_date.setCellValueFactory(cellData->cellData.getValue().startDate);
        col_finish_date.setCellValueFactory(cellData->cellData.getValue().finishDate);
        col_type.setCellValueFactory(cellData->cellData.getValue().type);
        col_description.setCellValueFactory(cellData -> cellData.getValue().description);
        col_is_approved.setCellValueFactory(cellData -> cellData.getValue().isApproved);
        adminTableView.setItems(employeeVacationJoinObservableList);

    }
}
