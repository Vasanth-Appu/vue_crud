<!-- src/components/NavBar.vue -->
<template>
  <v-app-bar color="green-darken-3" app>
    <template v-slot:prepend>
      <v-app-bar-nav-icon @click="toggleDrawer" />
    </template>

    <v-app-bar-title>Abc Company</v-app-bar-title>
    <v-spacer />
    
    <v-btn text @click="goToHome">Home</v-btn>
    <v-btn text @click="goToAbout">About</v-btn>
    <v-btn text @click="goToContact">Contact</v-btn>
    <v-btn text @click="goToProfile">Profile</v-btn>
    <v-btn text @click="logout">Logout</v-btn>
    <v-switch
  v-model="isDark"
  @change="toggleTheme"
 class="mt-5 mr-4">
  <template #label>
    <v-icon>{{ isDark ? 'mdi-weather-night' : 'mdi-weather-sunny' }}</v-icon>
  </template>
</v-switch>

  </v-app-bar>

  <!-- Passing `drawer` as a prop to NavDrawer -->
  <NavDrawer :drawer="drawer" @closeDrawer="drawer = false" />
</template>

<script setup>
import { defineEmits, defineProps, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useTheme } from 'vuetify/lib/framework.mjs';
import NavDrawer from './NavDrawer.vue'; // Ensure correct path
const emit = defineEmits(['theme-light']);


const router = useRouter();

const drawer = ref(true);

const toggleDrawer = () => {
  drawer.value = !drawer.value;
};
// const props = defineProps({
//   opentoggle: {
//     type: Boolean,
//     required: true,
//   },
// });
//DrkMode
const theme = useTheme();
//const isDark = ref(localStorage.getItem('isDark') === 'true');
const isDark = ref(false);

const toggleTheme = () => {
  theme.global.name.value = isDark.value ? 'dark' : 'light';
  localStorage.setItem('isDark', isDark.value);
  emit('theme-light',isDark.value);

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
