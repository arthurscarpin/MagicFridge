package com.github.arthurscarpin.MagicFridgeAI.controller;

import com.github.arthurscarpin.MagicFridgeAI.model.FoodItem;
import com.github.arthurscarpin.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FoodItem> create(@RequestBody FoodItem foodItem) {
        FoodItem saved = service.save(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> listById(@PathVariable Long id) {
        return ResponseEntity.ok(service.listById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> updateById(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        return ResponseEntity.ok(service.updateById(id, foodItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FoodItem> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }
}
