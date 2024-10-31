import './assets/main.css';

import { createApp } from 'vue'

import PrimeVue from 'primevue/config';
import Aura from '@primevue/themes/aura';
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import vueCountryRegionSelect from 'vue3-country-region-select'

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
app.use(vueCountryRegionSelect)
app.mount('#app')
