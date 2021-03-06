package Java.HMS.View.Appointment;

import Java.HMS.Data.Appointment;
import Java.HMS.Data.Services.PatientServices;
import Java.HMS.View.Administrator.AdminMainpage;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@PageTitle("appointment history")
@Route(value = "Appointment-history", layout = AdminMainpage.class)
@Tag("appointment-history-view")
@JsModule("./src/JSFile/appointment-history-view.js")
public class AppointmentHistoryView extends PolymerTemplate<TemplateModel> {

    @Id("searchField")
    private TextField searchField;
    @Id("searchbutton")
    private Button searchbutton;

    private Appointment  appointment;
    private PatientServices.InnerAppointmentFields innerAppointmentFields;

    private List<Appointment> appointmentList;
    private Grid<Appointment> grid = new Grid<>(Appointment.class, false);
    private Binder<Appointment> binder = new Binder<>(Appointment.class);
    private ListDataProvider<Appointment> listDataProvider;
    @Id("patientTextField")
    private TextField patientTextField;  // fullname
    @Id("patientBloodGroupTextField")
    private TextField patientBloodGroupTextField;
    @Id("patientPhoneNumberTextField")
    private TextField patientPhoneNumberTextField;
    @Id("doctorfullname")
    private TextField doctorfullname;

    public AppointmentHistoryView(@Autowired PatientServices patientServices) {
        updateGrid(patientServices);
        listDataProvider = DataProvider.ofCollection(appointmentList);

        grid.addColumn("appointmentID").setAutoWidth(true).setHeader("appointment ID");
        grid.addColumn("patientID").setAutoWidth(true).setHeader("patient ID");
        grid.addColumn("doctorID").setAutoWidth(true).setHeader("Doctor ID");
        grid.addColumn("appointmentDate").setAutoWidth(true).setHeader("Appointment Date");
        grid.addComponentColumn(item -> createRemoveButton(grid, item))
                .setHeader("Actions");

        grid.setHeight("500px");
        grid.setDataProvider(listDataProvider);
        grid.getElement().setAttribute("slot", "grid");
        grid.addSelectionListener(e -> updateForm());
        getElement().appendChild(grid.getElement());
        grid.setDetailsVisibleOnClick(false);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue()!=null){
              innerAppointmentFields =  patientServices.getFields(appointment.getAppointmentID());

              patientTextField.setValue(innerAppointmentFields.patientname);
              doctorfullname.setValue(innerAppointmentFields.doctorname);
              patientBloodGroupTextField.setValue(innerAppointmentFields.bloodGroup);
              patientPhoneNumberTextField.setValue(innerAppointmentFields.patientPhoneNumber);

            }
        });

        searchbutton.addClickListener(event -> {

            if (searchField.getValue().equalsIgnoreCase("")){
                updateGrid(patientServices);
            }
            else if (searchField.getValue().equalsIgnoreCase("today")){
                listDataProvider.setFilter(r -> r.getAppointmentDate().equals(LocalDate.now()));
            }
            else if (searchField.getValue().equalsIgnoreCase("onwards")){
                listDataProvider.setFilter(appointment1 -> appointment1.getAppointmentDate().compareTo(LocalDate.now()) == 0)  ;
            }
            else
            listDataProvider.setFilter(room -> room.getAppointmentID()== Integer.parseInt(searchField.getValue()) );
        });

    }

    private Button createRemoveButton(Grid<Appointment> grid, Appointment item) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Send Reminder", clickEvent -> {
            ListDataProvider<Appointment> dataProvider = (ListDataProvider<Appointment>) grid
                    .getDataProvider();
            Notification.show("Reminder Send to "+ innerAppointmentFields.patientname);
            dataProvider.refreshAll();
        });
        return button;
    }

    private void updateGrid(PatientServices services){
        appointmentList = services.findAllAppointments();
        grid.setItems(appointmentList);
        setFormVisible();
    }
    private void updateForm() {
        if (!grid.asSingleSelect().isEmpty()) {
           appointment  = grid.asSingleSelect().getValue();
            binder.setBean(appointment);
        }
        setFormVisible();
    }


    private void setFormVisible() {
/*        roomNoTextField.setVisible(true);
        wardNoTextField.setVisible(true);
        statusOfRoom.setVisible(true);*/
    }

    public interface AppointmentHistoryViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
