package Java.HMS.View.Patient;

import Java.HMS.Data.AdmittedPatient;
import Java.HMS.Data.Patient;
import Java.HMS.Data.Services.PatientServices;
import Java.HMS.View.Administrator.AdminMainpage;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.apache.commons.lang3.Streams;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Route(value = "patient-list", layout = AdminMainpage.class)
@Tag("patientlist-view")
@JsModule("./src/JSFile/patientlist-view.js")
@PageTitle("List of Patient")

public class PatientlistView extends PolymerTemplate<TemplateModel> {
    @Id
    private Button cancel;
    @Id
    private Button save;

    @Id("age")
    private TextField age;
    @Id("gender")
    private TextField gender;
    @Id("address")
    private TextField address;
    @Id("patientid")
    private TextField patientid;
    @Id("fullname")
    private TextField fullname;
    @Id("date")
    private DatePicker date;
    @Id("topLayout")
    private HorizontalLayout topLayout;
    @Id("gridDivior")
    private VerticalLayout gridDivior;
    @Id("phoneno")
    private TextField phoneno;
    @Id("email")
    private TextField email;

    // admitted patient fields.
    private TextField admitID;
    private TextField wardno;
    private TextField bloodgroup;
    private DatePicker admitDate;

    private List<Patient> patientList;
    private List<AdmittedPatient> admittedPatientList;
    private Patient patient;
    private Grid<Patient> grid;
    private Grid<AdmittedPatient> admittedPatientGrid;
    private AdmittedPatient admittedPatient;
    private TextField filter;
    private Button searchbutton;
    private Binder<Patient> binder;
    private ListDataProvider<Patient> listDataProvider;
    private ListDataProvider<AdmittedPatient> admittedPatientListDataProvider;
    @Id("lay")
    private FormLayout lay;

    public PatientlistView(@Autowired PatientServices services) {
        topLayout.add(createTopBar());
        patientList = new ArrayList<>();
        admittedPatientList = new ArrayList<>();

        patientList = services.retrievePatientsList();  // store the list of patient inside patientlist.
        admittedPatientList = services.retrieveAdmittedPatientList();   // stores the list of admittedpatient inside addmittedpatientList

        // defining grid column
        grid = new Grid<>(Patient.class, false);
        grid.addColumn(Patient::getPatientid).setAutoWidth(true).setHeader("Patient ID").setFooter("Total Patients= " + patientList.size());
        grid.addColumn("fullname").setAutoWidth(true).setHeader("Full-Name");
        grid.addColumn("sex").setAutoWidth(true).setHeader("Gender");

        long averageOfAge = Math.round(patientList.stream().mapToInt(Patient::getAge).average().orElse(0));
        grid.addColumn(Patient::getAge).setAutoWidth(true).setHeader("Age").setFooter("Average age =" + (averageOfAge));

        admittedPatientGrid = new Grid<>(AdmittedPatient.class, false);

        patientList.forEach(System.out::println);

        admittedPatientGrid.addColumn("patientid").setAutoWidth(true).setHeader("Patient ID");
        admittedPatientGrid.addColumn("admitted_id").setAutoWidth(true).setHeader("Admitted ID");
        admittedPatientGrid.addColumn("fullname").setAutoWidth(true).setHeader("Full-Name");
        admittedPatientGrid.addColumn(AdmittedPatient::getAdmittedDate).setAutoWidth(true).setHeader("Admitted Date");
        admittedPatientGrid.addColumn("ward").setAutoWidth(true).setHeader("Ward No");


        // List proivder.
        listDataProvider = DataProvider.ofCollection(patientList);
        admittedPatientListDataProvider = DataProvider.ofCollection(admittedPatientList);

        grid.setHeight("270px");

        grid.setDetailsVisibleOnClick(false);
        gridDivior.add(grid, admittedPatientGrid);
        grid.setDetailsVisibleOnClick(false);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        grid.getElement().setAttribute("slot", "grid");
//        grid.setItems(patientList);
        grid.setDataProvider(listDataProvider);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        getElement().appendChild(grid.getElement());

        binder = new Binder<>(Patient.class);
        grid.asSingleSelect().addValueChangeListener(event -> {

            hideextraFields(false);

            if (event.getValue() != null) {
                Optional<Patient> persondetail = Optional.ofNullable(event.getValue());
                patient = persondetail.get();
                if (persondetail.isPresent()) {
                    populateForm(persondetail.get());
                } else {
                    refreshGrid();
                }

                patientid.setValue(String.valueOf(event.getValue().getPatientid()));
                fullname.setValue(event.getValue().getFullname());
                age.setValue(Integer.toString(event.getValue().getAge()));
                gender.setValue(event.getValue().getSex());
                email.setValue(event.getValue().getEmail());
                address.setValue(event.getValue().getAddress());
                phoneno.setValue(event.getValue().getPhoneno());
                date.setValue(event.getValue().getDate());

            } else {
                clearForm();
            }
        });
        grid.setDetailsVisibleOnClick(false);


        admittedPatientGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        admittedPatientGrid.getElement().setAttribute("slot", "admittedPatientGrid");
        admittedPatientGrid.setItems(admittedPatientList);
        getElement().appendChild(admittedPatientGrid.getElement());

        extraFieldsOfAdmittedPatient();

        admittedPatientGrid.asSingleSelect().addValueChangeListener(event -> {
            hideextraFields(true);
            if (event.getValue() != null) {
                Optional<AdmittedPatient> optionalAdmittedPatient = Optional.ofNullable(event.getValue());
                admittedPatient = optionalAdmittedPatient.get();
                if (optionalAdmittedPatient.isPresent()) {
                    populateForm(optionalAdmittedPatient.get());
                } else {
                    refreshGrid();
                }

                patientid.setValue(String.valueOf(event.getValue().getPatientid()));
                fullname.setValue(event.getValue().getFullname());
                age.setValue(Integer.toString(event.getValue().getAge()));
                gender.setValue(event.getValue().getSex());
                address.setValue(event.getValue().getAddress());
                email.setValue(event.getValue().getEmail());
                phoneno.setValue(event.getValue().getPhoneno());
                admitID.setValue(Integer.toString(event.getValue().getAdmitted_id()));
                wardno.setValue(Integer.toString(event.getValue().getWard()));
                admitDate.setValue(event.getValue().getAdmittedDate());
                bloodgroup.setValue(event.getValue().getBloodgroup());


            } else {
                clearForm();
            }
        });
        admittedPatientGrid.setDetailsVisibleOnClick(false);
        save.addClickListener(e -> {

            if (this.patient == null) {
                String[] name = fullname.getValue().split(" ", 2);
                patientList.add(new Patient(Integer.parseInt(patientid.getValue()), name[0], name[1], Integer.parseInt(age.getValue()), gender.getValue(), address.getValue(), phoneno.getValue(), patient.getEmail(), patient.getBloodgroup(), date.getValue()));
            }
            grid.setItems(patientList);
            getElement().appendChild(grid.getElement());
            refreshGrid();
            Notification.show("Person details stored.");
        });


        searchbutton.addClickListener(event -> {

            ;

            if (pattern.matcher(filter.getValue()).matches()) {
                listDataProvider.setFilter(person -> person.getAge() == Integer.parseInt(filter.getValue()));
            } else {
                listDataProvider.setFilter(patient1 -> patient1.getFullname().equals(filter.getValue()));
            }

        });

    }

