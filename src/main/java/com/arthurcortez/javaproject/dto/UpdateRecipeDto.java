package com.arthurcortez.javaproject.dto;

import java.util.List;

public record UpdateRecipeDto(String id, String name, String category, String description,
                List<CreateIngredientDto> ingredients, String image) {
}