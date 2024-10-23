<template>
    <NavBar />
    <v-main>
    <div class="home-page text-center">
      <h1>profile</h1>
      <p>Welcome to the profile page!</p>
      <h3 style="color: blue;">Total Employee :  {{ empStore.totalEmp }}</h3>
    </div>
</v-main>
  </template>
  <script setup>
import { useCounterStore } from '../stores/empStore';
import NavBar from './NavBar.vue';

 const empStore=useCounterStore();


</script>
  