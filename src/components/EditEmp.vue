<template>
  <v-dialog v-model="internalModalVisible" max-width="600px" persistent>
    <v-card>
      <v-card-title class="headline">Edit Employee</v-card-title>
      <v-card-text>
        <v-form v-model="valid" ref="form">
          <v-container>
            <v-row>
              <v-col cols="12" md="12">
                <v-text-field
                  label="ID"
                  v-model="employeeData.id"
                  readonly
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="12">
                <v-text-field
                  label="Email"
                  v-model="employeeData.email"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="12">
                <v-text-field
                  label="Name"
                  v-model="employeeData.name"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="12">
                <v-text-field
                  label="Contact"
                  v-model="employeeData.contact"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="12">
                <v-text-field
                  label="Address"
                  v-model="employeeData.address"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-btn color="success" @click="submitForm">Update</v-btn>
        <v-btn color="error" @click="emitClose">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup>
import { defineEmits, defineProps, ref, watch } from 'vue';

const props = defineProps({
  openModalEdit: {
    type: Boolean,
    required: true,
  },
  employee: {
    type: Object,
    required: true,
  },
});

const emit = defineEmits(['close-modal','updatedEmp']);
const internalModalVisible = ref(props.openModalEdit);
const employeeData = ref({ ...props.employee }); // Clone employee data to avoid mutations
const valid = ref(false);

// Watch for changes in props to update modal visibility and employee data
watch(
  () => props.openModalEdit,
  (newValue) => {
    internalModalVisible.value = newValue;
    if (!newValue) resetForm(); // Reset form when modal closes
  }
);

watch(
  () => props.employee,
  (newData) => {
    employeeData.value = { ...newData }; // Update form data with the new employee
  }
);


const submitForm = ()=>{
  emit('updatedEmp', employeeData.value);
  emit('close-modal');
}


const emitClose = () => {
  internalModalVisible.value = false; // Hide the modal
  emit('close-modal'); // Emit the close event to parent
};

const resetForm = () => {
  employeeData.value = { ...props.employee }; // Reset to original data
};
</script>
