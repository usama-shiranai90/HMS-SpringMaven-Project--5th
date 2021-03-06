package Java.HMS.Data;

import Observer.Subscriber;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;

@Service
public class Patient extends HospitalRecord implements Serializable , Subscriber {

    private int patientid;
    private String address;
    private String phoneno;
    private LocalDate date;
    private String bloodgroup;
    private boolean checkappointment;

    public Patient() {
        super();
    }

    public Patient(String firstname, String lastname, int age, String sex, String email, String address, String phoneno) {
        super(firstname, lastname, age, sex, email);
        this.address = address;
        this.phoneno = phoneno;
    }

public Patient(int patientid, String firstname , String lastname , int age , String sex , String address , String phoneno , String email , String bloodgroup , LocalDate date){
        super(firstname , lastname , age , sex , email);
        this.patientid = patientid;
        this.address = address;
        this.phoneno = phoneno;
        this.bloodgroup = bloodgroup;
        this.address = address;
        this.date = date;
}

    public Patient(String firstname , String lastname , int age , String sex , String address , String phoneno , String email , String bloodgroup , LocalDate date){
        super(firstname , lastname , age , sex , email);
        this.address = address;
        this.phoneno = phoneno;
        this.bloodgroup = bloodgroup;
        this.address = address;
        this.date = date;
    }


    public Patient(String firstname, String lastname, int age, String sex, String email, String address, String phoneno, LocalDate date, String bloodgroup) {
        super(firstname, lastname, age, sex, email);
        this.address = address;
        this.phoneno = phoneno;
        this.date = date;
        this.bloodgroup = bloodgroup;
    }


    public Patient( int patientid ,String firstname, String lastname, int age, String sex, String address, String phoneno, LocalDate date) {
        super(firstname, lastname, age, sex);
        this.patientid = patientid;
        this.address = address;
        this.phoneno = phoneno;
        this.date = date;
    }

    public Patient(String firstname, String lastname, String sex) {
        super(firstname , lastname , sex);
    }



    public Patient(int patientid, String firstname, String lastname, String sex) {

        super(firstname , lastname , sex);
        this.patientid = patientid;

    }



    public Patient(String firstname, String lastname, int age, String email) {
        super(firstname ,lastname ,age , email);
    }

    public Patient(String firstname, String lastname, int age, String address, String phoneno, String email) {
        super(firstname, lastname, email, age);
        this.address = address;
        this.phoneno = phoneno;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String generateSecureRandomPassword() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String password = RandomStringUtils.random(length, useLetters, useNumbers);
        return password;

    }

    public boolean isCheckappointment() {
        return checkappointment;
    }

    public void setCheckappointment(boolean checkappointment) {
        this.checkappointment = checkappointment;
    }

    @Override
    public String toString() {
        return String.format("%s\nPatient-ID=%d\nAddress:%s\nPhone Number: %s", super.toString() ,patientid, address, phoneno);
    }

    @Override
    public void patientappointmentnotification() {
//        System.out.printf("Hello %s your id:%d and appointment has been set on (%s)\n", firstname+" "+lastname,patientid,date);
    }

    @Override
    public void eventnotification() {

    }

    /*    public boolean addPatientToDatabase(Patient p) {
        this.patient = p;
        try {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            Connection connection = instance.getConnection();
            connection.createStatement();
            String query = "insert into patient(Fname, Lname, email, address, phoneno, sex, DOB, age, bloodgroup , password ) VALUE \n" +
                    "    (? , ?, ?, ?, ?, ?, ?, ?, ? , ?);";
            if (patient == null) {
                return false;
            } else {

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, patient.getFirstname());
                preparedStatement.setString(2, patient.getLastname());
                preparedStatement.setString(3, patient.getEmail());
                preparedStatement.setString(4, patient.getAddress());
                preparedStatement.setString(5, patient.getPhoneno());
                preparedStatement.setString(6, patient.getSex());
                preparedStatement.setDate(7, java.sql.Date.valueOf(patient.getDate()));
                preparedStatement.setInt(8, patient.getAge());
                preparedStatement.setString(9, patient.getBloodgroup());
                preparedStatement.setString(10, patient.generateSecureRandomPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return true;
    }*/
    /*    public List confirmationPatientList() {
        try {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            Connection connection = instance.getConnection();
            Statement   statement =    connection.createStatement();
            String query = "select patient_id from patient where email = '" + patient.getEmail() + "'";

            ResultSet  resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                //Retrieve by column name
                patient.setPatientid(resultSet.getInt("patient_id"));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        List patientList = new ArrayList<>();
        patientList.add(patient.getPatientid());
        patientList.add(patient.getPhoneno());
        return patientList;
    }*/
}

