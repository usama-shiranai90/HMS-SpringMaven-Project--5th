package Java.HMS.Data;

import java.time.LocalDate;

public class BloodBank {

    private int bloodserialno;
    private String bloodtype;
    private LocalDate donationDate;
    private int patientid;

    public BloodBank(int bloodserialno, String bloodtype, LocalDate donationDate, int patientID) {
        this.bloodserialno = bloodserialno;
        this.bloodtype = bloodtype;
        this.donationDate = donationDate;
        patientid = patientID;
    }

    public BloodBank(int bloodserialno, String bloodtype, LocalDate donationDate) {
        this.bloodserialno = bloodserialno;
        this.bloodtype = bloodtype;
        this.donationDate = donationDate;
    }

    public int getBloodserialno() {
        return bloodserialno;
    }

    public void setBloodserialno(int bloodserialno) {
        this.bloodserialno = bloodserialno;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }
}
