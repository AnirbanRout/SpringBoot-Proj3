package com.springboot.customer_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.customer_management_system.dto.CustomerDto;
import com.springboot.customer_management_system.entity.Customer;
import com.springboot.customer_management_system.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    @Qualifier("cusimp")
    private CustomerService customerService;

    @GetMapping("/home")
    public String home() {
        return "home page of the customer management system...";
    }

    @PostMapping("/save-customer")
    public String addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        String msg = customerService.addCustomer(customerDto);
        return msg;
    }

    @PostMapping("/match-customer")
    public String matchCustomer(@RequestParam String name, @RequestParam String email) {
        String msg = customerService.matchCustomer(name, email);
        return msg;
    }

    @DeleteMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        String msg = customerService.deleteCustomer(id);
        return msg;
    }

    @PutMapping("/update-customer")
    public String updateCustomer(@RequestParam("id") int id, @Valid @RequestBody CustomerDto customerDto) {
        String msg = customerService.updateCustomer(id, customerDto);
        return msg;
    }

    @PostMapping("/get-order")
    public Customer getOrderById(@RequestParam("id") int id) {
        return customerService.getCustomerById(id);
    }

}
