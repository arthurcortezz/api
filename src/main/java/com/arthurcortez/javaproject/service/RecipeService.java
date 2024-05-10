package com.arthurcortez.javaproject.service;

import com.arthurcortez.javaproject.dto.CreateRecipeDto;
import com.arthurcortez.javaproject.entity.IngredientEntity;
import com.arthurcortez.javaproject.entity.RecipeEntity;
import com.arthurcortez.javaproject.entity.UnityTypeEntity;
import com.arthurcortez.javaproject.repository.RecipeRepository;
import com.arthurcortez.javaproject.repository.UnityTypeRepository;

import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UnityTypeRepository unityTypeRepository;

    public Page<RecipeEntity> findAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    public RecipeEntity createRecipe(CreateRecipeDto recipe) {
        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setName(recipe.name());
        recipeEntity.setDescription(recipe.description());
        recipeEntity.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        recipeEntity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("UTC")));

        List<IngredientEntity> ingredients = recipe.ingredients().stream()
                .map(ingredient -> {
                    UnityTypeEntity unityType = unityTypeRepository.findById(ingredient.unityType())
                            .orElseThrow(() -> new RuntimeException("UnityTypeEntity not found"));
                    IngredientEntity ingredientEntity = new IngredientEntity(ingredient.name(),
                            ingredient.unityValue());
                    ingredientEntity.setUnityType(unityType);
                    return ingredientEntity;
                })
                .collect(Collectors.toList());

        recipeEntity.setIngredients(ingredients);

        return recipeRepository.save(recipeEntity);
    }
}