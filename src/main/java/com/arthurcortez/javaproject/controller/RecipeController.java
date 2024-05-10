package com.arthurcortez.javaproject.controller;

import com.arthurcortez.javaproject.entity.RecipeEntity;
import com.arthurcortez.javaproject.service.RecipeService;
import com.arthurcortez.javaproject.dto.RecipePaginatedInterfaceDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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
}
