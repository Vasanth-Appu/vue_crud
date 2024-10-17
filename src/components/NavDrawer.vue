<!-- src/components/NavDrawer.vue -->
<template>
  <v-navigation-drawer v-model="localDrawer" color="green-darken-3" app>
    <v-list>
      <v-list-item-group>
        <v-list-item
          v-for="(item, index) in menuItems"
          :key="index"
          @click="handleNavigation(item.title)"
        >
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
</template>

<script setup>
import { defineEmits, defineProps, ref, watch } from 'vue';

// Define props and emit events
const props = defineProps({
  drawer: {
    type: Boolean,
    required: true,
  },
});
const emit = defineEmits(['closeDrawer']);

// Reactive state for the drawer
const localDrawer = ref(props.drawer);

// Sync the drawer prop with local state
watch(() => props.drawer, (newVal) => {
  localDrawer.value = newVal;
});

// Updated menu items
const menuItems = ref([
  { title: 'Home', icon: 'mdi-home' },
  { title: 'Profile', icon: 'mdi-account' },
  { title: 'Menu', icon: 'mdi-menu' },
  { title: 'Contact', icon: 'mdi-phone' },
  { title: 'Settings', icon: 'mdi-cog' },
]);

const handleNavigation = (title) => {
  console.log(`Navigating to: ${title}`);
  emit('closeDrawer'); // Close the drawer after clicking
};
</script>

<style scoped>
/* Optional: Custom styling */
</style>
