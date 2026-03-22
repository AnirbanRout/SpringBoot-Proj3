package com.springboot.employee_management_system.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {

    private String name;
    private String email;
    private Integer age;
    private Double salary;
    private List<AddressDto> addresses;

}
