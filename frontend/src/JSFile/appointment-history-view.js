import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';

class AppointmentHistoryView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    box-sizing: border-box;
                    display: block;
                    height: 100%;
                    overflow: auto;

                    padding: var(--lumo-space-m) var(--lumo-space-l);
                    font-size: var(--lumo-font-size-m);
                    background-color: var(--lumo-contrast-10pct);
                }

                #layout {
                    margin: 0 auto;
                    max-width: 1024px;
                    background-color: #FFFFFF;
                    padding: var(--lumo-space-s);
                    border-radius: var(--lumo-border-radius);
                    box-shadow: var(--lumo-box-shadow-xs);
                    /*overflow: hidden;*/
                    flex-direction: column;
                }
            </style>
<div id="layout" style="width: 100%; height: 100%;">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-bottom: var(--lumo-space-m); padding-top: var(--lumo-space-m); margin-top: var(--lumo-space-xs);">Appointment History</h3>
 <vaadin-horizontal-layout theme="spacing" id="topLayout" style="width: 100%; height: 10%; align-items: center; justify-content: center;">
  <vaadin-text-field style="width: 80%;" placeholder="Search for patient according to its appointmentID" id="searchField"></vaadin-text-field>
  <vaadin-button theme="icon" aria-label="Add new" id="searchbutton">
   <iron-icon icon="lumo:plus"></iron-icon>
  </vaadin-button>
 </vaadin-horizontal-layout>
 <div style="width: 100%;">
  <slot name="grid"></slot>
  <vaadin-horizontal-layout style="justify-content: flex-start; margin-left: var(--lumo-space-l);" theme="spacing-xl">
   <vaadin-text-field label="Patient FullName" placeholder="ward no placed here" id="patientTextField" style="align-self: center; flex-grow: 0; margin-right: var(--lumo-space-xl);" readonly prevent-invalid-input></vaadin-text-field>
   <vaadin-text-field label="Patient Blood Group" placeholder="status will be here" id="patientBloodGroupTextField" readonly></vaadin-text-field>
   <vaadin-text-field label="Phone Number" placeholder="room id placed here" id="patientPhoneNumberTextField" style="align-self: center; flex-grow: 0; margin-right: var(--lumo-space-xl);" readonly></vaadin-text-field>
   <vaadin-text-field label="Doctor Name" placeholder="status will be here" id="doctorfullname" readonly></vaadin-text-field>
  </vaadin-horizontal-layout>
 </div>
</div>
`;
    }

    static get is() {
        return 'appointment-history-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(AppointmentHistoryView.is, AppointmentHistoryView);
