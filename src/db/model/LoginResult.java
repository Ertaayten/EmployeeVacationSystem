package db.model;

public class LoginResult<X,Y> {
    public X result;
    public Y result1;
    LoginResult(X result,Y result1){
        this.result = result;
        this.result1 = result1;
    }
}
