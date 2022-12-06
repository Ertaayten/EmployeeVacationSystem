package db.model;

import java.util.Date;

public class Employee {
    public int eid;
    public String ssn;
    public String phoneNumber;
    public String firstName;
    public String lastName;
    public String  gender;
    public Date birthDate;
    public int departmentId;

    public Employee(int eid,String ssn, String phoneNumber,String firstName, String lastName,String gender, Date birthDate,int departmentId){
        this.eid = eid;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.departmentId = departmentId;
    }
    public Employee(int eid){
        this.eid = eid;
    }
}
