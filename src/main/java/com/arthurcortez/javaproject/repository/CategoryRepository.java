package com.arthurcortez.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurcortez.javaproject.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}