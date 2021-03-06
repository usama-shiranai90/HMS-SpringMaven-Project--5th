package Java.HMS.Data.Services;


import DataConnection.DatabaseConnection;
import Java.HMS.Data.AdmittedPatient;
import Java.HMS.Data.Appointment;
import Java.HMS.Data.DeathReport;
import Java.HMS.Data.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class PatientServices {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private HashMap<Integer, String> map;
    private static String retrieveThroughMail;
    private static int patientreportid;

    public void setDataSource(DatabaseConnection dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // method for adding new patient into database
    public void addPatientToDatabase(Patient patient) {

        String query = "insert into patient(Fname, Lname, email, address, phoneno, sex, DOB, age, bloodgroup , password ) VALUE \n" +
                "    (? , ?, ?, ?, ?, ?, ?, ?, ? , ?);";

        jdbcTemplate.update(query,
                patient.getFirstname(), patient.getLastname(), patient.getEmail(),
                patient.getAddress(), patient.getPhoneno(),
                patient.getSex(), java.sql.Date.valueOf(patient.getDate()),
                patient.getAge(), patient.getBloodgroup(), patient.generateSecureRandomPassword()
        );// closed. agr ap autowired and server pr run ho raha ahai ,
        retrieveThroughMail = patient.getEmail();
    }


    // return Patients id and phone-number in confirmation page.
    public HashMap<Integer, String> confirmationPatientList() {
        try {
            map = new HashMap<>();
            String query = "select patient_id , phoneno from patient where email = '" + getRetrieveThroughMail() + "'";

            jdbcTemplate.query(
                    query, (rs, rowNum) -> map.put(rs.getInt("patient_id"), rs.getString("phoneno"))
            );
        } catch (NoSuchElementException | NullPointerException nullPointerException) {
            System.err.println(nullPointerException.getMessage());
            map.put(0, null);
            return map;
        }
        return map;
    }

    // gets the mail of patient
    public static String getRetrieveThroughMail() {
        return retrieveThroughMail;
    }

    // sets the mail of patient
    public static void setRetrieveThroughMail(String retrieveThroughMail) {
        PatientServices.retrieveThroughMail = retrieveThroughMail;
    }


    // retrieve the data of patient from database [name , age , email ] according to its id
    public DeathReport retrievePatientForDeathReport(int patientid) {

        int count = returnCount(patientid);

        if (count >=1){
            return jdbcTemplate.query(
                    "select Fname , Lname  ,age, address,phoneno, email from patient where patient_id=" + patientid + ";",
                    (rs, rowNum) -> new DeathReport(
                            rs.getString("Fname"),
                            rs.getString("Lname"),
                            rs.getInt("age"),
                            rs.getString("address"),
                            rs.getString("phoneno"),
                            rs.getString("email")
                    )).get(0);
        }
        return null;
    }


    public Patient retrievePatientForAppointment(int patientid) {
        int count = returnCount(patientid);

        if (count >= 1) {
            return jdbcTemplate.query(
                    "select patient_id ,Fname , Lname , age  , sex, address , phoneno , email , bloodgroup , DOB from patient where patient_id=" + patientid + ";",
                    (resultset, rowNum) -> new Patient(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getInt(4)
                            , resultset.getString(5), resultset.getString(6), resultset.getString(7), resultset.getString(8),
                            resultset.getString(9),
                            resultset.getDate("DOB").toLocalDate()
                    )
            ).get(0);

        }
        return null;

    }

    private int returnCount(int patientid) {
        int count;
        String q = "select count(*) from patient where patient_id=?;";
        try {
            count = jdbcTemplate.queryForObject(q, new Object[]{patientid}, Integer.class);
        } catch (DataAccessException e) {
            return 0;
        }
        return count;
    }

    public List<Patient> retrievePatientsList() {
        String query = "select patient_id ,Fname , Lname , age  , sex, address , phoneno , email , bloodgroup , DOB from patient;";

        return jdbcTemplate.query(
                query, (resultset, rowNum) ->
                        new Patient(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getInt(4)
                                , resultset.getString(5), resultset.getString(6), resultset.getString(7), resultset.getString(8),
                                resultset.getString(9),
                                resultset.getDate("DOB").toLocalDate()
                        )
        );
    }


    public List<AdmittedPatient> retrieveAdmittedPatientList() {

//        String query = "select Admitted_ID , patient_id , Fname, Lname , sex , Ward_No from patient join admitted_patients ap on patient.patient_id = ap.PatientID;";
        String query = "select Admitted_ID , patient_id , Ward_No , Admitted_Date_Time , Fname , Lname , email , address , sex , DOB ,bloodgroup , age, phoneno  from admitted_patients join patient p on p.patient_id = admitted_patients.PatientID;";

        return jdbcTemplate.query(
                query, (resultset, rowNum) ->
                        new AdmittedPatient(
                                resultset.getInt("patient_id"),
                                resultset.getString("Fname"),
                                resultset.getString("Lname"),
                                resultset.getInt("age"),
                                resultset.getString("sex"),
                                resultset.getString("address"),
                                resultset.getString("phoneno"),
                                resultset.getString("email"),
                                resultset.getString("bloodgroup"),
                                resultset.getDate("dob").toLocalDate(),
                                resultset.getInt("Admitted_ID"),
                                resultset.getInt("Ward_No"),
                                resultset.getDate("Admitted_Date_Time").toLocalDate()
                        )
        );
    }

    public void generateDeathreport(DeathReport deathReport) {

        String query = "insert into deathreport(patientid, Deathdate, DeathPlace, DeathCause) VALUE (" + deathReport.getPatientid() + "  ,? ,? ,?);";

        jdbcTemplate.update(query,
                deathReport.getDeathdate(),
                deathReport.getDeathplace(),
                deathReport.getDeathcause()
        );

    }

    public void getreportidfromdatabase(DeathReport deathReport) {

        String query = "select reportId from deathreport where patientid=" + deathReport.getPatientid() + ";";


        jdbcTemplate.query(
                query, (rs, rowNum) -> patientreportid = rs.getInt(1)
        );
        deathReport.setReportid(patientreportid);
    }

    public void setAppointment(Appointment appointment) {
        String query = "insert into appointment(doctorid, patientid, appointmentdate) value (? , ? , ?);";
        jdbcTemplate.update(query,
                appointment.getDoctorID(), appointment.getPatientID(), appointment.getAppointmentDate()
        );
    }

    public List<Appointment> findAllAppointments() {

        String query = "select appointmentid, doctorid, patientid, appointmentdate from appointment;";


        return jdbcTemplate.query(
                query, (rs, rowNum) -> new Appointment(
                        rs.getInt(1), rs.getInt(3),
                        rs.getInt(2), rs.getDate(4).toLocalDate()
                )
        );
    }


    public class InnerAppointmentFields {
        public String patientname;
        public String doctorname;
        public String bloodGroup;
        public String patientPhoneNumber;

        public InnerAppointmentFields(String patientname, String doctorname, String bloodGroup, String patientPhoneNumber) {
            this.patientname = patientname;
            this.doctorname = doctorname;
            this.bloodGroup = bloodGroup;
            this.patientPhoneNumber = patientPhoneNumber;
        }

        public InnerAppointmentFields() {
        }

    }

    public InnerAppointmentFields getFields(int appointmentID) {

        String query = "select concat(p.Fname, ' ',  p.Lname),concat(d.Fname, ' ', d.Lname) , bloodgroup, p.phoneno  from appointment join patient p on p.patient_id = " +
                "appointment.patientid join doctor d on d.doctor_id = appointment.doctorid where appointmentid =" + appointmentID;

        return jdbcTemplate.query(query, (rs, rownumber) -> new InnerAppointmentFields(rs.getString(1),
                rs.getString(2), rs.getString(3), rs.getString(4)
        )).get(0);
    }


}
