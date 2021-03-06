import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-email-field.js';
import '@vaadin/vaadin-custom-field/src/vaadin-custom-field.js';
import '@vaadin/vaadin-combo-box/src/vaadin-combo-box.js';
import '@vaadin/vaadin-date-picker/src/vaadin-date-picker.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-area.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-text-field/src/vaadin-number-field.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';

class RegistrationofPatientView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles lumo-typography">
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

                .patientaddress {
                    text-align: justify;
                    align-content: stretch;
                    width: 300%;
                }

                .savebutton {
                    background-color: #000003;
                }

                #firstName {
                    background-color: transparent;
                }


            </style>
<div id="layout">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-bottom: var(--lumo-space-m); padding-top: var(--lumo-space-m); margin-top: var(--lumo-space-xs);"> Registration of Patient</h3>
 <h4 style="align-self: stretch; text-align: center; padding: var(--lumo-space-xs);">Please Complete all the required blanks given below</h4>
 <vaadin-form-layout style="width: 100%;">
  <vaadin-text-field label="First name" id="firstName" theme="lumo" placeholder="Enter first name" autoselect autofocus></vaadin-text-field>
  <vaadin-text-field label="Last name" id="lastName" placeholder="Enter last name"></vaadin-text-field>
  <vaadin-number-field id="patientage" placeholder="Enter age here" label="Age" error-message="age must be in range" invalid></vaadin-number-field>
  <vaadin-email-field id="email" label="Email address" placeholder="Enter email " error-message="must be a valid email" invalid></vaadin-email-field>
  <vaadin-custom-field id="PatientGender" label="Gender" value="	">
   <vaadin-horizontal-layout theme="spacing">
    <vaadin-combo-box id="genderComboBox" style="flex-grow: 1;" placeholder="select gender" prevent-invalid-input></vaadin-combo-box>
   </vaadin-horizontal-layout>
  </vaadin-custom-field>
  <vaadin-date-picker id="birthday" label="Birthday" style="margin-right: var(--lumo-space-s); width: 40%;" placeholder="mm-dd-yy"></vaadin-date-picker>
  <vaadin-custom-field id="phoneNumber" label="Phone number" value="	">
   <vaadin-horizontal-layout theme="spacing">
    <vaadin-combo-box id="pnCountryCode" style="width: 120px;" pattern="\\+\\d*" placeholder="Country" prevent-invalid-input loading helper-text="select country-code"></vaadin-combo-box>
    <vaadin-text-field id="pnNumber" style="flex-grow: 1;" pattern="\\d*" prevent-invalid-input placeholder="Contact Number"></vaadin-text-field>
   </vaadin-horizontal-layout>
  </vaadin-custom-field>
  <vaadin-text-field label="BloodGroup" placeholder="enter blood-type" id="bloodGroup" helper-text="Blood Type can be of A,B.AB.O"></vaadin-text-field>
  <vaadin-text-area label="Write Address" placeholder="Add detailed" id="occupation" required class="patientaddress" invalid autoselect style="flex-grow: 1; align-self: stretch; flex-shrink: 0;"></vaadin-text-area>
 </vaadin-form-layout>
 <vaadin-horizontal-layout style="margin-top: var(--lumo-space-m); margin-bottom: var(--lumo-space-l); justify-content: center;" theme="spacing">
  <vaadin-button theme="primary" id="save" class="savebutton">
    Save 
  </vaadin-button>
 </vaadin-horizontal-layout>
</div>
`;
    }

    static get is() {
        return 'registrationof-patient-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(RegistrationofPatientView.is, RegistrationofPatientView);
