package Java.HMS.Data;

import java.time.LocalDate;

public class DeathReport extends Patient{

    private int reportid;
    private String deathplace;
    private String deathcause;
    private LocalDate deathdate;


    public DeathReport(String firstname, String lastname, int age, String address, String phoneno, String email) {
        super(firstname, lastname, age, address, phoneno, email);
    }


    public DeathReport() {
        super();
    }

    public int getReportid() {
        return reportid;
    }

    public void setReportid(int reportid) {
        this.reportid = reportid;
    }

    public String getDeathplace() {
        return deathplace;
    }

    public void setDeathplace(String deathplace) {
        this.deathplace = deathplace;
    }

    public String getDeathcause() {
        return deathcause;
    }

    public void setDeathcause(String deathcause) {
        this.deathcause = deathcause;
    }

    public LocalDate getDeathdate() {
        return deathdate;
    }

    public void setDeathdate(LocalDate deathdate) {
        this.deathdate = deathdate;
    }




    @Override
    public String toString() {
        return String.format("%s\nReportID:%d\nDeath Place:%s\nDeath Cause:%s\nDate Of Death:%s", super.toString() , getReportid() , getDeathplace() , getDeathcause() , deathdate.toString() );
    }
}
