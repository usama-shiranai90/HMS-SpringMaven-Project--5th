import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-accordion/src/vaadin-accordion.js';
import '@vaadin/vaadin-accordion/src/vaadin-accordion-panel.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@vaadin/vaadin-date-picker/src/vaadin-date-picker.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';

class RegistrationOfDoctorQualificationView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    background-color: var(--lumo-contrast-10pct);
                    box-sizing: border-box;
                    display: block;
                    font-size: var(--lumo-font-size-m);
                    height: 105%;
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

                .accounttype {
                    margin-left: 8px;
                    margin-top: 20px;
                    /*align-content: center;*/
                    /*align-items: center;*/
                    text-align: center;
                }

                label.radio {
                    cursor: pointer;
                    text-indent: 35px;
                    overflow: visible;
                    display: inline-block;
                    position: relative;
                    margin-bottom: 15px;
                }

                label.radio:before {
                    background: #3a57af;
                    content: '';
                    position: absolute;
                    top: 2px;
                    left: 0;
                    width: 20px;
                    height: 20px;
                    border-radius: 100%;
                }

                label.radio:after {
                    opacity: 0;
                    content: '';
                    position: absolute;
                    width: 0.5em;
                    height: 0.25em;
                    background: transparent;
                    top: 7.5px;
                    left: 4.5px;
                    border: 3px solid #ffffff;
                    border-top: none;
                    border-right: none;

                    -webkit-transform: rotate(-45deg);
                    -moz-transform: rotate(-45deg);
                    -o-transform: rotate(-45deg);
                    -ms-transform: rotate(-45deg);
                    transform: rotate(-45deg);
                }

                input[type=radio]:checked + label:after {
                    opacity: 1;
                }


            </style>
<div id="layout" style="width: 100%; height: 100%;">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-bottom: var(--lumo-space-m); padding-top: var(--lumo-space-m); margin-top: var(--lumo-space-xs);"> Registration Of Doctor</h3>
 <h4 style="align-self: stretch; text-align: center; padding: var(--lumo-space-xs);">Please Complete all the required blanks given below</h4>
 <hr>
 <div class="accounttype" style="align-self: stretch; flex-grow: 0; flex-shrink: 1;">
  <input type="radio" value="None" id="radioTwo" name="account" checked>
  <label for="radioTwo" class="radio" id="label">Personal Information</label>
  <input type="radio" value="None" id="radioOne" name="account" checked>
  <label for="radioOne" class="radio" chec>Qualification</label>
 </div>
 <hr>
 <vaadin-accordion opened="1">
  <h4>Click on the arrows to expand and then add education information . Thankyou</h4>
  <vaadin-accordion-panel id="firstpanel">
   <vaadin-horizontal-layout style="justify-content: center;" theme="spacing-xl">
    <vaadin-text-field label="Qualification Name" placeholder="Placeholder" id="matric_qual_name"></vaadin-text-field>
    <vaadin-text-field label="Institute Name" placeholder="Placeholder" id="matric_institue_name"></vaadin-text-field>
    <vaadin-date-picker style="align-self: flex-end;" label="Graduation Year" id="matric_date"></vaadin-date-picker>
    <vaadin-button id="matric_addbutton" style="align-self: flex-end;">
      click 
    </vaadin-button>
   </vaadin-horizontal-layout>
  </vaadin-accordion-panel>
  <vaadin-accordion-panel id="secondpanel">
   <vaadin-horizontal-layout style="justify-content: center;" theme="spacing-xl">
    <vaadin-text-field label="Qualification Name" placeholder="Placeholder" id="interdemidate_qual_name"></vaadin-text-field>
    <vaadin-text-field label="Institute Name" placeholder="Placeholder" id="interdemidate_inst_name"></vaadin-text-field>
    <vaadin-date-picker style="align-self: flex-end;" label="Graduation Year" id="interdemidate_date"></vaadin-date-picker>
    <vaadin-button theme="icon" aria-label="Add new" id="inter_addbutton" style="align-self: flex-end;">
     <iron-icon icon="lumo:plus"></iron-icon>
    </vaadin-button>
   </vaadin-horizontal-layout>
  </vaadin-accordion-panel>
  <vaadin-accordion-panel id="thirdpanel">
   <vaadin-horizontal-layout style="justify-content: center;" theme="spacing-xl">
    <vaadin-text-field label="Qualification Name" placeholder="Placeholder" id="undergrad_qual_name"></vaadin-text-field>
    <vaadin-text-field label="Institute Name" placeholder="Placeholder" id="undergrad_inst_name"></vaadin-text-field>
    <vaadin-date-picker style="align-self: flex-end;" label="Graduation Year" id="undergrad_date"></vaadin-date-picker>
    <vaadin-button theme="icon" aria-label="Add new" id="under_addbutton" style="align-self: flex-end;">
     <iron-icon icon="lumo:plus"></iron-icon>
    </vaadin-button>
   </vaadin-horizontal-layout>
  </vaadin-accordion-panel>
  <vaadin-accordion-panel id="fourthpanel">
   <vaadin-horizontal-layout style="justify-content: center;" theme="spacing-xl">
    <vaadin-text-field label="Qualification Name" placeholder="Placeholder" id="master_qual_name"></vaadin-text-field>
    <vaadin-text-field label="Institute Name" placeholder="Placeholder" id="master_inst_name"></vaadin-text-field>
    <vaadin-date-picker style="align-self: flex-end;" label="Graduation Year" id="master_date"></vaadin-date-picker>
    <vaadin-button theme="icon" aria-label="Add new" id="master_addbutton" style="align-self: flex-end;">
     <iron-icon icon="lumo:plus"></iron-icon>
    </vaadin-button>
   </vaadin-horizontal-layout>
  </vaadin-accordion-panel>
  <vaadin-accordion-panel id="fifthpanel">
   <vaadin-horizontal-layout style="justify-content: center;" theme="spacing-xl">
    <vaadin-text-field label="Qualification Name" placeholder="Placeholder"></vaadin-text-field>
    <vaadin-text-field label="Institute Name" placeholder="Placeholder"></vaadin-text-field>
    <vaadin-date-picker style="align-self: flex-end;" label="Graduation Year"></vaadin-date-picker>
    <vaadin-button theme="icon" aria-label="Add new" id="addbutton" style="align-self: flex-end;">
     <iron-icon icon="lumo:plus"></iron-icon>
    </vaadin-button>
   </vaadin-horizontal-layout>
  </vaadin-accordion-panel>
 </vaadin-accordion>
 <div style="width: 100%;">
  <slot name="qualificationtable"></slot>
 </div>
 <vaadin-horizontal-layout style="align-items: center; justify-content: flex-end;" theme="spacing-xl">
   Click here to finish education and return to home 
  <vaadin-button theme="primary" id="save" class="HomeButton" tabindex="2">
    Submit 
  </vaadin-button>
 </vaadin-horizontal-layout>
</div>
`;
    }

    static get is() {
        return 'registration-of-doctor-qualification-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(RegistrationOfDoctorQualificationView.is, RegistrationOfDoctorQualificationView);
