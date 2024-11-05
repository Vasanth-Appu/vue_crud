<template>
  <NavBar @theme-light="themeimg" />

  <v-main>
   

      <div class="home-page text-center">
        <h1>Employee Details</h1> 

        <h4 v-if="addEmp">No data found Please Add Some data</h4>
        <v-btn color="blue" v-if="addEmp" @click="addEmployeeModal">Add New Emp</v-btn>
        <AddEmploye
                  :openModal="openModal"
                  @form-submit="handleFormSubmit"
                  @close-modal="closeModal"
                />

      </div>
      
      <v-container>
        <v-skeleton-loader 
      type="table"
      v-if="isLoading" 
      :loading="isLoading" 
    />
      <div class="justify-center themetable">
        <v-data-table
          :headers="Header"
          :items="Employee"
          class="elevation-1"
          :items-per-page="itemsPerPage"
          :page="page"
          hide-default-footer
          v-if="Employee.length > 0 && !fetchError"
          
        >
      
        
          <template v-slot:[`item.actions`]="{ item }">
            <v-btn rounded="xl" size="small" variant="outlined" @click="EditEmployeeModal(item)" class="mx-2" color="info" >Edit</v-btn>
            <v-btn rounded="xl" size="small" variant="outlined" @click="deleteEmployee (item)" class="mx-2" color="error">Delete</v-btn>
          </template>

          <template #top>
            <v-toolbar flat>
              <v-toolbar-title>Employees List</v-toolbar-title>
              <v-spacer></v-spacer>
              <div class="thbtn">
                <v-btn color="blue" variant="tonal" @click="addEmployeeModal" class="mx-2">Add Employee</v-btn>
                <AddEmploye
                  :openModal="openModal"
                  @form-submit="handleFormSubmit"
                  @close-modal="closeModal"
                />
                <v-btn color="#66BB6A" variant="tonal" @click="exportToExcel" class="mx-2">Export Excel</v-btn>
              </div>
            </v-toolbar>

          </template>

          <template #bottom>
            <v-pagination
              v-model="page"
              :length="Math.ceil(Employee.length / itemsPerPage)"
            ></v-pagination>
          </template>

          
        </v-data-table>

        <v-alert v-if="updateMsg" type="info" class="ma-4" dismissible>
          {{ updateMsg }}
        </v-alert>
        <v-snackbar 
  v-if="fetchMsg" 
  :timeout="30000"
  color="error" 
  class="ma-4 mt-16" 
    location="top center"
  v-model="fetchMsg"
  dismissible>
  {{ fetchMsg }} 
</v-snackbar>

        <v-alert v-if="saveMsg" type="success" class="ma-4" dismissible>
          {{ saveMsg }}
        </v-alert>
        <v-alert v-if="deleteMsg" type="success" class="ma-4" dismissible>
          {{ deleteMsg }}
        </v-alert>

        <!-- Place the EditEmp Component Here -->
        <EditEmp
          v-model:openModalEdit="openModalEdit"
          :employee="EditData"
          @close-modal="closeModalEdit"
          @UpdatedEmp="updatedEmp"
        />
      </div>
     
    </v-container>
   
    <template>
      <v-dialog v-model="confirmDeleteModal" max-width="400px" persistent>
  <v-card>
    <!-- Centering the Icon -->
    <v-card-title class="icon-center">
      <v-icon color="orange" size="68">mdi-alert-circle</v-icon> <!-- Exclamation icon -->
    </v-card-title>

    <v-card-text class="text-h6   text-center">
      Are you sure you want to delete this employee ID: 
      <strong>{{ employeeToDelete?.id }}</strong>? <!-- Display employee ID -->
    </v-card-text>

    <v-card-actions class="justify-center">
      <v-btn color="success" variant="outlined" rounded="lg" size="large" @click="confirmDelete">
        YES
      </v-btn>
      <v-btn color="red" variant="tonal" rounded="lg" size="large" @click="confirmDeleteClose">
        NO
      </v-btn>
    </v-card-actions>
  </v-card>
</v-dialog>

</template>


  </v-main>
</template>
<script setup>
import { onMounted, ref } from 'vue';
import * as XLSX from 'xlsx';
import { useCounterStore } from '../stores/empStore.js';
import AddEmploye from './AddEmploye.vue';
import EditEmp from './EditEmp.vue';
import NavBar from './NavBar.vue';

