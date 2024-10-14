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
                  v-model="form.id"
                  :rules="[() => !v$.id.$invalid || 'Id is required']"
                  label="Id"
                  hide-details
                  required
                  @blur="v$.id.$touch()"
                ></v-text-field>
                <v-error-message v-if="v$.id.$invalid && v$.id.$touched">
                  {{ v$.id.$errors[0].$message }}
                </v-error-message>
              </v-col>

              <v-col cols="12" md="12">
                <v-text-field
                  v-model="form.email"
                  :rules="[() => !v$.email.$invalid || 'Email is required']"
                  label="Email"
                  hide-details
                  required
                  @blur="v$.email.$touch()" 
                ></v-text-field>
                <v-error-message v-if="v$.email.$invalid && v$.email.$touched">
                  {{ v$.email.$errors[0].$message }}
                </v-error-message>
              </v-col>

              <v-col cols="12" md="12">
                <v-text-field
                  v-model="form.name"
                  :rules="[() => !v$.name.$invalid || 'Name is required']"
                  label="Name"
                  hide-details
                  required
                  @blur="v$.name.$touch()"
                ></v-text-field>
                <v-error-message v-if="v$.name.$invalid && v$.name.$touched">
                  {{ v$.name.$errors[0].$message }}
                </v-error-message>
              </v-col>

              <v-col cols="12" md="12">
                <v-text-field
                  v-model="form.contact"
                  :rules="[() => !v$.contact.$invalid || 'Contact is required']"
                  label="Contact"
                  hide-details
                  required
                  @blur="v$.contact.$touch()" 
                ></v-text-field>
                <v-error-message v-if="v$.contact.$invalid && v$.contact.$touched">
                  {{ v$.contact.$errors[0].$message }}
                </v-error-message>
              </v-col>

              <v-col cols="12" md="12">
                <v-text-field
                  v-model="form.address"
                  :rules="[() => !v$.address.$invalid || 'Address is required']"
                  label="Address"
                  hide-details
                  required
                  @blur="v$.address.$touch()"
                ></v-text-field>
                <v-error-message v-if="v$.address.$invalid && v$.address.$touched">
                  {{ v$.address.$errors[0].$message }}
                </v-error-message>
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

<script setup>
import useVuelidate from '@vuelidate/core';
import { email, required } from '@vuelidate/validators';
import { computed, defineEmits, defineProps, reactive, ref, watch } from 'vue';

const props = defineProps({
  openModal: {
    type: Boolean,
    required: true,
  },
});
const emit = defineEmits(['close-modal','form-submit']);


const internalModalVisible = ref(props.openModal);

// Watch for changes in the prop and update internal state
watch(
  () => props.openModal,
  (newValue) => {
    internalModalVisible.value = newValue;
  }
);

// Form Data and Validation
const valid = ref(false);
const form = reactive({
  id: '',
  email: '',
  name: '',
  contact: '',
  address: '',
});

const rules = computed(()=>({
  id: { required },
  email: { required, email },
  name: { required },
  contact: { required },
  address: { required },
}));

const v$ = useVuelidate(rules, form);

const submitForm = () => {
  v$.value.$touch(); // Mark all fields as touched
  if (!v$.value.$invalid) {
    console.log('Form Data:', form);
    emit('form-submit', { ...form }); // Emit form data to parent
     resetForm();
    emitClose();
  } else {
    // Focus the first invalid field
    const firstInvalidField = Object.keys(v$.value).find(key => v$[key].$invalid);
    if (firstInvalidField) {
      const field = document.querySelector(`[v-model="form.${firstInvalidField}"]`);
      if (field) field.focus();
    }
    console.log('Form has errors.');
  }
};

const emitClose = () => {
  internalModalVisible.value = false; // Hide the modal
  emit('close-modal'); // Emit the close event
};
const resetForm = () => {
  form.id = '';
  form.email = '';
  form.name = '';
  form.contact = '';
  form.address = '';
  v$.value.$reset(); // Reset Vuelidate state
};
</script>

