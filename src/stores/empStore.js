import { defineStore } from 'pinia';
import { computed, ref } from 'vue';

export const useCounterStore = defineStore('empCount', () => {
    const Employee = ref([]);

    const totalEmp = computed(() => Employee.value.length);
    
    const setEmployee =  (Employees) => {
        // Employee.value=[...Employees];
        Employee.value=Employees;

    };

    return { Employee, totalEmp, setEmployee };
});
