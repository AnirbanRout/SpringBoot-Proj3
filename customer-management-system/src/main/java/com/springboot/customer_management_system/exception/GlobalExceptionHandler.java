package com.springboot.customer_management_system.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String, String> customerNotFoundHandler(CustomerNotFoundException exp) {

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errro message:", exp.getMessage());

        return errorMap;

    }

}
