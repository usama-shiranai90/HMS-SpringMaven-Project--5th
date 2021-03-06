package Java.HMS.View.ResourceManagement;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import Java.HMS.View.Administrator.AdminMainpage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Route(value = "dashboard", layout = AdminMainpage.class)
@PageTitle("Dashboard")
@JsModule("./src/JSFile/admindashboard-view.js")
@Tag("dashboard-view")

public class AdminDashboardView extends PolymerTemplate<AdminDashboardView.DashboardViewModel> implements
        AfterNavigationObserver {

    public interface DashboardViewModel extends TemplateModel {

         void setCurrentUsers(int currentUsers);
         void setNumEvents(String events);
         void setConversionRate(int conversionRate);
    }

    @Id
     Grid<HealthGridItem> grid;

    @Id
     Chart monthlyVisitors;
    @Id
     Chart responseTimes;

    public AdminDashboardView() {
        TemplateRenderer<HealthGridItem> template = TemplateRenderer
                .<HealthGridItem>of("<span theme$=\"[[item.theme]]\">[[item.status]]</span>")
                .withProperty("theme", HealthGridItem::getTheme).withProperty("status", HealthGridItem::getStatus);

        grid.addColumn(HealthGridItem::getCity).setHeader("City");
        grid.addColumn(template).setFlexGrow(0).setWidth("100px").setHeader("Status");
        grid.addColumn(HealthGridItem::getLocalDate).setHeader("Date").setWidth("140px");
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        // Top row widgets
        getModel().setCurrentUsers(4);
        getModel().setNumEvents("12.6K"); // TODO client-side formatting
        getModel().setConversionRate(18);

        // First chart

        Configuration configuration = monthlyVisitors.getConfiguration();
        configuration.addSeries(new ListSeries("Islamabad", 49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4,
                194.1, 95.6, 54.4));
        configuration.addSeries(
                new ListSeries("Lahore", 83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3));
        configuration.addSeries(
                new ListSeries("Karachi", 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2));


        XAxis x = new XAxis();
        x.setCrosshair(new Crosshair());
        x.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        configuration.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        configuration.addyAxis(y);

        // Grid
        List<HealthGridItem> gridItems = new ArrayList<>();
        gridItems.add(new HealthGridItem(LocalDate.of(2019, 11, 06), "Rawalpindi", "Pakistan", "Good", "badge"));
        gridItems.add(new HealthGridItem(LocalDate.of(2019, 11, 06), "Lahore", "Pakistan", "Good", "badge"));
        gridItems.add(new HealthGridItem(LocalDate.of(2019, 11, 06), "Karachi", "Pakistan", "Good", "badge"));
        gridItems.add(new HealthGridItem(LocalDate.of(2019, 12, 06), "Rawalpindi", "Pakistan", "Excellent", "badge success"));
        gridItems
                .add(new HealthGridItem(LocalDate.of(2019, 12, 06), "Rawalpindi", "Pakistan", "Good", "badge"));
        gridItems.add(new HealthGridItem(LocalDate.of(2019, 12, 06), "Lahore", "Pakistan", "Good", "badge"));
        gridItems.add(new HealthGridItem(LocalDate.of(2020, 1, 06), "Karachi", "Pakistan", "Good", "badge"));
        gridItems.add(new HealthGridItem(LocalDate.of(2020, 1, 06), "Rawalpindi", "Pakistan", "Failing", "badge error"));
        gridItems.add(new HealthGridItem(LocalDate.of(2020, 1, 06), "Lahore", "Pakistan", "Excellent",
                "badge success"));

        grid.setItems(gridItems);

        // Second chart
        configuration = responseTimes.getConfiguration();
        configuration
                .addSeries(new ListSeries("Islamabad", 7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6));
        configuration
                .addSeries(new ListSeries("Karachi", 3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8));

        x = new XAxis();
        x.setCrosshair(new Crosshair());
        x.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        configuration.addxAxis(x);

        y = new YAxis();
        y.setMin(0);
        configuration.addyAxis(y);
    }
}
