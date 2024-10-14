<template>
    
<v-dialog v-model="internalModalVisible" max-width="600px" persistent>
    <v-card>
      <v-card-title class="headline">Form Modal</v-card-title>

      <v-card-text>
        <v-form v-model="valid">
          <v-container>
            <v-row>
              <v-col cols="12" md="12">
                <v-text-field
                  label="Id"
                ></v-text-field>
                
              </v-col>

              <v-col cols="12" md="12">
                <v-text-field
                  label="Email"
                  
                ></v-text-field>
                
              </v-col>

              
            </v-row>
          </v-container>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-btn color="primary" @click="submitForm">Submit</v-btn>
        <v-btn color="secondary" @click="emitClose">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>


<script setup >
import { defineEmits, defineProps, ref, watch } from 'vue';
const props = defineProps({
    openModalEdit: {
    type: Boolean,
    required: true,
  },
});
const emit = defineEmits(['close-modal']);
const internalModalVisible = ref(props.openModalEdit);

// Watch for changes in the prop and update internal state
watch(
  () => props.openModalEdit,
  (newValue) => {
    internalModalVisible.value = newValue;
  }
);

const valid=ref(false);
const emitClose = () => {
  internalModalVisible.value = false; // Hide the modal
  emit('close-modal'); // Emit the close event
};
</script>
