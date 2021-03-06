import {PolymerElement} from '@polymer/polymer/polymer-element.js';
import {html} from '@polymer/polymer/lib/utils/html-tag.js';
import '@vaadin/vaadin-board/vaadin-board.js';
import '@vaadin/vaadin-lumo-styles/all-imports.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-charts/vaadin-chart.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';

class DashboardView extends PolymerElement {
    static get template() {
        return html`
<style include="shared-styles lumo-badge lumo-typography">
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
                    /*background-color: #FFFFFF;*/
                    padding: var(--lumo-space-s);
                    /*border-radius: var(--lumo-border-radius);*/
                    /*box-shadow: var(--lumo-box-shadow-xs);*/
                    /*overflow: hidden;*/
                    /*flex-direction: column;*/
                }
                
                /* Wrapper */
                .wrapper {
                    display: flex;
                    padding: var(--lumo-space-s);
                }

                /* Card */
                .card {
                    align-items: baseline;
                    background-color: var(--lumo-base-color);
                    border-radius: var(--lumo-border-radius);
                    box-shadow: var(--lumo-box-shadow-xs);
                    display: flex;
                    flex-direction: column;
                    overflow: hidden;
                    width: 100%;
                }

                .card h2 {
                    margin-bottom: 0;
                    margin-top: var(--lumo-space-m);
                }

                .card h3 {
                    margin-bottom: var(--lumo-space-xs);
                    margin-right: var(--lumo-space-m);
                    margin-left: var(--lumo-space-m);
                    margin-top: var(--lumo-space-m);
                }

                /* Spacing */
                .space-m {
                    padding: var(--lumo-space-m);
                }

                /* Text colors */
                .error-text {
                    color: var(--lumo-error-text-color);
                }

                .primary-text {
                    color: var(--lumo-primary-text-color);
                }

                .secondary-text {
                    color: var(--lumo-secondary-text-color);
                }

                .success-text {
                    color: var(--lumo-success-text-color);
                }

                /* Charts */
                vaadin-chart {
                    padding-top: var(--lumo-space-s);
                }

                /* Grid */
                vaadin-grid {
                    height: 300px;
                }
            </style>
<vaadin-board></vaadin-board>
<div id="layout">
 <div class="card space-m">
  <span theme="badge" style="align-self: center; flex-grow: 0;">BillBoard</span>
  <div style="align-self: stretch;">
   <vaadin-button style="align-self: flex-start; margin-right: var(--lumo-space-xl);">
    <iron-icon icon="lumo:arrow-left" slot="suffix">
      Next 
    </iron-icon>
   </vaadin-button>
   <vaadin-horizontal-layout style="justify-content: space-between;" theme="spacing-xl">
    <vaadin-button style="flex-grow: 1;">
      Button 
    </vaadin-button>
    <vaadin-button style="flex-grow: 1;">
      Button 
    </vaadin-button>
    <vaadin-button style="flex-grow: 1;">
      Button 
    </vaadin-button>
    <vaadin-button style="flex-grow: 1;">
      Button 
    </vaadin-button>
    <vaadin-button style="flex-grow: 1;">
      Button 
    </vaadin-button>
    <vaadin-button style="flex-grow: 1;">
      Button 
    </vaadin-button>
   </vaadin-horizontal-layout>
   <vaadin-button style="margin-left: 830px " id="nextbutton">
    <iron-icon icon="lumo:arrow-right" slot="suffix"></iron-icon>
   </vaadin-button>
  </div>
 </div>
 <vaadin-board-row>
  <div class="wrapper">
   <div class="card space-m">
    <span theme="badge">Admins</span>
    <h2 class="primary-text">[[currentUsers]]</h2>
    <span class="secondary-text">Current users in the app</span>
   </div>
  </div>
  <div class="wrapper">
   <div class="card space-m">
    <span theme="badge success">Admitted Patients</span>
    <h2 class="success-text">[[numEvents]]</h2>
    <span class="secondary-text">View overall patient </span>
   </div>
  </div>
  <div class="wrapper">
   <div class="card space-m">
    <span theme="badge error">Appointment</span>
    <h2 class="error-text">[[conversionRate]]% More Patients</h2>
    <span class="secondary-text">Monthly bases</span>
   </div>
  </div>
 </vaadin-board-row>
 <div class="wrapper">
  <div class="card">
   <vaadin-chart type="column" id="monthlyVisitors" title="Monthly visitors per city"></vaadin-chart>
  </div>
 </div>
 <vaadin-board-row>
  <div class="wrapper">
   <div class="card">
    <h3>Service health</h3>
    <vaadin-grid id="grid" theme="no-border"></vaadin-grid>
   </div>
  </div>
  <div class="wrapper">
   <div class="card">
    <vaadin-chart id="responseTimes" title="Response times"></vaadin-chart>
   </div>
  </div>
 </vaadin-board-row>
</div>
`;
    }

    static get is() {
        return 'dashboard-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(DashboardView.is, DashboardView);
