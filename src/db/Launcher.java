package db;

import db.model.DataAccess;
import db.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        DataAccess dataAccess = new DataAccess();
        ViewFactory viewFactory = new ViewFactory(new DataAccess());
        viewFactory.showLoginWindow();
    }
}
