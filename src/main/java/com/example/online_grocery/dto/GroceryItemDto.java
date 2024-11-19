package com.example.online_grocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroceryItemDto {
    
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer quantity;
    
}
