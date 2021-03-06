package org.HMS;

import DataConnection.DatabaseConnection;
import Java.HMS.Data.Patient;
import Java.HMS.Data.Services.PatientServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@PropertySource("classpath:application.properties")  // annotation for calling propertySource.
public class PatientClassTest {

    private Patient patient;
    private PatientServices services; // depend JDBCTEMPLATE....

    private DatabaseConnection connection;

    @Before
    public void setupData() {
        patient = new Patient("hannan", "Smith", 32, "M",
                "New York", "031201254", "JohnSmith@gmail.com", "AB", LocalDate.now());
        services = new PatientServices();
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
        services.addPatientToDatabase(patient);  // database patient insert..
        Assert.assertEquals(patient.getEmail(), PatientServices.getRetrieveThroughMail());
    }

    @Test
    public void isNullReturnCheckoutsRegisteringPatient() {

        Map<Integer, String> patientMap;
        patientMap = services.confirmationPatientList();
        // closed

        dataSource();
        services.setDataSource(connection);
        services.addPatientToDatabase(patient);
        Assert.assertNull("Data was not retrieved", patientMap.get(0));
    }

    @Test
    public void RetrievingConfirmationDetailOfPatient() {
        services.addPatientToDatabase(patient);
        // closed.

        Map<Integer, String> patientMap;
        patientMap = services.confirmationPatientList();
        Assert.assertEquals(patient.getEmail(), patientMap.get(0));
        int p = patientMap.keySet().iterator().next();
        Assert.assertEquals(patient.getPatientid(), p);

    }

    @Test
    public void RetrievePatientList(){

        Patient patient =    new Patient(30036, "Abdul", "Wahab", 23
                , "M", "Jhangi Syedan", "03412547896", "ptanahi@gmail.com",
                "B+",  LocalDate.of(1998, 7, 2));

        List<Patient> patientList;

        patientList = services.retrievePatientsList();

        boolean answer = patientList.stream().anyMatch(n
                ->  n.getPatientid()==patient.getPatientid() );

        Assert.assertEquals( true , answer);

    }

}
