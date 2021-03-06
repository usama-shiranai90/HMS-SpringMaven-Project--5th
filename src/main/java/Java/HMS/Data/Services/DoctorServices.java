package Java.HMS.Data.Services;

import DataConnection.DatabaseConnection;
import Java.HMS.Data.Doctor;
import Java.HMS.Data.DoctorQualification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Queue;

@Component
public class DoctorServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Doctor doctor;
    private int sid = 0;

    public void setDataSource(DatabaseConnection dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }



    public void doctorIDList(Map<Integer, String> idlist) {
        try {
            String query = "select doctor_id , concat(Fname,' ', Lname) as fulname  from doctor;";

            jdbcTemplate.query(
                    query, (resultSet, rowNum) -> idlist.put(resultSet.getInt("doctor_id"), resultSet.getString("fulname"))
            );
        } catch (NullPointerException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addDoctorToDatabase(Doctor doctor) {
        this.doctor = doctor;
        String query = "insert into doctor(Fname, Lname, age, email, sex, phoneno, DOB,experience , position) VALUE \n" +
                "    ( ? , ? , ? , ? ,? , ? , ? ,? , 'Non');";

        jdbcTemplate.update(query,
                doctor.getFirstname(), doctor.getLastname(), doctor.getAge(),
                doctor.getEmail(), doctor.getSex(),
                doctor.getPhoneno(), java.sql.Date.valueOf(doctor.getDate()),
                doctor.getExperience()
        );

        query = "select doctor_id from doctor where email = '" + doctor.getEmail() + "'";
        final int[] value = {0};
/*        jdbcTemplate.query(
                query, (rs, rowNum) -> value[0] = rs.getInt("doctor_id")
        );
        doctor.setDoctor_id(value[0]);
        this.doctor.setDoctor_id(value[0]);*/
    }

    public void addDoctorSpecialization(Queue<String> queue) {
        while (!queue.isEmpty()) {   // queue contains the what specialization doctor has.
            String query = "select specialization_id from specialization where spec_name ='" + queue.peek() + "' ";
            jdbcTemplate.query(
                    query, (resultSet, rowNum) -> sid = resultSet.getInt(1)
            );
            String query2 = "insert into doctor_specialization(doctor_id, specialization_id) VALUE (" + doctor.getDoctor_id() + "," + sid + ")";
            jdbcTemplate.execute(query2);
            queue.remove();
        }

    }

    public String getID() {
        try {
            return String.valueOf(doctor.getDoctor_id());
        } catch (NullPointerException e) {
            return e.toString();
        }
    }

    public void addqualification(DoctorQualification qualification) {

        String query = "insert into qualification(qualification_id, qualificationname, institutename, graduationyear, doctorid) VALUE (? ,? ,? ,? , " + doctor.getDoctor_id() + " );";

        jdbcTemplate.update(query, qualification.getQualificationid(), qualification.getQualificationName(), qualification.getInstituteName(), java.sql.Date.valueOf(qualification.getGraducationyear())
        );

    }

    public List<DoctorQualification> findAll() {
        return jdbcTemplate.query(
                "select  qualificationname, institutename, graduationyear from qualification where doctorid =" + doctor.getDoctor_id() + ";",
                (rs, rowNum) -> new DoctorQualification(rs.getString("qualificationname"), rs.getString("institutename"), rs.getDate("graduationyear").toLocalDate())
        );

    }

}
