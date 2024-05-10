package com.arthurcortez.javaproject.service;

import com.arthurcortez.javaproject.dto.CreateCategoryDto;
import com.arthurcortez.javaproject.dto.UpdateCategoryDto;
import com.arthurcortez.javaproject.entity.CategoryEntity;
import com.arthurcortez.javaproject.repository.CategoryRepository;

import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<CategoryEntity> findAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public CategoryEntity findCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public CategoryEntity createCategory(CreateCategoryDto category) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName(category.name());
        categoryEntity.setDescription(category.description());
        categoryEntity.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        categoryEntity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("UTC")));

        return categoryRepository.save(categoryEntity);
    }

    public void updateCategory(UpdateCategoryDto category) {
        CategoryEntity categoryEntity = categoryRepository.findById(category.id())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        categoryEntity.setName(category.name());
        categoryEntity.setDescription(category.description());
        categoryEntity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        categoryRepository.delete(categoryEntity);
    }
}