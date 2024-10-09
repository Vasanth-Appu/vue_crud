<template>
    <v-app-bar app color="primary" dark>
      <!-- Left side: Logo and Title -->
      <v-app-bar-nav-icon @click="toggleDrawer"></v-app-bar-nav-icon>
      <v-toolbar-title class="ml-3">My Application</v-toolbar-title>
  
      <!-- Center: Spacer -->
      <v-spacer></v-spacer>
  
      <!-- Right side: Navigation links and actions -->
      <v-btn text @click="goToHome">
        <v-icon left>mdi-home</v-icon>
        Home
      </v-btn>
      <v-btn text @click="goToAbout">
        <v-icon left>mdi-information</v-icon>
        About
      </v-btn>
      <v-btn text @click="goToContact">
        <v-icon left>mdi-phone</v-icon>
        Contact
      </v-btn>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn text v-bind="attrs" v-on="on">
            <v-icon left>mdi-account</v-icon>
            Profile
            <v-icon right>mdi-menu-down</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item @click="goToProfile">
            <v-list-item-title>My Profile</v-list-item-title>
          </v-list-item>
          <v-list-item @click="logout">
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </template>
  
  <script>
  export default {
    name: 'NavBar',
    methods: {
      toggleDrawer() {
        // Logic for toggling sidebar or drawer
      },
      goToHome() {
        this.$router.push('/');
      },
      goToAbout() {
        this.$router.push('/about');
      },
      goToContact() {
        this.$router.push('/contact');
      },
      goToProfile() {
        this.$router.push('/profile');
      },
      logout() {
        // Handle logout logic
        console.log("User logged out");
      }
    }
  }
  </script>
  
  <style scoped>
  .v-app-bar {
    padding: 0 20px;
  }
  .v-toolbar-title {
    font-weight: 600;
  }
  </style>
  