package db.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessRules {

    public boolean isAppropriate(String firstName,String lastName,String email,String password , String phoneNumber, String ssn){
        if(isNameGreaterThan2(firstName) && isNameGreaterThan2(lastName) && isEmailAppropriate(email) && isPasswordAppropriate(password) && isValuesNull(phoneNumber,ssn)){
            return true;
        }
        return false;
    }

    public boolean isNameGreaterThan2(String name){
        if(name.length()>2) {
           return true;
        }
        return false;
    }
    public boolean isEmailAppropriate(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean isPasswordAppropriate(String password){
        String regex = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public boolean isValuesNull(String phoneNumber, String ssn){
        if(!phoneNumber.isEmpty() && !ssn.isEmpty()){
            return true;
        }
        return false;
    }



}
