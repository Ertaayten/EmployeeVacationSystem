package db.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class DataAccess {
    public static ArrayList<Employee> employees = new ArrayList<>();
    public ArrayList<Account> accounts = new ArrayList<>();
    public ArrayList<Vacation> vacations = new ArrayList<>();
    Employee employee;
    Account account;

    public static void selectEmployee() throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from employee inner join department on employee.department_id = department.did");
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("eid"),
                        resultSet.getString("ssn"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getDate("birth_date"),
                        resultSet.getInt("department_id")
                ));
            }
            //System.out.println(employees.size());
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
    }

    public void selectAccount() throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from account");
            while (resultSet.next()) {
                accounts.add(new Account(
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("employee_id")
                ));
            }
            //accounts.forEach(account -> System.out.println(account.email));
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
    }

    public Account selectAccountUsingEmail(String email) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "select * from account where email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account = new Account(
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("employee_id")
                );
            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
        return account;
    }

    public Employee selectEmployeeUsingEmployeeId(int eid) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "select eid,ssn,phone_number,first_name,last_name,gender,birth_date,department_id from employee where eid = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, eid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(
                        resultSet.getInt("eid"),
                        resultSet.getString("ssn"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getDate("birth_date"),
                        resultSet.getInt("department_id")
                );
            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
        return employee;
    }
    public ArrayList<EmployeeVacationJoin> selectEmployeesNotInEmployeeId(int eid)throws SQLException{
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        ArrayList<EmployeeVacationJoin> employeeVacationJoins = new ArrayList<>();
        try {
            connection = helper.getConnection();
            String sql = "select * from vacation inner join employee on vacation.employee_id = employee.eid where employee.eid !=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,eid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeVacationJoins.add(
                  new EmployeeVacationJoin(
                          resultSet.getInt("id"),
                          resultSet.getString("first_name"),
                          resultSet.getString("last_name"),
                          resultSet.getInt("department_id"),
                          resultSet.getString("phone_number"),
                          resultSet.getDate("start_date"),
                          resultSet.getDate("finish_date"),
                          resultSet.getString("type"),
                          resultSet.getString("description"),
                          resultSet.getBoolean("is_approved")
                  ));
            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
        return employeeVacationJoins;
    }

    public ArrayList<Vacation> selectVacationsByEmployeeId(int employeeId) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "select * from vacation where employee_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacations.add(new Vacation(
                        resultSet.getInt("id"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("finish_date"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("is_approved"),
                        resultSet.getString("description")
                        ));
            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
        return vacations;
    }

    public void insertEmployee(String ssn, String phoneMumber, String firstName, String lastName, String gender, Date birthDate, int departmentId) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "insert into employee (ssn, phone_number, first_name, last_name, gender, birth_date, department_id) values (?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, ssn);
            statement.setString(2, phoneMumber);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, gender);
            statement.setDate(6, (java.sql.Date) birthDate);
            statement.setInt(7, departmentId);
            statement.executeUpdate();
            System.out.println("employee eklendi");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void insertAccount(String email, String password, int employeeId) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "insert into account (email,password,employee_id) values (?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setInt(3, employeeId);
            statement.executeUpdate();
            System.out.println("account eklendi");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void insertVacationAdd(Date startDate, Date finishDate, String type, int employeeId, int departmentId, String description) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "insert into vacation (start_date,finish_date,type,employee_id,is_approved,department_id, description) values (?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, (java.sql.Date) startDate);
            statement.setDate(2, (java.sql.Date) finishDate);
            statement.setString(3, type);
            statement.setInt(4, employeeId);
            statement.setBoolean(5, false);
            statement.setInt(6, departmentId);
            statement.setString(7, description);
            statement.executeUpdate();
            System.out.println("employee eklendi");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }
    public boolean updateIsApprove(int num,int vid) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "update vacation set is_approved = ? where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,num);
            statement.setInt(2,vid);
            statement.executeUpdate();
            System.out.println("vacation update edildi. ");
            return true;
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
        return false;
    }
    public boolean updateEmployeeVacation(LocalDate start_date, LocalDate finish_Date,String type,String description,int vid) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = " update vacation set start_date = ?, finish_date=?,type = ?,description=? where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, java.sql.Date.valueOf(start_date));
            statement.setDate(2, java.sql.Date.valueOf(finish_Date));
            statement.setString(3,type);
            statement.setString(4,description);
            statement.setInt(5,vid);
            statement.executeUpdate();
            System.out.println("vacation update edildi. ");
            return true;
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
        return false;
    }

}
