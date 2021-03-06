package Java.HMS.View.BloodBank;

import Java.HMS.Data.BloodBank;
import Java.HMS.View.Administrator.AdminMainpage;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import Java.HMS.Data.Services.BloodBankServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * A Designer generated component for the bloodbank-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route(value = "bloodbank", layout = AdminMainpage.class)
@PageTitle("Blood Bank Studio")
@Tag("bloodbank-view")
@JsModule("./src/JSFile/bloodbank-view.js")
public class BloodbankView extends PolymerTemplate<TemplateModel>  {

    private List<BloodBank> bloodBankList;
    private Grid<BloodBank> grid = new Grid<>(BloodBank.class, false);
    private Binder<BloodBank> binder = new Binder<>(BloodBank.class);
    private BloodBank bloodBank;

    public BloodbankView(@Autowired BloodBankServices service) {

        updateGrid(service);

        grid.addColumn("bloodserialno").setAutoWidth(true);
        grid.addColumn("bloodtype").setAutoWidth(true);
        grid.addColumn("donationDate").setAutoWidth(true);
        grid.addColumn("patientid").setAutoWidth(true);

        grid.setHeight("500px");
        grid.setItems(bloodBankList);
        grid.getElement().setAttribute("slot", "grid");
        grid.addSelectionListener(e -> updateForm());
        getElement().appendChild(grid.getElement());
        grid.setDetailsVisibleOnClick(false);

    }


    private void updateGrid(BloodBankServices service) {
        bloodBankList = service.findAll();
        grid.setItems(bloodBankList);
        setFormVisible();
    }

    private void updateForm() {
        if (!grid.asSingleSelect().isEmpty()) {
            bloodBank = grid.asSingleSelect().getValue();
            binder.setBean(bloodBank);
        }
        setFormVisible();
    }


    private void setFormVisible() {

    }

    public interface BloodbankViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
