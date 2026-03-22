package com.springboot.employee_management_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private Integer id;
    private String street;
    private String state;
    private String city;
    private String country;

}
