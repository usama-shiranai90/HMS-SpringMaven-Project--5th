import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-details/src/vaadin-details.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-date-picker/src/vaadin-date-picker.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';

class AppointmentformView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    background-color: var(--lumo-contrast-10pct);
                    box-sizing: border-box;
                    display: block;
                    font-size: var(--lumo-font-size-m);
                    height: 100%;
                    overflow: auto;
                    padding: var(--lumo-space-m) var(--lumo-space-l);

                }

                #layout {
                    margin: 0 auto;
                    max-width: 1024px;
                    background-color: #FFFFFF;
                    padding: var(--lumo-space-s);
                    border-radius: var(--lumo-border-radius);
                    box-shadow: var(--lumo-box-shadow-xs);
                    overflow: hidden;
                    flex-direction: column;
                }


                #submitbutton {
                    text-align: center;
                    align-items: center;
                    background-color: #3f4f79;
                    align-content: center;
                }
            </style>
<div id="layout" style="width: 100%;">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-bottom: var(--lumo-space-m); padding-top: var(--lumo-space-m); margin-top: var(--lumo-space-xs);"> Appointment Form</h3>
 <h4 style="align-self: stretch; text-align: center; padding: var(--lumo-space-xs);">Please insert the PatientID to contiune</h4>
 <vaadin-horizontal-layout theme="spacing" style="justify-content: center; align-items: baseline;">
  <div>
   <vaadin-text-field label="PatientID" placeholder="Enter patientID" id="patientid" invalid></vaadin-text-field>
   <vaadin-button style="flex-grow: 0; flex-shrink: 0; margin-left: var(--lumo-space-s);" id="search">
    <iron-icon icon="lumo:arrow-right" slot="suffix"></iron-icon>
   </vaadin-button>
  </div>
 </vaadin-horizontal-layout>
 <vaadin-details id="vaadinDetails">
  <vaadin-form-layout>
   <vaadin-text-field label="First name" id="firstName" theme="lumo" placeholder="Enter first name"></vaadin-text-field>
   <vaadin-text-field label="Last name" id="lastName" placeholder="Enter last name"></vaadin-text-field>
   <vaadin-text-field label="Age" id="patientage" style="width: 100%;" placeholder="Enter age"></vaadin-text-field>
   <vaadin-email-field id="email" label="Email address" placeholder="Enter email "></vaadin-email-field>
   <vaadin-horizontal-layout style="justify-content: flex-end; align-items: baseline; flex-direction: row;" theme="spacing-l">
    <vaadin-combo-box id="doctoridlist" label="Available Doctor" style="align-self: center; flex-grow: 1;" allow-custom-value clear-button-visible></vaadin-combo-box>
    <vaadin-text-field label="Doctor Name" placeholder="DoctorName" id="doctorname"></vaadin-text-field>
   </vaadin-horizontal-layout>
   <vaadin-date-picker id="appointmentDate"></vaadin-date-picker>
  </vaadin-form-layout>
  <vaadin-horizontal-layout style="width: 100%; height: 100%; justify-content: center; padding-top: var(--lumo-space-s); margin-top: var(--lumo-space-m);">
   <vaadin-button theme="primary" id="submitbutton">
     Submit 
    <iron-icon icon="lumo:plus"></iron-icon>
   </vaadin-button>
  </vaadin-horizontal-layout>
 </vaadin-details>
</div>
`;
    }

    static get is() {
        return 'appointmentform-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(AppointmentformView.is, AppointmentformView);
