package com.github.arthurscarpin.MagicFridgeAI.controller;

import com.github.arthurscarpin.MagicFridgeAI.dto.FoodItemDTO;
import com.github.arthurscarpin.MagicFridgeAI.mapper.FoodItemMapper;
import com.github.arthurscarpin.MagicFridgeAI.model.FoodItem;
import com.github.arthurscarpin.MagicFridgeAI.service.ChatGptService;
import com.github.arthurscarpin.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final FoodItemService foodItemService;

    private final ChatGptService chatGptService;

    private final FoodItemMapper mapper;

    public RecipeController(FoodItemService foodItemService, ChatGptService chatGptService, FoodItemMapper mapper) {
        this.foodItemService = foodItemService;
        this.chatGptService = chatGptService;
        this.mapper = mapper;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItemDTO> foodItemDTOS = foodItemService.listAll();
        List<FoodItem> foodItems = foodItemDTOS.stream()
                .map(mapper::map)
                .toList();
        return chatGptService.generateRecipe(foodItems)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
