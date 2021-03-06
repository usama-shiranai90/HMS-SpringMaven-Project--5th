import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-combo-box/src/vaadin-combo-box.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';

class RegistrationOfDoctorView extends PolymerElement {

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


                input[type=radio] {
                    visibility: hidden;
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

                hr {
                    color: #a9a9a9;
                    opacity: 0.3;
                }

                .accounttype {
                    margin-left: 8px;
                    margin-top: 20px;
                    /*align-content: center;*/
                    /*align-items: center;*/
                    text-align: center;
                }


            </style>
<div id="layout">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-bottom: var(--lumo-space-m); padding-top: var(--lumo-space-m); margin-top: var(--lumo-space-xs);"> Registration Of Doctor</h3>
 <h4 style="align-self: stretch; text-align: center; padding: var(--lumo-space-xs);">Please Complete all the required blanks given below</h4>
 <hr>
 <div class="accounttype" style="align-self: stretch; flex-grow: 0; flex-shrink: 1;">
  <input type="radio" value="None" id="radioOne" name="account" checked>
  <label for="radioOne" class="radio" chec>Personal Information</label>
  <input type="radio" value="None" id="radioTwo" name="account">
  <label for="radioTwo" class="radio" id="label">Qualification</label>
 </div>
 <hr>
 <vaadin-form-layout>
  <vaadin-text-field label="First name" id="firstname" theme="lumo" placeholder="Enter first name"></vaadin-text-field>
  <vaadin-text-field label="Last name" id="lastname" placeholder="Enter last name"></vaadin-text-field>
  <vaadin-text-field label="Age" id="doctorage" style="width: 100%;" placeholder="Enter age"></vaadin-text-field>
  <vaadin-email-field id="doctormail" label="Email address" placeholder="Enter email "></vaadin-email-field>
  <vaadin-custom-field id="doctorGender" label="Gender" value="	">
   <vaadin-horizontal-layout theme="spacing" id="vaadinHorizontalLayout">
    <vaadin-combo-box id="genderComboBox" style="flex-grow: 1;" placeholder="select gender" prevent-invalid-input></vaadin-combo-box>
   </vaadin-horizontal-layout>
  </vaadin-custom-field>
  <vaadin-custom-field id="phoneNumber" label="Phone number" value="	">
   <vaadin-horizontal-layout theme="spacing">
    <vaadin-combo-box id="pnCountryCode" style="width: 120px;" pattern="\\+\\d*" placeholder="Country" prevent-invalid-input></vaadin-combo-box>
    <vaadin-text-field id="pnNumber" style="flex-grow: 1;" pattern="\\d*" prevent-invalid-input placeholder="number"></vaadin-text-field>
   </vaadin-horizontal-layout>
  </vaadin-custom-field>
  <vaadin-date-picker id="birthday" label="Birthday" style="margin-right: var(--lumo-space-s); width: 40%;" placeholder="mm-dd-yy"></vaadin-date-picker>
  <vaadin-text-field label="Experience" id="doctorexperience" theme="lumo" placeholder="experience in year"></vaadin-text-field>
  <vaadin-horizontal-layout>
   <vaadin-combo-box id="specializationlist" label="Specialization"></vaadin-combo-box>
   <vaadin-button id="add_specialization" style="align-self: flex-end; flex-grow: 0; margin-left: var(--lumo-space-xl);" theme="umo">
     Add 
   </vaadin-button>
  </vaadin-horizontal-layout>
 </vaadin-form-layout>
 <vaadin-horizontal-layout style="justify-content: flex-end;">
  <vaadin-button theme="primary" id="save" class="nextbutton" tabindex="2">
    Next 
  </vaadin-button>
 </vaadin-horizontal-layout>
</div>
`;
    }

    static get is() {
        return 'registration-of-doctor-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(RegistrationOfDoctorView.is, RegistrationOfDoctorView);
