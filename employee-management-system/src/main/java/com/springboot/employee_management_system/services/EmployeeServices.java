package com.springboot.employee_management_system.services;

import com.springboot.employee_management_system.dto.EmployeeDto;
import com.springboot.employee_management_system.entity.Employee;

public interface EmployeeServices {

    String addEmployee(EmployeeDto employeeDto);

    String deleteEmployee(int id);

    String matchEmploye(String name, String email);

    Employee findEmployee(int id);

    String updateEmployee(EmployeeDto employeeDto, int id);

}
