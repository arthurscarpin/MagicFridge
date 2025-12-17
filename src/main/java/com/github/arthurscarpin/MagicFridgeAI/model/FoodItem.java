package com.github.arthurscarpin.MagicFridgeAI.model;

import com.github.arthurscarpin.MagicFridgeAI.enums.FoodItemCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_food_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FoodItemCategory category;

    private Integer quantity;

    private LocalDate validity;
}
