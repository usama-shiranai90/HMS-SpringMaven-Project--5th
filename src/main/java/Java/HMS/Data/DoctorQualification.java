package Java.HMS.Data;

import java.time.LocalDate;
import java.util.Random;

public class DoctorQualification extends Doctor {

    private int qualificationid;
    private String qualificationName;
    private String instituteName;
    private LocalDate graducationyear;


    public DoctorQualification(int doctor_id, String qualificationName, String instituteName, LocalDate graducationyear) {
        super(doctor_id);
        this.qualificationName = qualificationName;
        this.instituteName = instituteName;
        this.graducationyear = graducationyear;

    }

    public DoctorQualification(String qualificationName, String instituteName, LocalDate graducationyear) {
        this.qualificationName = qualificationName;
        this.instituteName = instituteName;
        this.graducationyear = graducationyear;
    }


    public int getQualificationid() {
        qualificationid = 10000 + new Random().nextInt(90000);
        return qualificationid;
    }

    public void setQualificationid(int qualificationid) {
        this.qualificationid = qualificationid;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public LocalDate getGraducationyear() {
        return graducationyear;
    }

    public void setGraducationyear(LocalDate graducationyear) {
        this.graducationyear = graducationyear;
    }



}
