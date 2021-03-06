import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-grid/src/vaadin-grid-column.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';

class PatientlistView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    background-color: var(--lumo-contrast-10pct);
                    box-sizing: border-box;
                    display: block;
                    font-size: var(--lumo-font-size-m);
                    height: auto;
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
                    /*overflow: hidden;*/
                    flex-direction: column;
                    
                }


                #rightside {
                    /*border-left: dashed #000003;*/
                    /*border-left: thick #90ee7e;*/
                    border-left: groove #f5f0f0;
                    box-shadow: 5px 10px 3px #cfcfcf;

                }

            </style>
<div id="layout" style="width: 100%; height: 100%;">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-bottom: var(--lumo-space-m); padding-top: var(--lumo-space-m); margin-top: var(--lumo-space-xs);"> List Of Patients</h3>
 <vaadin-horizontal-layout id="topLayout" style="width: 100%; justify-content: center; align-items: center;"></vaadin-horizontal-layout>
 <vaadin-horizontal-layout style="width: 100%; height: 100%;">
  <vaadin-vertical-layout id="gridDivior" style="align-items: center; height: 100%; justify-content: flex-end; flex-direction: column;">
   <h4>List of Patient</h4>
   <div style="flex-grow:1; width:100%;" id="grid-wrapper">
    <slot name="grid"></slot>
   </div>
   <h4>List of Admitted Patient</h4>
   <div style="flex-grow:1; width:100%;" id="grid-wrapper">
    <slot name="admittedPatientGrid"></slot>
   </div>
  </vaadin-vertical-layout>
  <div style="width:400px; display:flex; flex-direction:column; justify-content: flex-end; align-items: center; height: 100%;" id="rightside">
   <div style="padding:var(--lumo-space-l); flex-grow:1;">
    <vaadin-form-layout id="lay" style="margin-bottom: var(--lumo-space-xl);">
     <vaadin-text-field label="Patient ID" id="patientid"></vaadin-text-field>
     <vaadin-text-field label="Full Name" id="fullname"></vaadin-text-field>
     <vaadin-text-field label="Age" id="age"></vaadin-text-field>
     <vaadin-text-field label="Gender" id="gender"></vaadin-text-field>
     <vaadin-text-field label="Address" id="address"></vaadin-text-field>
     <vaadin-text-field label="Phoneno" id="phoneno"></vaadin-text-field>
     <vaadin-text-field label="Email" id="email"></vaadin-text-field>
     <vaadin-date-picker label="Date" id="date"></vaadin-date-picker>
    </vaadin-form-layout>
   </div>
   <vaadin-horizontal-layout style="flex-wrap:wrap; width:100%; background-color:var(--lumo-contrast-5pct); padding:var(--lumo-space-s) var(--lumo-space-l); width: 100%; align-content: flex-end; flex-shrink: 1; align-self: flex-start; justify-content: center; margin-bottom: var(--lumo-space-xl);" theme="spacing-l">
    <vaadin-button theme="primary" id="save">
      Save 
    </vaadin-button>
    <vaadin-button theme="tertiary" slot="" id="cancel">
      Cancel 
    </vaadin-button>
   </vaadin-horizontal-layout>
  </div>
 </vaadin-horizontal-layout>
</div>
`;
    }

    static get is() {
        return 'patientlist-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(PatientlistView.is, PatientlistView);
