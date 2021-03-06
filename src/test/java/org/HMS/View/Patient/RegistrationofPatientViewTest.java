package org.HMS.View.Patient;

import Java.HMS.Data.Patient;
import Java.HMS.View.Patient.RegistrationofPatientView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinServlet;
import org.junit.Before;


@JsModule("./src/JSFile/registrationof-patient-view.js")
public class RegistrationofPatientViewTest extends VaadinServlet {

    @Id("firstName")
    TextField firstName;
    @Id("email")
    EmailField email;
    @Id("birthday")
    DatePicker dateOfBirth;

    @Id("pnCountryCode")
    private ComboBox<String> countryCode;

    @Id("PatientGender")
    RegistrationofPatientView phone;

    @Id("save")
    private Button save;

    private Patient patient;
    @Id("lastName")
    TextField lastName;
    @Id("patientage")
    TextField patientage;
    @Id("genderComboBox")
    ComboBox<String> genderComboBox;
    @Id("pnNumber")
    TextField pnNumber;
    @Id("bloodGroup")
    TextField bloodGroup;
    @Id("occupation")
    TextArea occupation;

    private RegistrationofPatientView view;
    @Before
    public void setupData(){
         view = new RegistrationofPatientView();


    }


}
