package com.arthurcortez.javaproject.dto;

import java.util.List;

public record CreateRecipeDto(String name, String description, String category,
        List<CreateIngredientDto> ingredients, List<CreateStepDto> steps) {
}