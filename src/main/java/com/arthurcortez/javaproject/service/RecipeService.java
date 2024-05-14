package com.arthurcortez.javaproject.service;

import com.arthurcortez.javaproject.dto.CreateRecipeDto;
import com.arthurcortez.javaproject.dto.UpdateRecipeDto;
import com.arthurcortez.javaproject.entity.RecipeEntity;
import com.arthurcortez.javaproject.entity.StepEntity;
import com.arthurcortez.javaproject.entity.CategoryEntity;
import com.arthurcortez.javaproject.entity.UnityTypeEntity;
import com.arthurcortez.javaproject.storage.StorageService;
import com.arthurcortez.javaproject.entity.IngredientEntity;
import com.arthurcortez.javaproject.repository.RecipeRepository;
import com.arthurcortez.javaproject.repository.CategoryRepository;
import com.arthurcortez.javaproject.repository.UnityTypeRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RecipeService {

        @Autowired
        private RecipeRepository recipeRepository;

        @Autowired
        private UnityTypeRepository unityTypeRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        private final StorageService storageService;

        public RecipeService(StorageService storageService) {
                this.storageService = storageService;
        }

        public Page<RecipeEntity> findAllRecipes(Pageable pageable) {
                return recipeRepository.findAll(pageable);
        }

        public RecipeEntity findRecipeById(String id) {
                return recipeRepository.findById(id).orElse(null);
        }

        public RecipeEntity createRecipe(CreateRecipeDto recipe, MultipartFile image) {
                RecipeEntity recipeEntity = new RecipeEntity();

                recipeEntity.setName(recipe.name());
                recipeEntity.setDescription(recipe.description());
                CategoryEntity category = categoryRepository.findById(recipe.category())
                                .orElseThrow(() -> new RuntimeException("CategoryEntity not found"));
                recipeEntity.setCategory(category);
                recipeEntity.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
                recipeEntity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("UTC")));

                List<IngredientEntity> ingredients = recipe.ingredients().stream()
                                .map(ingredient -> {
                                        UnityTypeEntity unityType = unityTypeRepository.findById(ingredient.unityType())
                                                        .orElseThrow(() -> new RuntimeException(
                                                                        "UnityTypeEntity not found"));
                                        IngredientEntity ingredientEntity = new IngredientEntity(ingredient.name(),
                                                        ingredient.unityValue());
                                        ingredientEntity.setUnityType(unityType);
                                        return ingredientEntity;
                                })
                                .collect(Collectors.toList());

                List<StepEntity> steps = recipe.steps().stream()
                                .map(step -> {
                                        StepEntity stepEntity = new StepEntity(step.name());
                                        return stepEntity;
                                })
                                .collect(Collectors.toList());

                recipeEntity.setIngredients(ingredients);
                recipeEntity.setSteps(steps);
                recipeEntity = recipeRepository.save(recipeEntity);

                if (image != null && !image.isEmpty()) {
                        String imageFileName = recipeEntity.getId();
                        String imageSaved = storageService.uploadFile(image, imageFileName);
                        System.out.println(imageSaved);
                        recipeEntity.setImage(imageSaved);
                        return recipeRepository.save(recipeEntity);
                } else {
                        return recipeEntity;
                }

        }

        public RecipeEntity updateRecipe(UpdateRecipeDto recipe, MultipartFile image) {
                RecipeEntity recipeEntity = recipeRepository.findById(recipe.id())
                                .orElseThrow(() -> new RuntimeException("RecipeEntity not found"));
                recipeEntity.setName(recipe.name());
                recipeEntity.setDescription(recipe.description());
                CategoryEntity category = categoryRepository.findById(recipe.category())
                                .orElseThrow(() -> new RuntimeException("CategoryEntity not found"));
                recipeEntity.setCategory(category);
                recipeEntity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
                List<IngredientEntity> newIngredients = recipe.ingredients().stream()
                                .map(ingredient -> {
                                        UnityTypeEntity unityType = unityTypeRepository.findById(ingredient.unityType())
                                                        .orElseThrow(() -> new RuntimeException(
                                                                        "UnityTypeEntity not found"));
                                        IngredientEntity ingredientEntity = new IngredientEntity(ingredient.name(),
                                                        ingredient.unityValue());
                                        ingredientEntity.setUnityType(unityType);
                                        return ingredientEntity;
                                })
                                .collect(Collectors.toList());
                recipeEntity.getIngredients().removeIf(ingredient -> !newIngredients.contains(ingredient));
                for (IngredientEntity ingredient : newIngredients) {
                        recipeEntity.getIngredients().add(ingredient);
                }

                List<StepEntity> newSteps = recipe.steps().stream()
                                .map(step -> {
                                        StepEntity stepEntity = new StepEntity(step.name());
                                        return stepEntity;
                                })
                                .collect(Collectors.toList());
                recipeEntity.getSteps().removeIf(step -> !newSteps.contains(step));
                for (StepEntity step : newSteps) {
                        recipeEntity.getSteps().add(step);
                }

                recipeRepository.save(recipeEntity);

                if (image != null && !image.isEmpty()) {
                        String imageFileName = recipeEntity.getId();
                        System.out.println(imageFileName);
                        String imageSaved = storageService.uploadFile(image, imageFileName);
                        System.out.println(imageSaved);
                        recipeEntity.setImage(imageSaved);
                        return recipeRepository.save(recipeEntity);
                } else {
                        return recipeEntity;
                }

        }

        public void deleteRecipe(String id) {
                RecipeEntity recipeEntity = recipeRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Receita não encontrada."));
                recipeRepository.delete(recipeEntity);
        }
}