package DataConnection;

public interface DatabaseByPass {
    String userID ="";
    String password="";

    public boolean authentication(String userID ,String  password);
    public String getUserID();
    public String getPassword();
}
