import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-area.js';
import '@vaadin/vaadin-date-picker/src/vaadin-date-picker.js';

class DeathreportView extends PolymerElement {

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
                
                
            </style>
<div id="layout" style="width: 100%; height: 100%;">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-bottom: var(--lumo-space-m); padding-top: var(--lumo-space-m); margin-top: var(--lumo-space-xs);">Death Report Form</h3>
 <vaadin-horizontal-layout style="align-items: center; justify-content: flex-start;" theme="spacing-xl">
  <h4 style="align-self: flex-end; text-align: center; padding: var(--lumo-space-xs); flex-grow: 1;">Instruction:</h4>NOTIFY LICENSING AGENCY, PLACEMENT AGENCY AND RESPONSIBLE PERSONS, IF ANY, BY NEXT WORKING DAY SUBMIT WRITTEN REPORT WITHIN 7 DAYS OF OCCURRENCE. RETAIN COPY OF REPORT IN CLIENTS FILE.
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="justify-content: center; align-items: baseline;">
  <div>
   <vaadin-text-field label="PatientID" placeholder="Enter patientID" id="patientid" style="flex-grow: 0; flex-shrink: 0;"></vaadin-text-field>
   <vaadin-button style="flex-grow: 0; flex-shrink: 0; margin-left: var(--lumo-space-s);" id="search">
    <iron-icon icon="lumo:arrow-right" slot="suffix"></iron-icon>
   </vaadin-button>
  </div>
 </vaadin-horizontal-layout>
 <vaadin-details id="vaadinDetails" opened>
  <vaadin-form-layout>
   <vaadin-text-field label="First name" id="firstName" theme="lumo"></vaadin-text-field>
   <vaadin-text-field label="Last name" id="lastName"></vaadin-text-field>
   <vaadin-text-field label="Age" id="patientage" style="width: 100%;"></vaadin-text-field>
   <vaadin-email-field id="email" label="Email address" placeholder="Enter email "></vaadin-email-field>
   <vaadin-text-field label="phone number" id="phoneNumber"></vaadin-text-field>
   <vaadin-text-area label="Address" id="address"></vaadin-text-area>
   <vaadin-text-field label="Death Place" placeholder="Placeholder" id="deathPlace"></vaadin-text-field>
   <vaadin-text-field label="Death Cause" placeholder="Placeholder" id="deathCause"></vaadin-text-field>
   <vaadin-date-picker id="dateOfDeath" label="Date Of Death"></vaadin-date-picker>
  </vaadin-form-layout>
  <vaadin-horizontal-layout style="width: 100%; height: 100%; justify-content: center; padding-top: var(--lumo-space-s); margin-top: var(--lumo-space-m);">
   <vaadin-button theme="primary" id="generate">
    Generate
    <iron-icon icon="lumo:plus"></iron-icon>
   </vaadin-button>
  </vaadin-horizontal-layout>
 </vaadin-details>
</div>
`;
    }

    static get is() {
        return 'deathreport-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(DeathreportView.is, DeathreportView);
