package Java.HMS.Data;

import java.time.LocalDate;

public class AdmittedPatient extends Patient{

    private int admitted_id;
    private int ward;
    private LocalDate admittedDate;
    private static   boolean isadmitted;


    public AdmittedPatient(int patientid,String firstname, String lastname, String sex, int admitted_id, int ward) {
//        super();
        super(patientid,firstname, lastname, sex);
        this.admitted_id = admitted_id;
        this.ward = ward;
    }

    public AdmittedPatient
            (int patientid, String firstname, String lastname,
             int age, String sex, String address, String phoneno, String email,
             String bloodgroup, LocalDate date, int admitted_id, int ward
                    , LocalDate admittedDate)
    {
        super(patientid, firstname, lastname, age, sex, address, phoneno, email, bloodgroup, date);
        this.admitted_id = admitted_id;
        this.ward = ward;
        this.admittedDate = admittedDate;
    }

    public static boolean isIsadmitted() {
        return isadmitted;
    }

    public void setIsadmitted(boolean isadmitted) {
        this.isadmitted = isadmitted;
    }

    public int getAdmitted_id() {
        return admitted_id;
    }

    public void setAdmitted_id(int admitted_id) {
        this.admitted_id = admitted_id;
    }

    public int getWard() {
        return ward;
    }

    public void setWard(int ward) {
        this.ward = ward;
    }

    public LocalDate getAdmittedDate() {
        return admittedDate;
    }

    public void setAdmittedDate(LocalDate admittedDate) {
        this.admittedDate = admittedDate;
    }

    public static Object isIsadmitted(Patient patient) {
        return patient;
    }


    @Override
    public String toString() {
        return "AdmittedPatient{" +
                "admitted_id=" + admitted_id +
                ", ward=" + ward +
                ", admittedDate=" + admittedDate +
                '}';
    }
}
