package com.springboot.employee_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee_management_system.dto.EmployeeDto;
import com.springboot.employee_management_system.entity.Employee;
import com.springboot.employee_management_system.services.EmployeeServices;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("empimp")
    private EmployeeServices employeeServices;

    @GetMapping("/home")
    public String homePage() {
        return "landing page of the employee management system...";
    }

    @PostMapping("/save-emp")
    public String saveEmployee(@RequestBody EmployeeDto employeeDto) {
        String msg = employeeServices.addEmployee(employeeDto);
        return msg;
    }

    @PostMapping("/delete-emp")
    public String deleteEmployee(@RequestParam("id") int id) {
        String msg = employeeServices.deleteEmployee(id);
        return msg;
    }

    @PostMapping("/match-employee")
    public String matchEmployee(@RequestParam String name, @RequestParam String email) {
        String msg = employeeServices.matchEmploye(name, email);
        return msg;
    }

    @PostMapping("/get-emp/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id) {
        return employeeServices.findEmployee(id);
    }

    @PutMapping("/update-employee/{id}")
    public String updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable("id") int id) {
        String msg = employeeServices.updateEmployee(employeeDto, id);
        return msg;
    }

}
