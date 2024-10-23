// main.js
import { createPinia } from 'pinia';
import { createApp } from 'vue';
import App from './App.vue';
import './assets/main.css'; // Global CSS file
import router from './router'; // Vue Router setup

// Vuetify imports
import '@mdi/font/css/materialdesignicons.css'; // Material Design Icons
import { createVuetify } from 'vuetify'; // Vuetify setup
import * as components from 'vuetify/components'; // Vuetify components
import * as directives from 'vuetify/directives'; // Vuetify directives
import 'vuetify/styles'; // Vuetify styles

// Create Vuetify instance
const vuetify = createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'light', // Set the default theme
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
      dark: {
        colors: {
          primary: '#BB86FC',
          secondary: '#03DAC5',
          background: '#121212',
          surface: '#333',
          error: '#CF6679',
          success: '#03DAC6',
        },
      },
    },
  },
});

// Create Vue app instance
const app = createApp(App);

// Use plugins: Pinia, Router, and Vuetify
app.use(createPinia());
app.use(router);
app.use(vuetify);

// Mount the app to the #app div in index.html
app.mount('#app');
