package com.github.arthurscarpin.MagicFridgeAI.service;

import com.github.arthurscarpin.MagicFridgeAI.dto.FoodItemDTO;
import com.github.arthurscarpin.MagicFridgeAI.exception.BusinessException;
import com.github.arthurscarpin.MagicFridgeAI.mapper.FoodItemMapper;
import com.github.arthurscarpin.MagicFridgeAI.model.FoodItem;
import com.github.arthurscarpin.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;

    private final FoodItemMapper mapper;

    public FoodItemService(FoodItemRepository repository, FoodItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FoodItemDTO save(FoodItemDTO dto) {
        FoodItem foodItem = mapper.map(dto);
        return mapper.map(repository.save(foodItem));
    }

    public List<FoodItemDTO> listAll() {
        List<FoodItem> foodItems = repository.findAll();
        return foodItems.stream()
                .map(mapper::map)
                .toList();
    }

    public FoodItemDTO listById(Long id) {
        FoodItem foodItem = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Food Item not found!"));
        return mapper.map(foodItem);
    }

    public FoodItemDTO updateById(Long id, FoodItemDTO dto) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Food Item not found!");
        }
        FoodItem foodItemUpdated = mapper.map(dto);
        foodItemUpdated.setId(id);
        return mapper.map(repository.save(foodItemUpdated));
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Food Item not found!");
        }
        repository.deleteById(id);
    }
}
