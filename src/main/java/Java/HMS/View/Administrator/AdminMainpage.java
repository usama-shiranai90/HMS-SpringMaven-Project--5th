package Java.HMS.View.Administrator;

import Java.HMS.View.Appointment.AppointmentHistoryView;
import Java.HMS.View.Appointment.AppointmentformView;
import Java.HMS.View.BloodBank.BloodbankView;
import Java.HMS.View.Doctor.RegistrationOfDoctorView;
import Java.HMS.View.Patient.PatientlistView;
import Java.HMS.View.Patient.RegistrationofPatientView;
import Java.HMS.View.ResourceManagement.AdminDashboardView;
import Java.HMS.View.ResourceManagement.DeathReport.DeathreportView;
import Java.HMS.View.RoomsManagement.AvailabilityOfRoomView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.Optional;

@Route("MainPage")
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/cssview/adminhomescreen-view.css")
public class AdminMainpage extends AppLayout {

    private final Tabs menu;
    private H1 viewTitle;

    private static int count = 1;
    public AdminMainpage() {

        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));

    }


    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1("Hospital Management System");
        layout.add(viewTitle);

        ProgressBar progressBar = new ProgressBar();
        progressBar.setValue(0.345);
        progressBar.setId("progressbar");

        Icon pb_status = new Icon(VaadinIcon.LIST_UL);
        pb_status.setId("option");

        Icon alarmlogo = new Icon(VaadinIcon.ALARM);
       alarmlogo.setColor("7979EC");
        alarmlogo.getStyle().set("cursor", "Pointer");
        alarmlogo.addClickListener(event -> Notification.show("Notification will be shown"));

        Icon messagelogo = new Icon(VaadinIcon.ENVELOPE);
        messagelogo.getStyle().set("cursor", "Pointer");
        messagelogo.addClickListener(event -> Notification.show("Messages will be shown"));

        Icon detaillogo = new Icon(VaadinIcon.QUESTION_CIRCLE_O);
        detaillogo.getStyle().set("cursor", "Pointer");
        detaillogo.addClickListener(event -> Notification.show("Details will be shown"));

//        Anchor logout = new Anchor("Login","Logout");

        HorizontalLayout iconlayout = new HorizontalLayout(detaillogo , messagelogo,alarmlogo);
        iconlayout.setId("icons");
        layout.add(pb_status,progressBar,iconlayout);
        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Image image = new Image("../Images/logo3.jpg", "DummyImage");
        image.setSizeFull();
        logoLayout.add(image);
        MenuBar bar  = menuBar();

        layout.add(logoLayout, menu , bar);
        return layout;
    }

    private MenuBar menuBar(){
        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);
        Text selected = new Text("");

        Icon usericon = new Icon(VaadinIcon.USER_CHECK);
        Icon location = new Icon(VaadinIcon.LOCATION_ARROW);
        Icon signouticon = new Icon(VaadinIcon.SIGN_OUT);


        MenuItem account = menuBar.addItem("profile");
        account.addComponentAsFirst(usericon);

        menuBar.addItem(location,e ->getUI().ifPresent(event ->{
            event.navigate("hospital-location");

        }));


        menuBar.addItem("logout" , event ->getUI().ifPresent(e ->{
            e.navigate("");
        })).addComponentAsFirst(signouticon) ;


        account.getSubMenu().addItem("Edit Profile",
                e -> selected.setText("Edit Profile"));
        account.getSubMenu().addItem("Privacy Settings",
                e -> selected.setText("Privacy Settings"));
        return menuBar;
    }


    private Tabs createMenu() {
        final Tabs tabs = new Tabs();

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        Component[] list = createMenuItems();
        tabs.add(list);
        return tabs;
    }

  private Component[] createMenuItems() {

        return new Tab[] {
                createTab("Dashboard", AdminDashboardView.class),
                createTab("Registration Of Patient", RegistrationofPatientView.class ),
                createTab("Check Patient Record", PatientlistView.class ),
                createTab("Registration of doctor", RegistrationOfDoctorView.class ),
                createTab("Available doctors", AppointmentformView.class ),
                createTab("Set appointment", AppointmentformView.class ),
                createTab("Appointment history", AppointmentHistoryView.class ),
                createTab("Room availability", AvailabilityOfRoomView.class ),
                createTab("Death report", DeathreportView.class ),
                createTab("Blood bank", BloodbankView.class )

        };
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget ) {

        final Tab tab = new Tab();
        if (count==2){
            tab.add(new RouterLink(text, navigationTarget));
            ComponentUtil.setData(tab, Class.class, navigationTarget);
        }
        else {
            tab.add(new RouterLink(text, navigationTarget));
            ComponentUtil.setData(tab, Class.class, navigationTarget);
        }
        count++;
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren()
                .filter(tab -> ComponentUtil.getData(tab, Class.class)
                        .equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        return getContent().getClass().getAnnotation(PageTitle.class).value();
    }

}
