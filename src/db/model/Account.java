package db.model;

public class Account {
    public String email;
    public String password;
    public int employeeId;

    public Account(String email, String password, int employeeId) {
        this.email = email;
        this.password = password;
        this.employeeId = employeeId;
    }


}
