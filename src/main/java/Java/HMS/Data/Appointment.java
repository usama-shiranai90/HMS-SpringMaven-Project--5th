package Java.HMS.Data;

import java.io.Serializable;
import java.time.LocalDate;


public class Appointment implements Serializable {

    private int appointmentID;
    private int patientID;
    private int doctorID;
    private LocalDate appointmentDate;



    public Appointment(int patientID, int doctorID, LocalDate appointmentDate) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(int appointmentID, int patientID, int doctorID, LocalDate appointmentDate) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
    }


    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
