package com.example.online_grocery.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.online_grocery.dto.GroceryItemDto;
import com.example.online_grocery.entity.GroceryItem;
import com.example.online_grocery.mapper.GroceryItemMapper;
import com.example.online_grocery.repository.GroceryItemRepository;
import com.example.online_grocery.service.GroceryItemService;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

    private GroceryItemRepository groceryItemRepository;

    public GroceryItemServiceImpl(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    @Override
    public GroceryItemDto createGroceryItem(GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = GroceryItemMapper.mapToGroceryItem(groceryItemDto);
        GroceryItem savedItem = groceryItemRepository.save(groceryItem);
        return GroceryItemMapper.mapToGroceryItemDto(savedItem);
    }

    @Override
    public GroceryItemDto getGroceryItembyId(Long id) {
        GroceryItem groceryItem = groceryItemRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Grocery item does not exists!"));
        return GroceryItemMapper.mapToGroceryItemDto(groceryItem);
    }

    @Override
    public List<GroceryItemDto> getAllGroceryItem() {
        List<GroceryItem> groceryItems = groceryItemRepository.findAll();

        return groceryItems.stream()
                .map(GroceryItemMapper::mapToGroceryItemDto)
                .toList();
    }

    @Override
    public GroceryItemDto updaGroceryItem(Long id, GroceryItemDto groceryItemDto) {
        GroceryItem existingGroceryItem = groceryItemRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Grocery item does not exists!"));
        existingGroceryItem.setName(groceryItemDto.getName());
        existingGroceryItem.setCategory(groceryItemDto.getCategory());
        existingGroceryItem.setPrice(groceryItemDto.getPrice());
        existingGroceryItem.setQuantity(groceryItemDto.getQuantity());

        GroceryItem updatedGroceryItem = groceryItemRepository.save(existingGroceryItem);

        return GroceryItemMapper.mapToGroceryItemDto(updatedGroceryItem);
    }

    @Override
    public void deleteGroceryItem(Long id) {
        GroceryItem groceryItem = groceryItemRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Grocery item does not exists!"));

        groceryItemRepository.deleteById(id); 
    }

}
