package org.Java.HMS.View;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the doctor-detail-view template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("doctor-detail-view")
@JsModule("./src/JSFile/doctor-detail-view.js")
public class DoctorDetailView extends PolymerTemplate<DoctorDetailView.DoctorDetailViewModel> {

    /**
     * Creates a new DoctorDetailView.
     */
    public DoctorDetailView() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between DoctorDetailView and doctor-detail-view
     */
    public interface DoctorDetailViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
