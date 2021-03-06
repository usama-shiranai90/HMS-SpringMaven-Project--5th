package org.HMS;

import DataConnection.DatabaseConnection;
import Java.HMS.Data.Doctor;
import Java.HMS.Data.Services.DoctorServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;
import java.time.LocalDate;

@PropertySource("classpath:application.properties")  // annotation for calling propertySource.
public class DoctorClassTest {

    private Doctor doctor;
    private DoctorServices services = new DoctorServices(); // depend JDBCTEMPLATE....

    private DatabaseConnection connection;


/*    public Doctor(int doctor_id, String firstname, String
            lastname, int age, String sex, String phoneno
                  LocalDate date, String email, String experience) {*/

    @Before
    public void setupData() {
/*        doctor = new Doctor(122, "Prof.Dr.Javed", "Akram",
                72, "M", "+9231042961", LocalDate.of(1948, 02, 24)
                , "Pro.akram1920@gmail.com", "40");*/

        doctor = new Doctor("Prof.Akhtar", "Javaid",
                44, "M", "+9231321321", LocalDate.of(1960, 12, 24)
                , "Profess.Akhtar@gmail.com", "16");


        services = new DoctorServices();
        dataSource();
        services.setDataSource(connection);

    }

    public void dataSource() {
        try {
            connection = DatabaseConnection.getInstance();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void IsInsertionIntoDatabase() {
        services.addDoctorToDatabase(doctor);  // database patient insert..
        Assert.assertEquals(doctor.getDoctor_id(), Integer.parseInt(services.getID()));
    }


}
