package com.example.online_grocery.service;

import java.util.List;

import com.example.online_grocery.dto.GroceryItemDto;

public interface GroceryItemService {
    
    GroceryItemDto createGroceryItem(GroceryItemDto groceryItemDto);

    GroceryItemDto getGroceryItembyId(Long id);

    List<GroceryItemDto> getAllGroceryItem();

    GroceryItemDto updaGroceryItem(Long id, GroceryItemDto groceryItemDto);

    void deleteGroceryItem(Long id);
}
