// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
 <style>
        html {
      /*--lumo-font-family: Corbel, "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", "DejaVu Sans", "Bitstream Vera Sans", "Liberation Sans", Verdana, "Verdana Ref", sans-serif;*/
      /* font-family: ;*/
      --lumo-font-family: 'Roboto', 'Noto', sans-serif;
      --lumo-border-radius: calc(var(--lumo-size-m)/3);
      --lumo-size-xl: 4rem;
      --lumo-size-l: 3rem;
      --lumo-size-m: 2.5rem;
      --lumo-size-s: 2rem;
      --lumo-size-xs: 1.75rem;
      --lumo-space-xl: 1.875rem;
      --lumo-space-l: 1.25rem;
      --lumo-space-m: 0.625rem;
      --lumo-space-s: 0.3125rem;
      --lumo-space-xs: 0.1875rem;
      --lumo-primary-color: hsl(203,33%,36%);
      --lumo-error-color: hsl(3, 100%, 50%);

    }

</style>
</custom-style>


<custom-style>
  <style>
    html {
      overflow:hidden;
    }
    
   #userlogo{
       size: 100px;
       background-color: #000003;
   } 
   
   #icons{
   position:absolute;
   margin-left: 85%;
    padding-right: 20px;
    padding-left: 20px;
   }
   
   
   #progressbar{
   width: 20%;
   position:relative;
   padding-left: 20px;
   }
   
   #option{
   padding-left: 570px;
   }
   #logo{
   background-color:#2A3147 ;
   }
  </style>
</custom-style>

<dom-module id="app-layout" theme-for="vaadin-app-layout">
  <template>
    <style>
      :host(:not([dir='rtl']):not([overlay])) [part='drawer'] {
        border-right: none;
        box-shadow: var(--lumo-box-shadow-s);
        background-color: var(--lumo-base-color);
        z-index: 1;
      }
      :host([dir='rtl']:not([overlay])) [part='drawer'] {
        border-left: none;
        box-shadow: var(--lumo-box-shadow-s);
        background-color: var(--lumo-base-color);
        z-index: 1;
      }
      [part='navbar'] {
        box-shadow: var(--lumo-box-shadow-s);
      }
    </style>
  </template>
</dom-module>`;

document.head.appendChild($_documentContainer.content);
