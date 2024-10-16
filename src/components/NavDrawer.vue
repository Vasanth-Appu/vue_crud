<template>
    <v-app>
      <!-- App Bar -->
      <v-app-bar app>
        <v-app-bar-nav-icon @click="drawer = !drawer" />
        <v-toolbar-title>My App</v-toolbar-title>
      </v-app-bar>
  
      <!-- Navigation Drawer -->
      <v-navigation-drawer v-model="drawer" app>
        <v-list>
          <v-list-item-group>
            <v-list-item v-for="(item, index) in menuItems" :key="index" gi>
              <v-list-item-icon>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-navigation-drawer>
  
      <!-- Main Content -->
      <v-main>
        <v-container fluid>
          <h1>Welcome to My Vuetify App</h1>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script>
  export default {
    data() {
      return {
        drawer: false, // Controls drawer visibility
        menuItems: [
          { title: "Home", icon: "mdi-home" },
          { title: "Profile", icon: "mdi-account" },
          { title: "Settings", icon: "mdi-cog" },
        ],
      };
    },
  };
  </script>
  
  <style>
  /* Optional: Custom styling */
  </style>
  