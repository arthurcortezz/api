package com.arthurcortez.javaproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.arthurcortez.javaproject.entity.RecipeEntity;
import com.arthurcortez.javaproject.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.arthurcortez.javaproject.dto.CreateRecipeDto;
import com.arthurcortez.javaproject.dto.UpdateRecipeDto;
import com.arthurcortez.javaproject.dto.RecipePaginatedInterfaceDto;

import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/{id}")
    public RecipeEntity findRecipeById(@PathVariable("id") String id) {
        return service.findRecipeById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createRecipe(@RequestParam("image") MultipartFile image,
            @RequestParam("recipe") String recipe) {
        try {
            CreateRecipeDto recipeDto = new ObjectMapper().readValue(recipe, CreateRecipeDto.class);
            service.createRecipe(recipeDto, image);
            return ResponseEntity.ok(new ResponseMessage("Receita", "Receita criada com sucesso"));
        } catch (JsonProcessingException e) {
            System.out.println(e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage("Erro", "Erro ao processar JSON"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateRecipe(@RequestParam("image") MultipartFile image,
            @RequestParam("recipe") String recipe) {
        try {
            UpdateRecipeDto recipeDto = new ObjectMapper().readValue(recipe, UpdateRecipeDto.class);
            service.updateRecipe(recipeDto, image);
            return ResponseEntity.ok(new ResponseMessage("Receita", "Receita editada com sucesso"));
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage("Erro", "Erro ao processar JSON"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteRecipe(@PathVariable("id") String id) {
        service.deleteRecipe(id);
        return ResponseEntity.ok(new ResponseMessage("Receita", "Receita removida com sucesso"));
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
