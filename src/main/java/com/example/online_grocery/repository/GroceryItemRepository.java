package com.example.online_grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_grocery.entity.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long>{
    
}
