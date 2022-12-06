package db.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private String userName = "root";
    private String password = "Password1";
    private String dbUrl = "jdbc:mysql://localhost:3306/database_hw";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl,userName,password);
    }

    public void showErrorMessage(SQLException exception){
        System.out.println("Error Message : " + exception.getMessage());
        System.out.println("Error Code : " + exception.getErrorCode());
    }
}
