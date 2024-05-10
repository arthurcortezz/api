package com.arthurcortez.javaproject.dto;

import java.util.List;

public record CreateRecipeDto(String name, String description, String categoryId,
        List<CreateIngredientDto> ingredients) {
}