package db.model;

import javafx.beans.property.*;

import java.util.Date;

public class Vacation {
    public IntegerProperty id;
    public ObjectProperty<Date> startDate;
    public ObjectProperty<Date> finishDate;
    public StringProperty type;
    public BooleanProperty isApproved;
    public StringProperty description;

    public Vacation(int id, Date startDate, Date finishDate, String type, boolean isApproved, String description) {
        this.id = new SimpleIntegerProperty(id);
        this.startDate =new SimpleObjectProperty<Date>(startDate) ;
        this.finishDate =new SimpleObjectProperty<Date>(finishDate) ;
        this.type = new SimpleStringProperty(type);
        this.isApproved =new SimpleBooleanProperty(isApproved) ;
        this.description =new SimpleStringProperty(description) ;
    }


}
