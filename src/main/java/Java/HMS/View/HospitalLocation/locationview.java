package Java.HMS.View.HospitalLocation;

import Java.HMS.View.HospitalLocation.data.MapLocation;
import Java.HMS.View.HospitalLocation.data.MapLocationService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("hospital-location")
@PageTitle("Welcome , To our Map Location and see the nearest Hospital for you")
public class locationview extends VerticalLayout {

    private MapLocationService service;

    private LeafletMap map;

    @Autowired
    public locationview(MapLocationService service) {
        this.service = service;

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        showIntro();

        // Create the map and add it to this view
        map = new LeafletMap();
        map.setWidthFull();
        add(map);

        // Register for marker clicks
        map.addMarkerClickListener(e -> Notification.show("User clicked on the marker " + e.getMarker().getName()));

        // Register for clicks on the map itself
        map.addMapClickListener(this::mapClicked);

        // Add all known markers to the map
        map.addMarkersAndZoom(service.getAll());
    }

    private void showIntro() {
        H3 title = new H3("Welcome , To our Map Location and see the nearest Hospital for you....");
        Span subtitle = new Span("You can add a marker by clicking anywhere on the map.");
        Button ok = new Button("OK!", VaadinIcon.CHECK.create());
        VerticalLayout titleLayout = new VerticalLayout(title, subtitle, ok);
        titleLayout.setPadding(false);

        Dialog introDialog = new Dialog(titleLayout);
        introDialog.open();

        ok.addClickListener(e -> introDialog.close());
    }

    /**
     * Called when the user clicks the map. Creates a {@link Dialog} for the user to
     * input further data and to save the data.
     */
    private void mapClicked(LeafletMap.MapClickEvent event) {

        // Create a dialog for adding a marker. This is not part of the custom
        // component, just normal Vaadin stuff

        VerticalLayout popupLayout = new VerticalLayout();
        popupLayout.setPadding(false);

        Dialog popup = new Dialog(popupLayout);
        popup.open();

        Span coords = new Span(String.format("You selected the following coordinates: %f %f", event.getLatitude(), event.getLongitude()));

        TextField markerName = new TextField("What is this spot called?");
        markerName.setWidthFull();
        markerName.focus();

        Button saveMarker = new Button("Save", VaadinIcon.CHECK.create(), e -> {
            saveMarkerAndRefresh(markerName.getValue(), event.getLatitude(), event.getLongitude());
            popup.close();
        });
        saveMarker.addClickShortcut(Key.ENTER);
        saveMarker.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancel = new Button("Cancel", e -> popup.close());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.getStyle().set("margin-left", "auto");

        HorizontalLayout buttons = new HorizontalLayout(saveMarker, cancel);
        buttons.setWidthFull();
        buttons.setMargin(false);

        popupLayout.add(coords, markerName, buttons);
    }

    /**
     * Save a new marker in the backend and add it to the map
     */
    private void saveMarkerAndRefresh(String name, double latitude, double longitude) {
        MapLocation spot = new MapLocation(latitude, longitude, name);
        service.addSpot(spot);
        map.addMarker(spot);
    }
}
