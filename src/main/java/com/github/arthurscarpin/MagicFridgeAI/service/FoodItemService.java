package com.github.arthurscarpin.MagicFridgeAI.service;

import com.github.arthurscarpin.MagicFridgeAI.model.FoodItem;
import com.github.arthurscarpin.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem save(FoodItem foodItem) {
        return repository.save(foodItem);
    }

    public List<FoodItem> listAll() {
        return repository.findAll();
    }

    public FoodItem listById(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public FoodItem updateById(Long id, FoodItem foodUpdate) {
        if (repository.existsById(id)) {
            foodUpdate.setId(id);
            return repository.save(foodUpdate);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