const Employee = ref([]);
const Header = ref([
  { title: 'Emp ID', key: 'id' },
  { title: 'Name', value: 'name' },
  { title: 'Email', value: 'email' },
  { title: 'Contact', value: 'contact' },
  { title: 'Address', value: 'address' },
  { title: 'Actions', value: 'actions', sortable: false },
]);
const deleteMsg=ref('');
const saveMsg = ref ('');
const fetchMsg=ref('');
const updateMsg = ref('');
const fetchError = ref(false);
const isLoading = ref(true);
const page = ref(1);
const itemsPerPage = ref(5);
const addEmp=ref(false);
const empStore = useCounterStore();//store instance


const fetchEmp = async () => {
  try {
    const response = await fetch('http://localhost:8080/getUser');
    
    if (!response.ok) throw new Error('Response is not OK');
    
  const data = await response.json();
  Employee.value=data;
//console.log(Employee.value);

    if (Employee.value <= 0 || Employee.value === null){
      addEmp.value=true;
    }else{
    empStore.setEmployee(Employee.value);// set pinia store
  }

  } catch (error) {
    console.error('Error fetching data: ', error);
    fetchMsg.value = 'Failed To Fetch';
    fetchError.value = true;
  } finally {
    isLoading.value = false;
  }
};

const handleFormSubmit = async (data) => {
  try {
    const response = await fetch(`http://localhost:8080/addEmployee`, {
      method: 'POST',
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (response.ok) {
      fetchEmp();
      saveMsg.value='Saved Successfully'
      setTimeout(()=>{
        saveMsg.value='';
      },3000);

    } else {
      console.log('Form submission failed');
    }
  } catch (error) {
    console.log('Network error:', error);
  }
};
const updatedEmp = async (updatedData) => {
  try {
    const response = await fetch(`http://localhost:8080/editEmployee/${updatedData.id}`, {
      method: 'PUT',
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify(updatedData),
    });
    if (response.ok) {
      fetchEmp();
      console.log('Employee updated successfully');
      fetchError.value=true;
updateMsg.value = 'Employee updated successfully';

      // Clear the message after 3 seconds
      setTimeout(() => {
        updateMsg.value = ''; 
        fetchError.value=false;
      }, 3000);     } else {
      console.log('Error updating employee');
    }
  } catch (error) {
    console.log('Network error:', error);
  }
};
//confrim Delete 
const confirmDeleteModal = ref(false);
const employeeToDelete = ref(null); // To store the employee being deleted

const deleteEmployee = (item) => {
  employeeToDelete.value = item; // Store the employee to delete
  confirmDeleteModal.value = true;
};

const confirmDeleteClose = () => {
  confirmDeleteModal.value = false;
  employeeToDelete.value = null; // Reset the employee being deleted
};

const confirmDelete = async () => {
  if (!employeeToDelete.value) return; // Ensure there is an employee to delete
  try {
    const response = await fetch(`http://localhost:8080/deleteEmployee/${employeeToDelete.value.id}`, {
      method: 'DELETE',
    });
    if (response.ok) {
      fetchEmp(); 
      deleteMsg.value='Deleted Successfully';
      setTimeout(() => {
        deleteMsg.value = ''; 
      }, 3000);

    } ;
    
  } catch (error) {
    console.log('Network error:', error);
  } finally {
    confirmDeleteClose(); // Close modal after action
  }
};


const openModal = ref(false);
const addEmployeeModal = () => (openModal.value = true);
const closeModal = () => (openModal.value = false);

const openModalEdit = ref(false);
const EditData = ref({});

const EditEmployeeModal = (item) => {
  openModalEdit.value = true;
  EditData.value = { ...item };
};

const closeModalEdit = () => (openModalEdit.value = false);


// Export Excel 
const exportToExcel = () => {
  const worksheet = XLSX.utils.json_to_sheet(Employee.value); // Convert JSON data to worksheet
  const workbook = XLSX.utils.book_new(); // Create a new workbook
  XLSX.utils.book_append_sheet(workbook, worksheet, 'Employee Data'); // Append sheet to workbook
  //generate excel for download
  XLSX.writeFile(workbook, 'EmployeeDetails.xlsx');
};
// const darkMode = ref( null );
// const themeimg = (isDark) => {
//   setTimeout(() => {
//     darkMode.value = isDark;
//     console.log("Theme changed:", darkMode.value ? 'Dark' : 'Light');
//     console.log(darkMode.value);
    
//   }, 2000);
// };s

// const modeIcon = computed(() => {
//   return darkMode.value
//     ? 'https://thumbs.dreamstime.com/z/waning-crescent-moon-clouds-isolated-transparent-background-stand-alone-your-own-design-333353858.jpg?ct=jpeg'   // Moon icon for dark mode
//     : '.../src/assets/sun.png';   // Sun icon for light mode
// });

onMounted(fetchEmp);

</script>
<style scoped>

.icon-center {
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
  height: 100px; /* Optional: Adjust height as needed */
}



 
 
</style>
