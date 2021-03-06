import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-select/src/vaadin-select.js';
import '@vaadin/vaadin-list-box/src/vaadin-list-box.js';
import '@vaadin/vaadin-item/src/vaadin-item.js';

class BloodbankView extends PolymerElement {

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
<div id="layout" style="height: 100%;">
 <h3 style="align-self: stretch; background-color:#353d5b; color: whitesmoke; text-align: center; padding-bottom: var(--lumo-space-m); padding-top: var(--lumo-space-m); margin-top: var(--lumo-space-xs);">Resource Management</h3>
 <vaadin-horizontal-layout theme="spacing-xl" id="topLayout" style="width: 100%; height: 10%; align-items: center; justify-content: center;">
  <vaadin-text-field style="width: 70%;" placeholder="Search Donor Name"></vaadin-text-field>
  <vaadin-button theme="icon" aria-label="Add new">
   <iron-icon icon="lumo:search"></iron-icon>
  </vaadin-button>
  <vaadin-select value="A+">
   <template>
    <vaadin-list-box selected="0">
     <vaadin-item selected>
       Item one 
     </vaadin-item>
     <vaadin-item>
       Item two 
     </vaadin-item>
     <vaadin-item>
       Item three 
     </vaadin-item>
    </vaadin-list-box>
   </template>
  </vaadin-select>
 </vaadin-horizontal-layout>
 <div style="width: 100%; height: 100%;">
  <slot name="grid"></slot>
 </div>
</div>
`;
    }

    static get is() {
        return 'bloodbank-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(BloodbankView.is, BloodbankView);
