package db.model;

import javafx.beans.property.*;

import java.util.Date;

public class EmployeeVacationJoin {

    public IntegerProperty id;
    public ObjectProperty<Date> startDate;
    public ObjectProperty<Date> finishDate;
    public StringProperty type;
    public BooleanProperty isApproved;
    public StringProperty description;

    public IntegerProperty eid;
    public String ssn;
    public StringProperty phoneNumber;
    public StringProperty firstName;
    public StringProperty lastName;
    public String gender;
    public Date birthDate;
    public IntegerProperty departmentId;

    public EmployeeVacationJoin(int id,String firstName, String lastName, Integer departmentId,String phoneNumber,Date startDate, Date finishDate, String type, String description,boolean isApproved) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName =new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.departmentId = new SimpleIntegerProperty(departmentId);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.startDate = new SimpleObjectProperty<Date>(startDate);
        this.finishDate = new SimpleObjectProperty<Date>(finishDate);
        this.type = new SimpleStringProperty(type);
        this.description = new SimpleStringProperty(description);
        this.isApproved = new SimpleBooleanProperty(isApproved);

    }



}
