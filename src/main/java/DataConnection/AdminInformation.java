package DataConnection;

import Java.HMS.Data.Patient;
import Java.HMS.Observer.Publisher;
import Java.HMS.Observer.Subscriber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AdminInformation implements DatabaseByPass , Publisher {

    private static int userid = 0;
    private static String pass = "";
    private boolean logincheck;

    @Override
    public boolean authentication(String userID, String password) {

        try {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            Connection connection = instance.getConnection();
            connection.createStatement();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("select admin_id,password from admin");
            boolean flag = true;
            ResultSet resultset = preparedStatement.executeQuery();

            while ((resultset.next()) && flag) {
                userid = resultset.getInt("admin_id");
                pass = resultset.getString("password");
                int passuserid = Integer.parseInt(userID);
                if (userid== passuserid && password.hashCode() == pass.hashCode()) {
                    flag = false;
                    logincheck = true;
                }
                else {
                    logincheck =false;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return logincheck;
    }

    @Override
    public String getUserID() {
        return String.valueOf(userid);
    }


    @Override
    public String getPassword() {
        return pass;
    }



    @Override
    public void eventnotify() {

    }

    @Override
    public void patientappointmentnotify(List<Subscriber> patientappointmentSubsciber) {
        System.out.println("Sending notification to Patient");
        int count =0;
        for (Subscriber subscriber : patientappointmentSubsciber) {

            Patient sub = (Patient) subscriber;
            if (sub.isCheckappointment()){
                subscriber.patientappointmentnotification();
            }

        }
    }


/*    public String greet(String adminid, String password) throws Exception {

        admin = new ProxyByPass();
        admin.authentication(adminid, password);
        String s1 = getUsername();
        String s2 = getPassword();

        if (adminid.equals(s1) && password.equals(s2)) {
            logincheck = true;
//            return adminid + "  " + password;
                return "hello";
        } else {
            logincheck = false;
//            return "Wrong " + adminid + "  " + password;
                return "wrong";
        }

    }*/


}
