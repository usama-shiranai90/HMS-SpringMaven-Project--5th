import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';


class DoctorDetailView extends PolymerElement {

    static get template() {
        return html`
            <style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
        `;
    }

    static get is() {
        return 'doctor-detail-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(DoctorDetailView.is, DoctorDetailView);
