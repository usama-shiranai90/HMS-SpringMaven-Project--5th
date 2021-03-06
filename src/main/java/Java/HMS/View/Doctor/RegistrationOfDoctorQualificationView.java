package Java.HMS.View.Doctor;

import Java.HMS.Data.DoctorQualification;
import Java.HMS.Data.Services.DoctorServices;
import Java.HMS.View.Administrator.AdminMainpage;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * A Designer generated component for the registration-of-doctor-qualification-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */

@Route(value = "doctor-qualification", layout = AdminMainpage.class)
@PageTitle("qualificationOfDoctor")
@Tag("registration-of-doctor-qualification-view")
@JsModule("./src/JSFile/registration-of-doctor-qualification-view.js")
public class RegistrationOfDoctorQualificationView extends PolymerTemplate<TemplateModel> {

    @Id("firstpanel")
    private AccordionPanel firstpanel;
    @Id("thirdpanel")
    private AccordionPanel thirdpanel;
    @Id("secondpanel")
    private AccordionPanel secondpanel;
    @Id("fifthpanel")
    private AccordionPanel fifthpanel;
    @Id("fourthpanel")
    private AccordionPanel fourthpanel;

    @Id("matric_qual_name")
    private TextField matric_qual_name;
    @Id("matric_institue_name")
    private TextField matric_institue_name;
    @Id("matric_date")
    private DatePicker matric_date;
    @Id("interdemidate_qual_name")
    private TextField interdemidate_qual_name;
    @Id("interdemidate_inst_name")
    private TextField interdemidate_inst_name;
    @Id("interdemidate_date")
    private DatePicker interdemidate_date;
    @Id("undergrad_qual_name")
    private TextField undergrad_qual_name;
    @Id("undergrad_inst_name")
    private TextField undergrad_inst_name;
    @Id("undergrad_date")
    private DatePicker undergrad_date;
    @Id("master_inst_name")
    private TextField master_inst_name;
    @Id("master_date")
    private DatePicker master_date;
    @Id("master_qual_name")
    private TextField master_qual_name;

    private Grid<DoctorQualification> qualificationtable = new Grid<>(DoctorQualification.class, false);

    private List<DoctorQualification> qualificationList = new ArrayList<>();
    private Binder<DoctorQualification> binder = new Binder<>(DoctorQualification.class);

    DoctorQualification qualification;
    @Id("inter_addbutton")
    private Button inter_addbutton;
    @Id("matric_addbutton")
    private Button matric_addbutton;
    @Id("under_addbutton")
    private Button under_addbutton;
    @Id("master_addbutton")
    private Button master_addbutton;

    @Id("radioTwo")
    private Input radioTwo;
    @Id("save")
    private Button save;

    public RegistrationOfDoctorQualificationView(@Autowired DoctorServices services) {

        radioTwo.addValueChangeListener(event -> getUI().get().navigate("doctor-form"));


        firstpanel.setSummaryText("Matriculation Education");
        secondpanel.setSummaryText("Intermediate Education");
        thirdpanel.setSummaryText("UnGraduation Education");
        fourthpanel.setSummaryText("Masters Education");
        fifthpanel.setSummaryText("Other");

        qualificationtable.addColumn("qualificationName").setAutoWidth(true);
        qualificationtable.addColumn("instituteName").setAutoWidth(true);
        qualificationtable.addColumn("graducationyear").setAutoWidth(true);
        qualificationtable.setHeight("350px");

        qualificationtable.getElement().setAttribute("slot", "qualificationtable");
        getElement().appendChild(qualificationtable.getElement());
        qualificationtable.addSelectionListener(e -> updateForm());
        qualificationtable.setDetailsVisibleOnClick(false);

        matric_addbutton.addClickListener(event -> {
            Notification.show("ID WILL BE SHWON HERE" + services.getID());
            services.addqualification(new DoctorQualification(matric_qual_name.getValue(),
                    matric_institue_name.getValue(),
                    matric_date.getValue()));
            updateGrid(services);
        });

        inter_addbutton.addClickListener(event -> {
            Notification.show("ID WILL BE SHWON HERE" + services.getID());
            services.addqualification(new DoctorQualification(interdemidate_qual_name.getValue(),
                    interdemidate_inst_name.getValue(),
                    interdemidate_date.getValue()));
            updateGrid(services);
        });

        under_addbutton.addClickListener(event -> {
            Notification.show("ID WILL BE SHWON HERE" + services.getID());
            services.addqualification(new DoctorQualification(undergrad_qual_name.getValue(),
                    undergrad_inst_name.getValue(),
                    undergrad_date.getValue()));
            updateGrid(services);
        });

        master_addbutton.addClickListener(event -> {
            Notification.show("ID WILL BE SHWON HERE" + services.getID());
            services.addqualification(new DoctorQualification(master_qual_name.getValue(),
                    master_inst_name.getValue(),
                    master_date.getValue()));
            updateGrid(services);
        });
        qualificationtable.setDetailsVisibleOnClick(false);
        if (save.getUI().isPresent()){
            save.getUI().get().navigate("dashboard");
        }


    }

    private void updateGrid(DoctorServices service) {
        qualificationList = service.findAll();
        qualificationtable.setItems(qualificationList);
        setFormVisible();
    }

    private void updateForm() {
        if (!qualificationtable.asSingleSelect().isEmpty()) {
            qualification = qualificationtable.asSingleSelect().getValue();
            binder.setBean(qualification);
        }
        setFormVisible();
    }


    private void setFormVisible() {

    }

    public interface RegistrationOfDoctorQualificationViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
