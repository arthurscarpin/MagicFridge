package com.github.arthurscarpin.MagicFridgeAI.mapper;

import com.github.arthurscarpin.MagicFridgeAI.dto.FoodItemDTO;
import com.github.arthurscarpin.MagicFridgeAI.model.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FoodItemMapper {

    public FoodItem map(FoodItemDTO dto) {
        FoodItem foodItem = new FoodItem();
        foodItem.setId(dto.getId());
        foodItem.setCategory(dto.getCategory());
        foodItem.setQuantity(dto.getQuantity());
        foodItem.setName(dto.getName());
        foodItem.setValidity(dto.getValidity());
        return foodItem;
    }
    
    public FoodItemDTO map(FoodItem foodItem) {
        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(foodItem.getId());
        dto.setCategory(foodItem.getCategory());
        dto.setQuantity(foodItem.getQuantity());
        dto.setName(foodItem.getName());
        dto.setValidity(foodItem.getValidity());
        return dto;
    }
}
