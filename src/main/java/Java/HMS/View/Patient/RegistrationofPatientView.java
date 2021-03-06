package Java.HMS.View.Patient;

import Java.HMS.Data.Patient;
import Java.HMS.Data.Services.PatientServices;
import Java.HMS.View.Administrator.AdminMainpage;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@PageTitle("Registration of Patient")
@Route(value = "patient-form", layout = AdminMainpage.class)
@Tag("registrationof-patient-view")
@JsModule("./src/JSFile/registrationof-patient-view.js")

public class RegistrationofPatientView extends PolymerTemplate<TemplateModel> {

    @Id("firstName")
    TextField firstName;
    @Id("email")
    EmailField email;
    @Id("birthday")
    DatePicker dateOfBirth;

    @Id("pnCountryCode")
    private ComboBox<String> countryCode;

    @Id("PatientGender")
    PhoneNumberField phone;

    @Id("save")
    private Button save;

    @Id("lastName")
    TextField lastName;

    @Id("genderComboBox")
    ComboBox<String> genderComboBox;
    @Id("pnNumber")
    TextField pnNumber;
    @Id("bloodGroup")
    TextField bloodGroup;
    @Id("occupation")
    TextArea occupation;

    private Patient patient;
//    private Binder<Person> binder = new Binder(Person.class);

    @Autowired
    PatientServices data;
    @Id("patientage")
    private NumberField patientage;

    public RegistrationofPatientView() {
        choosegenderComboBox();
        chooseCountryCode();
        phone.setForm(this);
        validationFieldChecks();
        save.addClickListener(this::addClickListener);

//            long years = ChronoUnit.YEARS.between(dateOfBirth.getValue(), LocalDate.now());


    }

    private void validationFieldChecks() {
        firstName.setRequired(true);
        firstName.setMinLength(3);
        firstName.setMaxLength(40);
        firstName.setErrorMessage("Character range between " + firstName.getMinLength() + " to " + firstName.getMaxLength());
        lastName.setRequired(true);
        lastName.setMinLength(3);
        lastName.setMaxLength(40);
        lastName.setErrorMessage("Character range between " + lastName.getMinLength() + " to " + lastName.getMaxLength());
        patientage.setMin(1);
        patientage.setMax(150);
        dateOfBirth.setRequired(true);
        dateOfBirth.setMax(LocalDate.now());

    }

    private void addClickListener(ClickEvent<Button> buttonClickEvent) {

        patient = new Patient(firstName.getValue(), lastName.getValue(),
                patientage.getValue().intValue(),
                genderComboBox.getValue(),
                occupation.getValue(),
                countryCode.getValue() + " " + pnNumber.getValue(),
                email.getValue(), bloodGroup.getValue(), dateOfBirth.getValue());

        data.addPatientToDatabase(patient);
        if (getUI().isPresent()) {
            Notification.show("Person details stored.");
            getUI().get().navigate("patient-confirmation");
        } else {
            Notification.show("Insertion error");
        }
    }

    private void chooseCountryCode() {
        countryCode.setItems("+354", "+92", "+62", "+98", "+964", "+353",
                "+44", "+972", "+39", "+225");
        countryCode.addCustomValueSetListener(
                e -> countryCode.setValue(e.getDetail()));
    }

    private void choosegenderComboBox() {
        genderComboBox.setItems("M", "F", "Transgender");
        genderComboBox.addCustomValueSetListener(
                e -> genderComboBox.setValue(e.getDetail()));
    }

    private static class PhoneNumberField extends CustomField<String> {
        private RegistrationofPatientView form;

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

        public void setForm(RegistrationofPatientView form) {
            this.form = form;
        }

        private TextField getNumber() {
            return form.pnNumber;
        }

        private ComboBox<String> getCountryCode() {
            return form.countryCode;
        }
    }


}
