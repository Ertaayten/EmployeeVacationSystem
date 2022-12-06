package db.model;


import java.sql.SQLException;

public class Validation {

    public static LoginResult<Boolean, Boolean> validateEmailAndPassword(String email, String password) throws SQLException {
        DataAccess dataAccess = new DataAccess();
        dataAccess.selectAccount();
        int employeeId = 0;
        for (int i = 0; i < dataAccess.accounts.size(); i++) {
            if (dataAccess.accounts.get(i).email.equals(email) && dataAccess.accounts.get(i).password.equals(password)) {
                employeeId = i + 1;
                boolean isAdmin = isAdmin(employeeId);
                return new LoginResult<>(true, isAdmin);
            }
        }
        return new LoginResult<>(false,false);
    }

    public static boolean isAdmin(int index) throws SQLException {
        DataAccess dataAccess = new DataAccess();
        dataAccess.selectEmployee();
        for (int i = 0; i< dataAccess.employees.size(); i++){
            if(dataAccess.employees.get(i).eid == index){
                if (dataAccess.employees.get(i).departmentId == 5 || dataAccess.employees.get(i).departmentId == 6){
                    System.out.println("department id : " + dataAccess.employees.get(i).departmentId );
                    return true;
                }
            }
        }
        return false;
    }

}
