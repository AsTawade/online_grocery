package com.example.online_grocery.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_grocery.dto.GroceryItemDto;
import com.example.online_grocery.service.GroceryItemService;

@RestController
@RequestMapping("/api/groceryItems")
public class GroceryItemController {

    private GroceryItemService groceryItemService;

    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    // create item REST API
    @PostMapping
    public ResponseEntity<GroceryItemDto> createGroceryItem(@RequestBody GroceryItemDto groceryItemDto){
        return new ResponseEntity<>(groceryItemService.createGroceryItem(groceryItemDto), HttpStatus.CREATED);
    }

    // get Item by ID REST API
    @GetMapping("/{id}")
    public ResponseEntity<GroceryItemDto> getGroceryItembyId(@PathVariable Long id){
        GroceryItemDto groceryItemDto = groceryItemService.getGroceryItembyId(id);
        return ResponseEntity.ok(groceryItemDto);

    }

    //get all Grocery Item REST API
    @GetMapping
    public ResponseEntity<List<GroceryItemDto>> getAllGroceryItem(){
        List<GroceryItemDto> groceryItems = groceryItemService.getAllGroceryItem();
        return ResponseEntity.ok(groceryItems);
    }

    //update grocery item REST API
    @PostMapping("/{id}")
    public ResponseEntity<GroceryItemDto> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItemDto groceryItemDto){
        GroceryItemDto updaGroceryItem = groceryItemService.updaGroceryItem(id, groceryItemDto);
        return ResponseEntity.ok(updaGroceryItem);
    }

    //delete frocery item REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroceryItem(@PathVariable Long id){
        groceryItemService.deleteGroceryItem(id);
        return ResponseEntity.ok("Grocery item has been deleted!");
    }
}
