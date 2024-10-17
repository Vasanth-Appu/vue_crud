<!-- src/components/NavBar.vue -->
<template>
  <v-app-bar color="green-darken-3" app>
    <template v-slot:prepend>
      <v-app-bar-nav-icon @click="toggleDrawer" />
    </template>

    <v-app-bar-title>Application Bar</v-app-bar-title>
    <v-spacer />

    <v-btn text @click="goToHome">Home</v-btn>
    <v-btn text @click="goToAbout">About</v-btn>
    <v-btn text @click="goToContact">Contact</v-btn>
    <v-btn text @click="goToProfile">Profile</v-btn>
    <v-btn text @click="logout">Logout</v-btn>
  </v-app-bar>

  <!-- Passing `drawer` as a prop to NavDrawer -->
  <NavDrawer :drawer="drawer" @closeDrawer="drawer = false" />
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import NavDrawer from './NavDrawer.vue'; // Ensure correct path

const router = useRouter();

const drawer = ref(false);

const toggleDrawer = () => {
  drawer.value = !drawer.value;
  console.log('Drawer toggled:', drawer.value);
};

// Navigation actions
const goToHome = () => router.push('/');
const goToAbout = () => router.push('/about');
const goToContact = () => router.push('/contact');
const goToProfile = () => router.push('/profile');
const logout = () => console.log('User logged out');
</script>

<style scoped>
.v-app-bar {
  padding: 0 20px;
}
.v-toolbar-title {
  font-weight: 600;
}
</style>
