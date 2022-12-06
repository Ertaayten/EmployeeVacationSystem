package db.controller;

import db.model.DataAccess;
import db.view.ViewFactory;

public abstract class BaseController {

    protected ViewFactory viewFactory;
    protected String fxmlName;
    public DataAccess dataAccess;

    public BaseController(DataAccess dataAccess, ViewFactory viewFactory, String fxmlName ) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
        this.dataAccess = dataAccess;
    }

    public String getFxmlName() {
        return fxmlName;
    }

}
