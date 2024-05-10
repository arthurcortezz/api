package com.arthurcortez.javaproject.controller;

import com.arthurcortez.javaproject.entity.RecipeEntity;
import com.arthurcortez.javaproject.service.RecipeService;

import jakarta.validation.Valid;

import com.arthurcortez.javaproject.dto.CreateRecipeDto;
import com.arthurcortez.javaproject.dto.RecipePaginatedInterfaceDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @GetMapping("/list")
    public RecipePaginatedInterfaceDto findAllRecipes(Pageable pageable) {
        Page<RecipeEntity> recipesPage = service.findAllRecipes(pageable);

        RecipePaginatedInterfaceDto recipePaginatedInterface = new RecipePaginatedInterfaceDto();
        recipePaginatedInterface.setRows(recipesPage.getContent());
        recipePaginatedInterface.setCount(recipesPage.getTotalElements());

        return recipePaginatedInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createCategory(@RequestBody @Valid CreateRecipeDto recipe) {
        service.createRecipe(recipe);
        return ResponseEntity.ok(new ResponseMessage("Categoria", "Categoria criada com sucesso"));
    }

    public class ResponseMessage {
        private String title;
        private String message;

        public ResponseMessage(String title, String message) {
            this.message = message;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
