package com.arthurcortez.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurcortez.javaproject.entity.RecipeEntity;

public interface RecipeRepository extends JpaRepository<RecipeEntity, String> {
}