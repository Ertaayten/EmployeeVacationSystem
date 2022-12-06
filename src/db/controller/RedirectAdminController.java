package db.controller;

import db.model.DataAccess;
import db.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class RedirectAdminController extends BaseController {

    public RedirectAdminController(DataAccess dataAccess, ViewFactory viewFactory, String fxmlName) {
        super(dataAccess, viewFactory, fxmlName);
    }

    public static boolean isClicked = false;

    @FXML
    void adminPageRedirectButon(ActionEvent event) {
        viewFactory.showAdminPageController();
    }

    @FXML
    void userPageRedirectButon(ActionEvent event) throws SQLException {
        isClicked = true;
        viewFactory.showEmployeeWindow();

    }

}
