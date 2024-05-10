package com.arthurcortez.javaproject.service;

import com.arthurcortez.javaproject.entity.RecipeEntity;
import com.arthurcortez.javaproject.repository.RecipeRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Page<RecipeEntity> findAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }
}