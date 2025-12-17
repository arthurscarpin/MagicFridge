package com.github.arthurscarpin.MagicFridgeAI.service;

import com.github.arthurscarpin.MagicFridgeAI.model.FoodItem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final WebClient webClient;

    private final String apiKey = System.getenv("API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItem> foodItems) {
        String foods = foodItems.stream()
                .map(item -> String.format("%s (%s) - Quantity: %d, Validate: %s",
                        item.getName(), item.getCategory(), item.getQuantity(), item.getValidity()))
                .collect(Collectors.joining("\n"));
        String prompt = "Based on my database, create a recipe using the following items: " + foods;
        Map<String, Object> requestBody = Map.of(
                "model","gpt-5.2",
                "input", List.of(
                        Map.of("role", "system", "content", "You are an assistant that creates recipes."),
                        Map.of("role", "user", "content", prompt)
                )
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var output = (List<Map<String, Object>>) response.get("output");
                    if (output != null && !output.isEmpty()) {
                        var content = (List<Map<String, Object>>) output.get(0).get("content");
                        return content.get(0).get("text").toString();
                    }
                    return "Nothing recipe went generate.";
                });
    }
}
