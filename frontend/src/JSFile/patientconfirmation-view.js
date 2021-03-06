import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-upload/src/vaadin-upload.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-area.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@polymer/iron-icon/iron-icon.js';

class PatientconfirmationView extends PolymerElement {

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

    
                #patientimage {
                    background-color: #596186;
                    text-align: center;
                    /*border: 2px solid red;*/
                    border-radius: 25px;
                    /*height: 150px;*/
                }

                #uploadpic {
                    background-color: #e2e2e2;
                    -moz-border-radius-bottomleft: 0px;
                    border-top-left-radius: 25px;
                    border-top-right-radius: 25px;
                }

            </style>
<div id="layout" style="width: 100%;">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-top: var(--lumo-space-m); padding-bottom: var(--lumo-space-m); margin-top: var(--lumo-space-xs);"> Registration of Patient</h3>
 <h4 style="align-self: stretch; text-align: center; padding: var(--lumo-space-xs);">Patient record successfully added , please add picture and return to Home screen.</h4>
 <vaadin-horizontal-layout theme="spacing" style="align-items: center; justify-content: center; padding-top: var(--lumo-space-xl); padding-bottom: var(--lumo-space-xl); margin-bottom: var(--lumo-space-xl);">
  <div id="patientimage" style="flex-grow: 1; flex-shrink: 0; height: 550%; align-self: center; margin-left: var(--lumo-space-l);">
   <vaadin-upload id="uploadpic" style="height: 100%; padding-bottom: var(--lumo-space-xl); margin-bottom: var(--lumo-space-xl);"></vaadin-upload>
  </div>
  <vaadin-vertical-layout style="align-self: center; align-items: flex-start; justify-content: flex-start; flex-grow: 1;">
   <vaadin-text-field label="Unique ID for Patient" placeholder="Code" style="flex-grow: 0; align-self: stretch;" id="patientID">
    <iron-icon></iron-icon>
   </vaadin-text-field>
   <vaadin-vertical-layout style="align-self: stretch; align-items: flex-start; justify-content: flex-end; flex-grow: 1;">
    <vaadin-text-field label="Phone no" style="flex-grow: 0; align-self: stretch; padding-top: var(--lumo-space-l); padding-bottom: var(--lumo-space-l);" id="phoneNo"></vaadin-text-field>
   </vaadin-vertical-layout>
   <vaadin-text-area placeholder="Patient information has being send to given

contact no" style="align-self: stretch; flex-grow: 0; flex-shrink: 1;"></vaadin-text-area>
  </vaadin-vertical-layout>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout style="justify-content: space-between;" theme="spacing-xl">
  <vaadin-horizontal-layout style="align-self: flex-end;" theme="spacing-l">
   <vaadin-vertical-layout>
    <label style="align-self: center;">Do you wish to set appointment </label>
    <a href="https://vaadin.com" style="align-self: center;">click on appointment</a>
   </vaadin-vertical-layout>
   <vaadin-button id="appointmentbutton" style="align-self: center;">
    Appointment
    <iron-icon icon="lumo:arrow-right"></iron-icon>
   </vaadin-button>
  </vaadin-horizontal-layout>
  <vaadin-button theme="primary success" id="home" style="align-self: center;">
    Home 
  </vaadin-button>
 </vaadin-horizontal-layout>
</div>
`;
    }

    static get is() {
        return 'patientconfirmation-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(PatientconfirmationView.is, PatientconfirmationView);
