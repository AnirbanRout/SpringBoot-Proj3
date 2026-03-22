package com.springboot.customer_management_system.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.customer_management_system.dto.CustomerDto;
import com.springboot.customer_management_system.entity.Customer;
import com.springboot.customer_management_system.entity.Order;
import com.springboot.customer_management_system.exception.CustomerNotFoundException;
import com.springboot.customer_management_system.repository.CustomerRepository;

@Service("cusimp")
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String addCustomer(CustomerDto customerDto) {

        Customer customer = Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .age(customerDto.getAge())
                .build();

        Order order = Order.builder()
                .orderName(customerDto.getOrderDto().getOrderName())
                .price(customerDto.getOrderDto().getPrice())
                .build();

        customer.setOrder(order);
        order.setCustomer(customer);

        customerRepository.save(customer);

        return "customer data saved successfully...";

    }

    @Override
    public String deleteCustomer(int id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customerRepository.delete(customer);

            return "customer data deleted with id:" + customer.getId();
        }

        return "no customer found with this id";

    }

    @Override
    public String matchCustomer(String name, String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByName(name);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            if (customer.getEmail().equals(email)) {
                return "credentails verified...";
            } else {
                return "wrong email...";
            }
        }

        return "no customer found with this name:" + name;
    }

    @Override
    public String updateCustomer(int id, CustomerDto customerDto) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            if (customerDto.getName() != null) {
                customer.setName(customerDto.getName());
            }

            if (customerDto.getEmail() != null) {
                customer.setEmail(customerDto.getEmail());
            }

            if (customerDto.getAge() != 0) {
                customer.setAge(customerDto.getAge());
            }

            if (customerDto.getOrderDto() != null) {
                Order existingOrder = customer.getOrder();

                if (existingOrder != null) {
                    if (customerDto.getOrderDto().getOrderName() != null) {
                        existingOrder.setOrderName(customerDto.getOrderDto().getOrderName());
                    }
                    if (customerDto.getOrderDto().getPrice() != 0) {
                        existingOrder.setPrice(customerDto.getOrderDto().getPrice());
                    }
                }

                else {
                    Order order = Order.builder()
                            .orderName(customerDto.getOrderDto().getOrderName())
                            .price(customerDto.getOrderDto().getPrice())
                            .build();

                    customer.setOrder(order);
                    order.setCustomer(customer);
                }

            }

            customerRepository.save(customer);
            return "customer data updated successfully...";

        }

        return "no customer found with this id:" + id;

    }

    @Override
    public Customer getCustomerById(int id) {

        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("no customer found with this id:" + id));

    }

}
