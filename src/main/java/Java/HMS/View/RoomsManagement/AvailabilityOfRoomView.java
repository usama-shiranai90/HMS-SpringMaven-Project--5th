package Java.HMS.View.RoomsManagement;

import Java.HMS.Data.Room;
import Java.HMS.Data.Services.RoomServies;
import Java.HMS.View.Administrator.AdminMainpage;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.FooterRow;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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

import java.util.List;

@Route(value = "rooms-list", layout = AdminMainpage.class)
@Tag("availability-of-room-view")
@JsModule("./src/JSFile/availability-of-room-view.js")
@PageTitle("Rooms list")
public class AvailabilityOfRoomView extends PolymerTemplate<TemplateModel> {

    @Id("topLayout")
    private HorizontalLayout topLayout;
    @Id("statusOfRoom")
    private TextField statusOfRoom;
    @Id("searchbutton")
    private Button searchbutton;
    @Id("searchField")
    private TextField searchField;
    @Id("wardNoTextField")
    private TextField wardNoTextField;
    @Id("roomNoTextField")
    private TextField roomNoTextField;

    private Room room;
    private List<Room> roomList;
    private Grid<Room> grid = new Grid<>(Room.class, false);
    private Binder<Room> binder = new Binder<>(Room.class);
    private ListDataProvider<Room> listDataProvider;
    private TextField filter;
    private Button removeButton;
    private Dialog  confirmationDialog= new Dialog();
    private FooterRow footerRow;




    public AvailabilityOfRoomView(@Autowired RoomServies service) {

        updateGrid(service);
        listDataProvider = DataProvider.ofCollection(roomList);
        setConfirmationDialog(service);


        grid.addColumn("wardno").setAutoWidth(true).setHeader("Ward Number");
        grid.addColumn("wardname").setAutoWidth(true).setHeader("Ward Name");
        grid.addColumn("numberofbeds").setAutoWidth(true).setHeader("Ward Beds");
        grid.addColumn("roomno").setAutoWidth(true).setHeader("Room-Number");
        grid.addColumn("status").setAutoWidth(true).setHeader("Status");


//        binder.forField(textfield).withConverter(new StringToIntegerConverter("Must enter a number")).bind("roomid");

        grid.setHeight("500px");
//        grid.setItems(roomList);
        grid.setDataProvider(listDataProvider);
        grid.getElement().setAttribute("slot", "grid");

        footerRow = grid.appendFooterRow();
        removeButton = new Button("Remove");

        removeButton.addClickListener(event -> {
            confirmationDialog.open();
            grid.getDataProvider().refreshAll();
        });

        grid.addSelectionListener(e -> updateForm());
        getElement().appendChild(grid.getElement());
        grid.setDetailsVisibleOnClick(false);

        footerRow.getCell(grid.getColumnByKey("status")).setComponent(removeButton);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue()!=null){
                roomNoTextField.setValue(String.valueOf(event.getValue().getRoomno()));
                wardNoTextField.setValue( String.valueOf(event.getValue().getWardno()));
                statusOfRoom.setValue(event.getValue().getStatus() );
            }
        });

        searchbutton.addClickListener(event -> {

            if (searchField.getValue().equalsIgnoreCase("")){
                updateGrid(service);
            }

                listDataProvider.setFilter(room -> room.getStatus().equalsIgnoreCase(searchField.getValue()) );
        });


    }

    private void updateGrid(RoomServies service) {
        roomList = service.findAll();
        grid.setItems(roomList);
        setFormVisible();
    }

    private void updateForm() {
        if (!grid.asSingleSelect().isEmpty()) {
            room = grid.asSingleSelect().getValue();
            binder.setBean(room);
        }
        setFormVisible();
    }


    private void setFormVisible() {
        roomNoTextField.setVisible(true);
        wardNoTextField.setVisible(true);
        statusOfRoom.setVisible(true);
    }

    private void removeroom(RoomServies service) {
        service.removeroom(room);
        updateGrid(service);
    }


    private void setConfirmationDialog(RoomServies service){
        confirmationDialog.setWidth("400px");
        confirmationDialog.setHeight("150px");
        confirmationDialog.add("Are you sure , you want to clear this room?");
        Span message = new Span();
        Button confirmButton = new Button("Confirm", event -> {
            removeroom(service);
            message.setText("Confirmed!");
            confirmationDialog.close();
        });

        Button cancelButton = new Button("Cancel", event -> {
            message.setText("Cancelled...");
            confirmationDialog.close();
        });
        confirmationDialog.add(confirmButton, cancelButton);
    }

}

/*
//      roomList = servies.findAll();
        grid = new Grid<>(Room.class, false);
        grid.addColumn("roomid").setAutoWidth(true);
        grid.addColumn("bedid").setAutoWidth(true);
        grid.addColumn("bedstatus").setAutoWidth(true);

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.getElement().setAttribute("slot", "grid");

        grid.setItems(roomList);

        Button addButton = new Button("Add Item", event -> {
            roomList.add(new Room(114, 2, "Empty"));
            grid.getDataProvider().refreshAll();

        });

        Button removeButton = new Button("Free Room", event -> {
            roomList.remove(roomList.size() - 1);
            grid.getDataProvider().refreshAll();
        });
        getElement().appendChild(grid.getElement());
        grid.setDetailsVisibleOnClick(false);*/