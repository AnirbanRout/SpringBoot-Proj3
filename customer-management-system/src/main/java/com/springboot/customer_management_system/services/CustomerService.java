package com.springboot.customer_management_system.services;

import com.springboot.customer_management_system.dto.CustomerDto;
import com.springboot.customer_management_system.entity.Customer;

public interface CustomerService {

    String addCustomer(CustomerDto customerDto);

    String deleteCustomer(int id);

    String matchCustomer(String name, String email);

    String updateCustomer(int id, CustomerDto customerDto);

    Customer getCustomerById(int id);

}
