package com.github.arthurscarpin.MagicFridgeAI.dto;

import com.github.arthurscarpin.MagicFridgeAI.enums.FoodItemCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItemDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private FoodItemCategory category;

    @NotNull
    private Integer quantity;

    @NotNull
    private LocalDate validity;
}
