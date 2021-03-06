package Java.HMS.View.ResourceManagement.DeathReport;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import Java.HMS.View.Administrator.AdminMainpage;
import Java.HMS.Data.DeathReport;
import Java.HMS.Data.Services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Designer generated component for the deathreport-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */

@PageTitle("Death Report")
@Route(value = "deathreport-form", layout = AdminMainpage.class)
@Tag("deathreport-view")
@JsModule("./src/JSFile/deathreport-view.js")
public class DeathreportView extends PolymerTemplate<TemplateModel> {


    @Id("patientid")
    private TextField patientid;
    @Id("search")
    private Button search;
    @Id("vaadinDetails")
    private Details vaadinDetails;
    @Id("firstName")
    private TextField firstName;
    @Id("lastName")
    private TextField lastName;
    @Id("patientage")
    private TextField patientage;
    @Id("email")
    private EmailField email;
    @Id("phoneNumber")
    private TextField phoneNumber;
    @Id("address")
    private TextArea address;
    @Id("deathPlace")
    private TextField deathPlace;
    @Id("deathCause")
    private TextField deathCause;
    @Id("dateOfDeath")
    private DatePicker dateOfDeath;
    @Id("generate")
    private Button generate;


    private DeathReport patientdeathreport = new DeathReport();


    private PatientPDF patientPDF ;


    public DeathreportView(@Autowired PatientServices services) {

        vaadinDetails.setSummaryText("Patient Information will be shown here...");
        search.addClickListener(event -> {
            patientdeathreport = services.retrievePatientForDeathReport(Integer.parseInt(patientid.getValue()));

            if (patientdeathreport != null) {
                vaadinDetails.setOpened(true);
                firstName.setValue(patientdeathreport.getFirstname());
                lastName.setValue(patientdeathreport.getLastname());
                patientage.setValue(String.valueOf(patientdeathreport.getAge()));
                email.setValue(patientdeathreport.getEmail());
                phoneNumber.setValue(patientdeathreport.getPhoneno());
                address.setValue(patientdeathreport.getAddress());
            }

            else {
                Notification.show("Input valid ID");
                patientid.setInvalid(true);
                patientid.setErrorMessage("input valid ID");
            }

        });

        generate.addClickListener(event -> {
            if (!(deathCause.getValue() == null || deathPlace.getValue() == null || dateOfDeath.getValue() == null)) {
                patientdeathreport.setPatientid(Integer.parseInt(patientid.getValue()));
                patientdeathreport.setDeathcause(deathCause.getValue());
                patientdeathreport.setDeathplace(deathPlace.getValue());
                patientdeathreport.setDeathdate(dateOfDeath.getValue());
                services.generateDeathreport(patientdeathreport);

                if (patientdeathreport != null){
                    // PDF creation
                    services.getreportidfromdatabase(patientdeathreport);
                    patientPDF = new PatientPDF(String.format("%sDeathReport.pdf", patientdeathreport.getFirstname()+" "+ patientdeathreport.getLastname()), patientdeathreport);

                    Notification.show("Generated");
                }
                  else {
                    Notification.show("Input valid ID");
                    patientid.setInvalid(true);
                    patientid.setErrorMessage("input valid ID");
                }

            } else {
                Notification.show("Complete all blocks.");
            }
        });


    }


    public interface DeathreportViewModel extends TemplateModel {

    }
}
