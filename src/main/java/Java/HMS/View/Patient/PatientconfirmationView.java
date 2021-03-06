package Java.HMS.View.Patient;

import Java.HMS.Data.Services.PatientServices;
import Java.HMS.View.Administrator.AdminMainpage;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Set;


@Route(value = "patient-confirmation", layout = AdminMainpage.class)
@Tag("patientconfirmation-view")
@PageTitle("Registration of Patient")

@JsModule("./src/JSFile/patientconfirmation-view.js")
public class PatientconfirmationView extends PolymerTemplate<TemplateModel>  {

    private MemoryBuffer memoryBuffer;

    @Id("patientimage")
    private Div patientimage;
    @Id("uploadpic")
    private Upload uploadpic;
    @Id("patientID")
    private TextField patientID;
    @Id("phoneNo")
    private TextField phoneNo;
    @Id("home")
    private Button home;

    public PatientconfirmationView(@Autowired PatientServices information) {

        Icon addpicture = new Icon(VaadinIcon.CAMERA);
        addpicture.setSize("80px");
        addpicture.setColor("LIGHT_GRAY");
        patientimage.add(addpicture);
        uploadpic.addFailedListener(event -> {
            Notification.show("Error has occured");
        });

        HashMap<Integer , String> patientList = information.confirmationPatientList();

/*        if (patientList.containsValue(null)|| patientList.get(0)==null){
            getUI().get().navigate("dashboard");
            NativeButton buttonInside = new NativeButton(" Go Back to Patient Registration");
            Notification.show("Notification generted as no patient record was inserted , "+ buttonInside).setPosition(Notification.Position.MIDDLE);
        }*/
        Set<Integer> patient1 = patientList.keySet();
        patientID.setValue(String.valueOf( patient1.iterator().next() ));
        phoneNo.setValue(patientList.get(patient1.iterator().next()));
        home.addClickListener(event -> {
            getUI().get().navigate("dashboard");
        });

    }

    public interface PatientconfirmationViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
