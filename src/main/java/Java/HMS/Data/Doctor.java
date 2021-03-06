package Java.HMS.Data;

import DataConnection.DatabaseConnection;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class Doctor implements Serializable {

    private int doctor_id;
    private String firstname;
    private String lastname;
    private int age;
    private String sex;
    private String phoneno;
    private LocalDate date;
    private String fullname;
    private String email;
    private String experience;
    private List<String> specialization;
    private Doctor doctor;

    public Doctor() {
    }

    public Doctor(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Doctor(int doctor_id, String firstname, String lastname, int age, String sex, String phoneno, LocalDate date, String email, String experience) {
        this.doctor_id = doctor_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.sex = sex;
        this.phoneno = phoneno;
        this.date = date;
        this.fullname = firstname + " " + lastname;
        this.email = email;
        this.experience = experience;
    }

    public Doctor(String firstname, String lastname, int age, String sex, String phoneno, LocalDate date, String email, String experience) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.sex = sex;
        this.phoneno = phoneno;
        this.date = date;
        this.email = email;
        this.experience = experience;
        fullname = firstname + " " + lastname;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<String> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<String> specialization) {
        this.specialization = specialization;
    }

    public List<String> generalizations() {

        try {
            specialization = new ArrayList<>();
            DatabaseConnection instance = DatabaseConnection.getInstance();
            Connection connection = instance.getConnection();
            connection.createStatement();
            String query = "select spec_name from specialization;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                specialization.add(resultSet.getString("spec_name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return specialization;
    }




//    public void doctorIDList(Map<Integer,String> idlist) {
//        try {
//
//            DatabaseConnection instance = DatabaseConnection.getInstance();
//            Connection connection = instance.getConnection();
//            connection.createStatement();
//            String query = "select doctor_id , concat(Fname , Lname) as fulname  from doctor;";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                idlist.put(resultSet.getInt("doctor_id"), resultSet.getString("fulname"));
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }


//    public void addDoctorToDatabase(Doctor doctor) {
//        this.doctor = doctor;
//        try {
//            DatabaseConnection instance = DatabaseConnection.getInstance();
//            Connection connection = instance.getConnection();
//            connection.createStatement();
//            String query = "insert into doctor(Fname, Lname, age, email, sex, phoneno, DOB,experience , position) VALUE \n" +
//                    "    ( ? , ? , ? , ? ,? , ? , ? ,? , 'Non');";
//            if (this.doctor == null) {
//            } else {
//
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1, this.doctor.getFirstname());
//                preparedStatement.setString(2, this.doctor.getLastname());
//                preparedStatement.setInt(3, this.doctor.getAge());
//                preparedStatement.setString(4, this.doctor.getEmail());
//                preparedStatement.setString(5, this.doctor.getSex());
//                preparedStatement.setString(6, this.doctor.getPhoneno());
//                preparedStatement.setDate(7, java.sql.Date.valueOf(this.doctor.getDate()));
//                preparedStatement.setString(8, this.doctor.getExperience());
//                preparedStatement.executeUpdate();
//
//                Statement statement = connection.createStatement();
//                query = "select doctor_id from doctor where email = '" + doctor.getEmail() + "'";
//
//                ResultSet resultSet = statement.executeQuery(query);
//                while (resultSet.next()) {
//                    //Retrieve by column name
//                    doctor.setDoctor_id(resultSet.getInt("doctor_id"));
//                }
//                System.out.println("doctor.getDoctor_id() = " + doctor.getDoctor_id());
//            }
//
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//    }


    // this method is use to add specialization into doctor_spec

/*    public void addDoctorSpecialization(Queue<String> queue) {
        int sid = 0;  // specializationID
        try {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            Connection connection = instance.getConnection();
            PreparedStatement statement ;
            ResultSet  resultSet;
            while (!queue.isEmpty()) {   // queue contains the what specialization doctor has.
                String query = "select specialization_id from specialization where spec_name ='" + queue.peek() + "' ";
                statement = connection.prepareStatement(query);
                resultSet =  statement.executeQuery();

                while (resultSet.next()) {
                    sid = resultSet.getInt(1);
                    System.out.println("sid = " + sid);
                }
                String query2 = "insert into doctor_specialization(doctor_id, specialization_id) VALUE ("+ doctor.doctor_id +","+sid+")" ;
                statement = connection.prepareStatement(query2);
                statement.executeUpdate();
                queue.remove();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

}
