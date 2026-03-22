package com.springboot.customer_management_system.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Valid
@Data
public class CustomerDto {

    @NotEmpty(message = "enter customer name")
    private String name;

    @Email(message = "enter valid email")
    @NotNull(message = "enter email of the customer")
    private String email;

    @NotNull(message = "enter age")
    @jakarta.validation.constraints.Min(20)
    private Integer age;

    @NotNull(message = "enter order details")
    private OrderDto orderDto;

}
