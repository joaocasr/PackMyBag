import './assets/main.css';


import { createApp } from 'vue'

import PrimeVue from 'primevue/config';
import Aura from '@primevue/themes/aura';
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import VuePhoneNumberInput from 'vue-phone-number-input';

import store from './store';
import App from './App.vue'
import router from './router'

const app = createApp(App)
app.use(store);
app.use(router)
app.use(PrimeVue, {
    theme: {
        preset: Aura
    }
});

app.use(VueSweetalert2);
app.use(VuePhoneNumberInput);

app.config.globalProperties.$apiGatewayUrl = import.meta.env.VITE_GATEWAY_DOMAIN;

app.mount('#app')
