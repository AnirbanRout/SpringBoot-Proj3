package com.springboot.customer_management_system.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String msg) {
        super(msg);
    }

}
