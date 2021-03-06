package Java.HMS.Data;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class HospitalRecord implements Serializable {

    private String firstname;
    private String lastname;
    private int age;
    private String sex;
    private String email;
    private String fullname;

    public HospitalRecord() {
    }
    public HospitalRecord(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        fullname = firstname + " " + lastname;
    }

    public HospitalRecord(String firstname, String lastname, int age, String sex) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.sex = sex;
        fullname = firstname + " " + lastname;
    }


    public HospitalRecord(String firstname, String lastname,String email, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        fullname = firstname + " " + lastname;
    }


    public HospitalRecord(String firstname, String lastname, int age, String sex, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.sex = sex;
        this.email = email;
        fullname = firstname + " " + lastname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return String.format("FirstName= %s \nLastName= %s\nFullName= %s \nAge= %s \nSex=%s \nEmail =%s\n", firstname, lastname, fullname, age,sex, email);
    }
}
