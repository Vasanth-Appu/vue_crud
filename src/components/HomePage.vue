<!-- src/components/HomePage.vue -->
<template>
  <NavBar />

  <v-main>
    <v-container>
      <div class="home-page">
        <h1>Employee Details</h1>
      </div>
      <div>
        <v-data-table
          :tHeader="tHeader"
          :Employee="Employee"
          class="elevation-1"
          
        >
        <h2>{{ setMsg }}</h2>
          <!-- <template v-slot:loading>
            <v-skeleton-loader type="table" />
          </template>
          <template v-slot:item.action="{ item }">
            <v-btn @click="viewDetails(item)">View</v-btn>
          </template> -->
        </v-data-table>
      </div>
    </v-container>
  </v-main>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import NavBar from './NavBar.vue';

const Employee =ref ([]);
const tHeader= ref([
  {text:'Emp ID',value:'id'},
  {text:'Name',value:'name'},
  {text:'Email',value:'email'},
  {text:'Contact',value:'contact'},
  {text:'Address',value:'address'},
]);
const setMsg=ref('');
const fetchEmp = async()=>{
  try{
const response = await fetch('http://localhost:8080/getUser');
if(!response.ok){
throw new error('Respone is Not Ok!!');
}
Employee.value = await response.json();
console.log(Employee);
  }catch (error){
    console.error('Error fecthing data: ',error)
    return setMsg.value='Failed To Fetch';
    

  }

};

onMounted(fetchEmp)
</script>

<style scoped>

</style>
