import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';

class AvailabilityOfRoomView extends PolymerElement {

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
                }

            </style>
<div id="layout" style="width: 100%; height: 100%;">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-bottom: var(--lumo-space-m); padding-top: var(--lumo-space-m); margin-top: var(--lumo-space-xs);"> Rooms Detail</h3>
 <vaadin-horizontal-layout theme="spacing" id="topLayout" style="width: 100%; height: 10%; align-items: center; justify-content: center;">
  <vaadin-text-field style="width: 80%;" placeholder="Search for Rooms and BedID" id="searchField"></vaadin-text-field>
  <vaadin-button theme="icon" aria-label="Add new" id="searchbutton">
   <iron-icon icon="lumo:plus"></iron-icon>
  </vaadin-button>
 </vaadin-horizontal-layout>
 <div style="width: 100%;">
  <slot name="grid"></slot>
  <vaadin-horizontal-layout style="justify-content: flex-start; margin-left: var(--lumo-space-l);" theme="spacing-xl">
   <vaadin-text-field label="Ward No" placeholder="ward no placed here" id="wardNoTextField" style="align-self: center; flex-grow: 0; margin-right: var(--lumo-space-xl);" readonly prevent-invalid-input></vaadin-text-field>
   <vaadin-text-field label="RoomNo" placeholder="room id placed here" id="roomNoTextField" style="align-self: center; flex-grow: 0; margin-right: var(--lumo-space-xl);" readonly></vaadin-text-field>
   <vaadin-text-field label="Status Of Room" placeholder="status will be here" id="statusOfRoom" readonly></vaadin-text-field>
  </vaadin-horizontal-layout>
 </div>
</div>
`;
    }

    static get is() {
        return 'availability-of-room-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(AvailabilityOfRoomView.is, AvailabilityOfRoomView);
