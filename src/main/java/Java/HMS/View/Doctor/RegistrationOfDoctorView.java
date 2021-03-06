package Java.HMS.View.Doctor;

import Java.HMS.Data.Doctor;
import Java.HMS.Data.Services.DoctorServices;
import Java.HMS.View.Administrator.AdminMainpage;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


@Route(value = "doctor-form", layout = AdminMainpage.class)
@PageTitle("Doctor Registration")
@Tag("registration-of-doctor-view")
@JsModule("./src/JSFile/registration-of-doctor-view.js")
public class RegistrationOfDoctorView extends PolymerTemplate<RegistrationOfDoctorView.RegistrationOfDoctorViewModel> {


    @Id("specializationlist")
    private ComboBox<String> specializationlist;

    @Id("add_specialization")
    private Button add_specialization;


    @Id("save")
    private Button save;
    @Id("firstname")
    private TextField firstname;
    @Id("lastname")
    private TextField lastname;
    @Id("doctorage")
    private TextField doctorage;
    @Id("doctormail")
    private EmailField doctormail;
    @Id("genderComboBox")
    private ComboBox<String> genderComboBox;
    @Id("birthday")
    private DatePicker birthday;
    @Id("doctorexperience")
    private TextField doctorexperience;
    @Id("pnNumber")
    private TextField pnNumber;
    @Id("pnCountryCode")
    private ComboBox<String> pnCountryCode;
    @Id("doctorGender")
    private PhoneNumberField phone;

    private Doctor doctor;
    private List<String> specialization;
    private Queue<String> queue;
    @Id("radioTwo")
    private Input radioTwo;

    public RegistrationOfDoctorView(@Autowired DoctorServices autowire_doctor , @Autowired Doctor doc) {

        radioTwo.addValueChangeListener(event -> getUI().get().navigate("doctor-qualification"));

        addCountryCodeandGender();
        validationFieldChecks();
        queue = new PriorityQueue<>();
        add_specialization.setIcon(new Icon(VaadinIcon.PLUS));
        specialization = doc.generalizations();                         // retrieving specialization from database storing reference to List.
        specializationlist.setItems(specialization);                                // setting List to ComboBox
        add_specialization.addClickListener(this::showButtonClickedMessage);        // calls methods to add specialization into queue and showing notification

        save.addClickListener(event -> {
            doctor = new Doctor(
                    firstname.getValue()  , lastname.getValue()  , Integer.parseInt( doctorage.getValue()) , genderComboBox.getValue(),
                    pnCountryCode.getValue()+" "+pnNumber.getValue(), birthday.getValue()
                    , doctormail.getValue(), doctorexperience.getValue()                    // Doctor Object
            );
            autowire_doctor.addDoctorToDatabase(doctor);                            // adding Doctor detail into database.
            autowire_doctor.addDoctorSpecialization(queue);                         // passing list of specialization which doctor has chosen.

            getUI().get().navigate("doctor-qualification");
        });

    }

    private void validationFieldChecks() {
        firstname.setRequired(true);
        firstname.setMinLength(5);
        firstname.setMaxLength(40);
        firstname.setErrorMessage("Character range between " + firstname.getMinLength() + " to " + lastname.getMaxLength());
        lastname.setRequired(true);
        lastname.setMinLength(5);
        lastname.setMaxLength(40);
        lastname.setErrorMessage("Character range between " + firstname.getMinLength() + " to " + lastname.getMaxLength());
        doctorage.setMinLength(1);
        doctorage.setMaxLength(150);
        birthday.setRequired(true);
        birthday.setMax(LocalDate.now());
        doctormail.setInvalid(true);
        doctormail.setValue("example123@gmail.com");
        doctormail.setErrorMessage("insert valid mail");


    }
    public void addCountryCodeandGender(){
        genderComboBox.setItems("M", "F", "Transgender");
        genderComboBox.addCustomValueSetListener(
                e -> genderComboBox.setValue(e.getDetail()));

        pnCountryCode.setItems("+354", "+92", "+62", "+98", "+964", "+353",
                "+44", "+972", "+39", "+225");
        pnCountryCode.addCustomValueSetListener(
                e -> pnCountryCode.setValue(e.getDetail()));
        phone.setForm(this);
    }

    private void showButtonClickedMessage(ClickEvent<Button> buttonClickEvent) {
        if (!specializationlist.getValue().equals("")){
            queue.add(specializationlist.getValue());
            Notification.show("Added " + specializationlist.getValue());
        }

    }



    private static class PhoneNumberField extends CustomField<String> {
        private RegistrationOfDoctorView form;

        @Override
        protected String generateModelValue() {
            if (getCountryCode().getValue() != null && getNumber().getValue() != null) {
                return getCountryCode().getValue() + " " + getNumber().getValue();
            }
            return "";
        }

        @Override
        protected void setPresentationValue(String phoneNumber) {
            String[] parts = phoneNumber != null ? phoneNumber.split(" ", 2)
                    : new String[0];
            if (parts.length == 1) {
                getCountryCode().clear();
                getNumber().setValue(parts[0]);
            } else if (parts.length == 2) {
                getCountryCode().setValue(parts[0]);
                getNumber().setValue(parts[1]);
            } else {
                getCountryCode().clear();
                getNumber().clear();
            }
        }

        public void setForm(RegistrationOfDoctorView form) {
            this.form = form;
        }

        private TextField getNumber() {
            return form.pnNumber;
        }

        private ComboBox<String> getCountryCode() {
            return form.pnCountryCode;
        }
    }

    public interface RegistrationOfDoctorViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
