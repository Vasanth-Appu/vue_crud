import { createPinia } from 'pinia';
import { createApp } from 'vue';
import App from './App.vue';
import './assets/main.css'; // Your global CSS file
import router from './router'; // Your Vue Router setup

// Import Vuetify and styles
import '@mdi/font/css/materialdesignicons.css'; // Import Material Design Icons
import { createVuetify } from 'vuetify'; // Import createVuetify
import * as components from 'vuetify/components'; // Import all Vuetify components
import * as directives from 'vuetify/directives'; // Import all Vuetify directives
import 'vuetify/styles'; // Import Vuetify styles

// Create a Vuetify instance
const vuetify = createVuetify({
  components, // Register components
  directives, // Register directives
  theme: {
    defaultTheme: 'light', // Set the default theme to light or dark
    themes: {
      light: {
        colors: {
          primary: '#1976D2',
          secondary: '#424242',
          accent: '#82B1FF',
          error: '#FF5252',
          info: '#2196F3',
          success: '#4CAF50',
          warning: '#FFC107',
        },
      },
    },
  },
});

// Create the Vue app instance
const app = createApp(App);

// Use Pinia and Vue Router
app.use(createPinia());
app.use(router);
app.use(vuetify); // Use Vuetify

// Mount the app to the DOM
app.mount('#app');
