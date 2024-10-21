<!-- src/components/NavDrawer.vue -->
<template>
  <v-navigation-drawer v-model="localDrawer" color="green-darken-3" app>
    <v-list>
      <v-list-item-group>
        <v-list-item
          v-for="(item, index) in menuItems"
          :key="index"
          :class="{ 'v-list-item--active': isActive(item.route) }"
          @click="handleNavigation(item.route)"
        >
          <v-list-item-content class="d-flex align-center">
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-title class="ml-3">
              <router-link
                :to="item.route || '/'"
                class="text-decoration-none"
              >
                {{ item.title }}
              </router-link>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
</template>

<script setup>
import { defineEmits, defineProps, ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router'; // Import Vue Router's useRouter

// Define props and emit events
const props = defineProps({
  drawer: {
    type: Boolean,
    required: true,
  },
});
const emit = defineEmits(['closeDrawer']);

// Initialize Vue Router
const router = useRouter();
const route=useRoute();

// Reactive state for the drawer
const localDrawer = ref(props.drawer);

// Sync the drawer prop with local state
watch(() => props.drawer, (newVal) => {
  localDrawer.value = newVal;
});

// Menu items with routes
const menuItems = ref([
  { title: 'Home', icon: 'mdi-home', route: '/' },
  { title: 'Profile', icon: 'mdi-account', route: '/profile' },
  { title: 'Menu', icon: 'mdi-menu', },
  { title: 'Contact', icon: 'mdi-phone', },
  { title: 'Settings', icon: 'mdi-cog' },
]);

const handleNavigation = (route) => {
  
    router.push(route).catch((err) => console.error(err)); // Ensure navigation happens
   // emit('closeDrawer'); // Close the drawer
  
};
const isActive = (itemRoute) => {
  return route.path === itemRoute; // Compare the current route path with the item route
};
</script>

<style scoped>
.v-list-item--active {
  background-color: rgba(255, 255, 255, 0.1); /* Optional: Customize the active item background color */
}
</style>
