<template>
  <NavBar />

  <v-main>
    <v-container>
      <div class="home-page text-center">
        <h1>Employee Details</h1>
      </div>

      <div class="justify-center">
        <v-data-table
          :headers="tHeader"
          :items="Employee"
          class="elevation-1"
          :loading="isLoading"
          :items-per-page="itemsPerPage"
          :page="page"
          hide-default-footer
          v-if="Employee.length > 0 && !fetchError"
          loading-text="Loading employee data..."
        >
          <template #item.actions ="{ item }">
            <v-btn icon @click="EditEmployeeModal()" class="mx-2" color="secondary">
              <v-icon>mdi-pencil</v-icon>
              <EditEmp
              :openModalEdit="openModalEdit"
              @close-modal="closeModalEdit"
              />
            </v-btn>
            <v-btn icon @click="deleteEmployee(item)" class="mx-2" color="error">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </template>
              


          <template #top>
            <v-toolbar flat>
              <v-toolbar-title>Employees List</v-toolbar-title>
              <v-spacer></v-spacer>
              <div>
                <v-btn color="primary" @click="addEmployeeModal" class="mx-2">
                  Add Employee
                </v-btn>
                <AddEmploye
                  :openModal="openModal"
                  @form-submit="handleFormSubmit"
                  @close-modal="closeModal"
                />
                <v-btn color="secondary" class="mx-2">
                  Export Excel
                </v-btn>
              </div>
            </v-toolbar>
          </template>

          <template #bottom>
            <v-pagination
              v-model="page"
              :length="Math.ceil(Employee.length / itemsPerPage)"
            ></v-pagination>
          </template>

          <template #no-data>
            <v-alert type="warning" class="ma-4">
              No employee data available.
            </v-alert>
          </template>
        </v-data-table>

        <v-alert v-if="fetchError" type="error" class="ma-4" dismissible>
          {{ setMsg }}
        </v-alert>
      </div>
    </v-container>
  </v-main>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import AddEmploye from './AddEmploye.vue';
import EditEmp from './EditEmp.vue';
import NavBar from './NavBar.vue';

const Employee = ref([]);
const tHeader = ref([
  { text: 'Emp ID', value: 'id' },
  { text: 'Name', value: 'name' },
  { text: 'Email', value: 'email' },
  { text: 'Contact', value: 'contact' },
  { text: 'Address', value: 'address' },
  { text: 'Actions', value: 'actions', sortable: false },
]);
const setMsg = ref('');
const fetchError = ref(false);
const isLoading = ref(true);
const page = ref(1);
const itemsPerPage = ref(5);

const fetchEmp = async () => { // FETCH DATA 
  try {
    const response = await fetch('http://localhost:8080/getUser');
    if (!response.ok) throw new Error('Response is not OK');
    Employee.value = await response.json();
  } catch (error) {
    console.error('Error fetching data: ', error);
    setMsg.value = 'Failed To Fetch';
    fetchError.value = true;
  } finally {
    isLoading.value = false;
  }
};
const handleFormSubmit = async (data) => {
  try{
    const response= await fetch ('http://localhost:8080/addEmployee',{ // POST DATA
      method:'POST',
      headers:{'content-type':'application/json'},
      body: JSON.stringify(data),
    });
    if(response.ok){
      fetchEmp();
      const result= await response.json();
      console.log('form submited',result);
    }else{
      console.log('not submitted');
      
    }

  }catch (error){
    console.log('Error Network',error);
  }
};

const editEmployee = (item) => {
  console.log('Edit employee:', item.id);
};

const deleteEmployee = async(item) => {
  try{
    const response= await fetch(`http://localhost:8080/deleteEmployee/${item.id}`,
      {
        method:'DELETE',
      });
      if(response.ok){
        fetchEmp();
        console.log('Deleted Successfully',item.id);
        }else{
          console.log('Deleting Some error occured');
          
        }
  }catch (error){
console.log('Network Error:',error);

  }
};

const openModal = ref(false);
const addEmployeeModal = () => {
  openModal.value = true; // Correctly set the openModal state
};

const closeModal = () => {
  openModal.value = false; // Properly close the modal
};

const openModalEdit=ref(false);

const EditEmployeeModal = () =>{
  openModalEdit.value=true;
  if(openModalEdit.value=true){
  console.log('edit modal is opened');
  }else{
    console.log('some error');
  }
};
const closeModalEdit = () => {
  openModalEdit.value = false; // Properly close the modal
};

onMounted(fetchEmp);
</script>