    private void extraFieldsOfAdmittedPatient() {

        admitID = new TextField("Admitted ID");
        wardno = new TextField("Ward No");
        bloodgroup = new TextField("Blood Group");
        admitDate = new DatePicker("Admitted Date");
        admitID.setVisible(false);
        wardno.setVisible(false);
        bloodgroup.setVisible(false);
        admitDate.setVisible(false);
        lay.add(admitID, wardno, bloodgroup, admitDate);
    }

    private void hideextraFields(boolean b){
        admitID.setVisible(b);
        wardno.setVisible(b);
        bloodgroup.setVisible(b);
        admitDate.setVisible(b);
//        lay.setVisible(b);

    }

    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");


    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Patient value) {
        this.patient = value;
        binder.readBean(this.patient);
    }

    public HorizontalLayout createTopBar() {
        filter = new TextField();
        filter.setPlaceholder("Filter name, age , gender. Insert the information here");
        // Apply the filter to grid's data provider. TextField value is never

        // A shortcut to focus on the textField by pressing ctrl + F
        filter.addFocusShortcut(Key.KEY_F, KeyModifier.CONTROL);

        searchbutton = new Button("Search");
        // Setting theme variant of new production button to LUMO_PRIMARY that
        // changes its background color to blue and its text color to white
        searchbutton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        searchbutton.setIcon(VaadinIcon.PLUS_CIRCLE.create());
        // A shortcut to click the new product button by pressing ALT + N
        searchbutton.addClickShortcut(Key.KEY_N, KeyModifier.ALT);
        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(filter);
        topLayout.add(searchbutton);
        topLayout.setVerticalComponentAlignment(FlexComponent.Alignment.START, filter);
        topLayout.expand(filter);
        return topLayout;
    }

/*    public static void getpatient(List<Patient> list) {

        try {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            Connection connection = instance.getConnection();
            connection.createStatement();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("select patient_id, Fname, Lname, email, password, address, phoneno, sex, DOB, age from patient;");
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                Patient patient = new Patient(resultset.getInt("patient_id"), resultset.getString("Fname"), resultset.getString("Lname"), resultset.getInt("age")
                        , resultset.getString("sex"), resultset.getString("address"), resultset.getString("phoneno"), resultset.getDate("DOB").toLocalDate());
                list.add(patient);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/
/*    public static void getadmittedpatient(List<AdmittedPatient> list) {

        try {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            Connection connection = instance.getConnection();
            connection.createStatement();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("select Admitted_ID , patient_id , Fname, Lname , sex , Ward_No from patient join admitted_patients ap on patient.patient_id = ap.PatientID;");
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                AdmittedPatient patient = new AdmittedPatient(
                        resultset.getInt("patient_id"),
                        resultset.getString("Fname"),
                        resultset.getString("Lname"),
                        resultset.getString("sex"),
                        resultset.getInt("Admitted_ID"),
                        resultset.getInt("Ward_No")
                );

                list.add(patient);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

}
