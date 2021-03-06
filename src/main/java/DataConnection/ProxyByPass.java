package DataConnection;

import java.util.ArrayList;
import java.util.List;

public class ProxyByPass implements DatabaseByPass {
    DatabaseByPass myinformation = new AdminInformation();
    private static List<String> bannedusers;

    //     here the names will be check , if user has inserted these name , system will not go into db.
    static {
        bannedusers = new ArrayList<>();
        bannedusers.add("Hacker");
        bannedusers.add("crackhead");
        bannedusers.add("hack 123");
        bannedusers.add("hacker");
        bannedusers.add("hack");
    }


    @Override
    public boolean authentication(String userID, String password) {

        if (bannedusers.contains(String.valueOf(userID))) {
            return false;
        }
        return myinformation.authentication(userID, password);
    }


    @Override
    public String getUserID() {
        return myinformation.getUserID();
    }

    @Override
    public String getPassword() {
        return myinformation.getPassword();
    }
}


