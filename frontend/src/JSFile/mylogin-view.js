import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-login/src/vaadin-login-form.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';

class MyloginView extends PolymerElement {

    static get template() {
        return html`
            <style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }

                #topic {
                    font-family: 'DejaVu Sans Mono', monospace;
                    font-size: xx-large;
                    color: white;
                }

                #icon {
                    height: 50px;
                    width: 50px;

                }

                #icon:hover {
                    color: rgba(63, 50, 237, 0.75);
                }


                #header {
                    /*background-color: rgba(232, 235, 239);*/
                    background-color: rgb(51, 51, 51);
                }

                #top {
                    background-color: rgb(51, 51, 51);
                }

                .content {
                    position: relative;

                }

                .form {
                    position: absolute;
                    align-self: center;
                    flex-grow: 1;
                    margin-top: var(--lumo-space-xl);
                    flex-shrink: 1;
                }

                #ironIcon {
                    transition: width 2s, height 2s, transform 2s;
                }

                #ironIcon:hover {
                    width: 100px;
                    height: 100px;
                    transform: rotate(360deg);
                }

                #leftcontainer {
                    /*background-image: url("../Images/1.png");*/
                    background-color: #333333;

                    -webkit-transition: all 5s linear;
                    -moz-transition: all 5s linear;
                    -ms-transition: all 5s linear;
                    -o-transition: all 5s linear;
                    transition: all 5s linear;
                }

                #leftcontainer:hover {
                    background-color: #2b908f;

                }


                #rightcontainer {
                    background-image: url("../Images/Hospital1.jpg");
                    background-position: center;
                    background-repeat: no-repeat;
                    opacity: 1;
                    border-top: groove #4f3737;
                    box-shadow: 5px 10px 3px #cfcfcf;
                }

                #rightcontainer:target {
                    -webkit-transition: opacity 2.0s ease-in;
                    -moz-transition: opacity 2.0s ease-in;
                    -o-transition: opacity 2.0s ease-in;
                    opacity: 1;
                }

                #logo {
                    background-image: url("../Images/logo.png");
                    background-repeat: no-repeat;
                }

                .wrapper .name_container {
                    position: absolute;
                    left: 50%;
                    top: 50%;
                    transform: translate(-50%, -50%);
                    width: 80vmin;
                    height: 80vmin;
                    background: rgba(255, 255, 255, 0.1);
                    border-radius: 100%;
                    box-shadow: inset 0px 0px 30px 30px rgba(200, 200, 200, 0.1);
                    display: flex;
                    flex-direction: column;
                    justify-content: center;
                    align-items: center;

                }

                div {
                    font-family: 'Pacifico', cursive;
                    color: rgba(255, 255, 255, 0.7);
                }


                .wra .name {
                    font-size: 5.5vmax;
                }

                .designation {
                    margin-top: 10px;
                    font-size: 2vmax;
                }

                @keyframes scaleUpDown {
                    0%,
                    100% {
                        transform: scaleY(1) scaleX(1);
                    }
                    50%,
                    90% {
                        transform: scaleY(1.1);
                    }
                    75% {
                        transform: scaleY(0.95);
                    }
                    80% {
                        transform: scaleX(0.95);
                    }
                }

                @keyframes shake {
                    0%,
                    100% {
                        transform: skewX(0) scale(1);
                    }
                    50% {
                        transform: skewX(5deg) scale(0.9);
                    }
                }

                @keyframes particleUp {
                    0% {
                        opacity: 0;
                    }
                    20% {
                        opacity: 1;
                    }
                    80% {
                        opacity: 1;
                    }
                    100% {
                        opacity: 0;
                        top: -100%;
                        transform: scale(0.5);
                    }
                }

                @keyframes glow {
                    0%,
                    100% {
                        background-color: #358894;
                    }
                    50% {
                        background-color: #358894;
                    }
                }

                .fire {
                    position: absolute;
                    /*top: calc(50% - 50px);*/
                    left: calc(50% - 50px);

                    width: 70px;
                    height: 70px;
                    background-color: transparent;
                    margin-left: auto;
                    margin-right: auto;
                }

                .fire-main {
                    position: absolute;
                    height: 100%;
                    width: 100%;
                    animation: scaleUpDown 3s ease-out;
                    animation-iteration-count: infinite;
                    animation-fill-mode: both;
                }

                .fire-main .main-fire {
                    position: absolute;
                    width: 100%;
                    height: 100%;
                    background-image: radial-gradient(farthest-corner at 10px 0, #358894 0%, #4ec6d7 95%);
                    transform: scaleX(0.8) rotate(45deg);
                    border-radius: 0 40% 60% 40%;
                    filter: drop-shadow(0 0 10px #358894);
                }

                .fire-main .particle-fire {
                    position: absolute;
                    top: 60%;
                    left: 45%;
                    width: 10px;
                    height: 10px;
                    background-color: #4ec6d7;
                    border-radius: 50%;
                    filter: drop-shadow(0 0 10px #358894);
                    animation: particleUp 2s ease-out 0;
                    animation-iteration-count: infinite;
                    animation-fill-mode: both;
                }

                .fire-right {
                    height: 100%;
                    width: 100%;
                    position: absolute;
                    animation: shake 2s ease-out 0;
                    animation-iteration-count: infinite;
                    animation-fill-mode: both;
                }

                .fire-right .main-fire {
                    position: absolute;
                    top: 15%;
                    right: -25%;
                    width: 80%;
                    height: 80%;
                    background-color: #4ec6d7;
                    transform: scaleX(0.8) rotate(45deg);
                    border-radius: 0 40% 60% 40%;
                    filter: drop-shadow(0 0 10px #358894);
                }

                .fire-right .particle-fire {
                    position: absolute;
                    top: 45%;
                    left: 50%;
                    width: 15px;
                    height: 15px;
                    background-color: #4ec6d7;
                    transform: scaleX(0.8) rotate(45deg);
                    border-radius: 50%;
                    filter: drop-shadow(0 0 10px #358894);
                    animation: particleUp 2s ease-out 0;
                    animation-iteration-count: infinite;
                    animation-fill-mode: both;
                }

                .fire-left {
                    position: absolute;
                    height: 100%;
                    width: 100%;
                    animation: shake 3s ease-out 0;
                    animation-iteration-count: infinite;
                    animation-fill-mode: both;
                }

                .fire-left .main-fire {
                    position: absolute;
                    top: 15%;
                    left: -20%;
                    width: 80%;
                    height: 80%;
                    background-color: #4ec6d7;
                    transform: scaleX(0.8) rotate(45deg);
                    border-radius: 0 40% 60% 40%;
                    filter: drop-shadow(0 0 10px #358894);
                }

                .fire-left .particle-fire {
                    position: absolute;
                    top: 10%;
                    left: 20%;
                    width: 10%;
                    height: 10%;
                    background-color: #4ec6d7;
                    border-radius: 50%;
                    filter: drop-shadow(0 0 10px #358894);
                    animation: particleUp 3s infinite ease-out 0;
                    animation-fill-mode: both;
                }

                .fire-bottom .main-fire {
                    position: absolute;
                    top: 30%;
                    left: 20%;
                    width: 75%;
                    height: 75%;
                    background-color: #68c9db;
                    transform: scaleX(0.8) rotate(45deg);
                    border-radius: 0 40% 100% 40%;
                    filter: blur(10px);
                    animation: glow 2s ease-out 0;
                    animation-iteration-count: infinite;
                    animation-fill-mode: both;
                }
            </style>


            <vaadin-vertical-layout style="width: 100%; height: 100%;">
                <vaadin-horizontal-layout id="top"
                                          style="align-self: stretch; align-items: center; justify-content: flex-start; height: 10%;"
                                          theme="spacing-xl">
                    <div id="logo"
                         style="flex-grow: 0; flex-shrink: 0; align-self: stretch; width: 20%; height: 100%; margin-right: var(--lumo-space-xl); margin-left: var(--lumo-space-xl);"></div>
                    <!--  <h1>Hospital Management System</h1>-->
                    <div class="wrapper" style="align-self: center; margin-right: var(--lumo-space-xl);">
                        <div id="name_container" style="width: 100%; height: 100%; margin-right: var(--lumo-space-xl);">
                            <div class="designation" style="height: 100%; width: 100%;">
                                Hospital Management System
                            </div>
                        </div>
                    </div>
                    <div class="fire"
                         style="flex-shrink: 1; align-self: center; flex-grow: 1; margin-left: 100px; margin-top: var(--lumo-space-m);">
                        <div class="fire-left">
                            <div class="main-fire"></div>
                            <div class="particle-fire"></div>
                        </div>
                        <div class="fire-main">
                            <div class="main-fire"></div>
                            <div class="particle-fire"></div>
                        </div>
                        <div class="fire-right">
                            <div class="main-fire"></div>
                            <div class="particle-fire"></div>
                        </div>
                        <div class="fire-bottom">
                            <div class="main-fire"></div>
                        </div>
                        <br>
                        <br>
                    </div>
                </vaadin-horizontal-layout>
                <vaadin-horizontal-layout
                        style="flex-grow: 1; flex-shrink: 1; flex-basis: auto; align-self: stretch; justify-content: space-around; align-items: center;">
                    <vaadin-vertical-layout theme="spacing" style=" align-self: stretch; 
                   flex-grow: 1; 
                   justify-content: center;
                    align-items: center;
                    /*border-style:solid;*/
                     border-width:1px; 
                     /*border-color: #efe1e1;*/
                    border-top: groove #4f3737;
                    box-shadow: 5px 10px 3px #cfcfcf;
                     
                     " id="leftcontainer">
                        <label style="   font-family:'Mongolian Baiti',serif; font-size: x-large; color: white ; font-size: 35px">Welcome
                            Back</label>
                        <h5 style="color: #c4c4c4 ;font-size: 14px">Login into your account using userid and
                            password</h5>
                        <iron-icon style="align-self: center; flex-grow: 0; flex-shrink: 0; color: #91e8e1"
                                   icon="vaadin:user-star" id="ironIcon"></iron-icon>
                        <vaadin-login-form id="adminlogin" style="flex-grow: 0; flex-shrink: 1;"></vaadin-login-form>
                    </vaadin-vertical-layout>
                    <vaadin-vertical-layout theme="spacing" style=" align-self: stretch; 
                   flex-grow: 1; 
                   justify-content: center;
                    align-items: center;
                    /*border-style:solid;*/
                      /*border-color: #efe1e1;*/
                     border-width:1px 1px 1px 0px ; 
                  border-top: groove #4f3737;
                  border-left: groove #4f3737;
                    box-shadow: 5px 10px 3px #cfcfcf;
                     
                     
                     " id="rightcontainer"></vaadin-vertical-layout>
                </vaadin-horizontal-layout>
                <vaadin-horizontal-layout class="footer"
                                          style="width: 100%; flex-basis: var(--lumo-size-l); flex-shrink: 0; background-color: var(--lumo-contrast-10pct);">
                    <a href="https://vaadin.com"
                       style="flex-grow: 1; flex-shrink: 0; align-self: center; text-align:center">
                        <footer>
                            © Copyright 2020 OneEyeOwl. All Rights Reserved
                        </footer>
                    </a>
                </vaadin-horizontal-layout>
            </vaadin-vertical-layout>
        `;
    }

    static get is() {
        return 'mylogin-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(MyloginView.is, MyloginView);
