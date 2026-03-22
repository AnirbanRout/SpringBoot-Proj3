package com.springboot.employee_management_system.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public Map<String, String> employeeNotFoundHandler(EmployeeNotFoundException exp) {
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("error message:", exp.getMessage());

        return errorMap;
    }

}
