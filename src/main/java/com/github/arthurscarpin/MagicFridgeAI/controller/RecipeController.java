package com.github.arthurscarpin.MagicFridgeAI.controller;

import com.github.arthurscarpin.MagicFridgeAI.model.FoodItem;
import com.github.arthurscarpin.MagicFridgeAI.service.ChatGptService;
import com.github.arthurscarpin.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    private final FoodItemService foodItemService;
    private final ChatGptService chatGptService;

    public RecipeController(FoodItemService foodItemService, ChatGptService chatGptService) {
        this.foodItemService = foodItemService;
        this.chatGptService = chatGptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItem> foodItems = foodItemService.listAll();
        return chatGptService.generateRecipe(foodItems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
