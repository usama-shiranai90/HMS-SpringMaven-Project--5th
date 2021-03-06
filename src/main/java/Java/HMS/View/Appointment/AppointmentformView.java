package Java.HMS.View.Appointment;

import Java.HMS.Data.Appointment;
import Java.HMS.Data.Patient;
import Java.HMS.Data.Services.DoctorServices;
import Java.HMS.Data.Services.PatientServices;
import Java.HMS.View.Administrator.AdminMainpage;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@PageTitle("Appointment form")
@Route(value = "appointment-form", layout = AdminMainpage.class)
@Tag("appointmentform-view")
@JsModule("./src/JSFile/appointmentform-view.js")
public class AppointmentformView extends PolymerTemplate<TemplateModel>  {

    @Id("firstName")
    private TextField firstName;
    @Id("lastName")
    private TextField lastName;
    @Id("patientage")
    private TextField patientage;
    @Id("email")
    private EmailField email;
    @Id("doctoridlist")
    private ComboBox<Integer> doctoridlist;
    @Id("doctorname")
    private TextField doctorname;
    @Id("appointmentDate")
    private DatePicker appointmentDate;
    @Id("submitbutton")
    private Button submitbutton;
    @Id("search")
    private Button search;

    private Map<Integer , String> doctorMap = new HashMap<>();

    private Patient patient;
    @Id("vaadinDetails")
    private Details vaadinDetails;
    @Id("patientid")
    private TextField patientid;

    public AppointmentformView(@Autowired PatientServices services , @Autowired DoctorServices doctorServices) {
        vaadinDetails.setSummaryText("Patient Detail");

        search.addClickListener(event -> {
            patient =  services.retrievePatientForAppointment(Integer.parseInt(patientid.getValue()));

            if (patient != null){
                vaadinDetails.setOpened(true);
                firstName.setValue(patient.getFirstname());
                lastName.setValue(patient.getLastname());
                patientage.setValue( String.valueOf(patient.getAge())   );
                email.setValue(patient.getEmail());
            }
            else {
                Notification.show("Input valid ID");
                patientid.setInvalid(true);
                patientid.setErrorMessage("input valid ID");
            }
        });

        doctorServices.doctorIDList(doctorMap);

        doctoridlist.setItems(doctorMap.keySet());

        doctoridlist.addValueChangeListener(event -> doctorname.setValue(  doctorMap.get(doctoridlist.getValue()) ));

        submitbutton.addClickListener(event -> {
            services.setAppointment(new Appointment(Integer.parseInt(patientid.getValue()), Integer.parseInt(String.valueOf(doctoridlist.getValue())) ,appointmentDate.getValue() ));

                if (getUI().isPresent()){
                    getUI().get().navigate("dashboard");
                }
        });

    }

    public interface AppointmentformViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
