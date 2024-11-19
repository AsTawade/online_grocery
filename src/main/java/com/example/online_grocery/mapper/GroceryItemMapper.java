package com.example.online_grocery.mapper;

import com.example.online_grocery.dto.GroceryItemDto;
import com.example.online_grocery.entity.GroceryItem;

public class GroceryItemMapper {

    public static GroceryItem mapToGroceryItem(GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = new GroceryItem(
                groceryItemDto.getId(),
                groceryItemDto.getName(),
                groceryItemDto.getCategory(),
                groceryItemDto.getPrice(),
                groceryItemDto.getQuantity()
        );

        return groceryItem;
    }

    public static GroceryItemDto mapToGroceryItemDto(GroceryItem groceryItem) {
        GroceryItemDto groceryItemDto = new GroceryItemDto(
                groceryItem.getId(),
                groceryItem.getName(),
                groceryItem.getCategory(),
                groceryItem.getPrice(),
                groceryItem.getQuantity()
        );

        return groceryItemDto;
    }
}

