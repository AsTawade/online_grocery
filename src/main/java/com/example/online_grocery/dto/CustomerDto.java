package com.example.online_grocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class CustomerDto {
    
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
}
