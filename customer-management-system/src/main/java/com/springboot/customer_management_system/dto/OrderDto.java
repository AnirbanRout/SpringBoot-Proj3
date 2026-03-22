package com.springboot.customer_management_system.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Valid
@Data
public class OrderDto {

    @NotEmpty(message = "enter the order name")
    private String orderName;

    @NotNull(message = "enter the order price")
    private Double price;

}
