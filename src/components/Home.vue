<!-- src/components/HomePage.vue -->
<template>

    <div class="home-page">
      <h1>Welcome to the our Home Page</h1>
      <p>This is a simple home page with a welcome message.</p>
    </div>
  </template>
  
  <script>
  export default {
    name: "Home",
  };
  </script>
  
  <style scoped>
  .home-page {
    text-align: center;
    margin-top: 50px;
  }
  
  h1 {
    font-size: 2.5rem;
    color: #6160b8; /* Vite/Vue theme color */
  }
  
  p {
    font-size: 1.2rem;
    color: #333;
  }
  </style>
  