package com.example.online_grocery.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private CustomerDto customer;
    private List<GroceryItemDto> groceryItems;
    private LocalDate orderDate;
    private Double totalPrice;
}