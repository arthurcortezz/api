package com.arthurcortez.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurcortez.javaproject.entity.IngredientEntity;

public interface IngredientRepository extends JpaRepository<IngredientEntity, String> {
}