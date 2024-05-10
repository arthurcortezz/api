package com.arthurcortez.javaproject.controller;

import com.arthurcortez.javaproject.entity.CategoryEntity;
import com.arthurcortez.javaproject.service.CategoryService;

import jakarta.validation.Valid;

import com.arthurcortez.javaproject.dto.CategoryPaginatedInterfaceDto;
import com.arthurcortez.javaproject.dto.CreateCategoryDto;
import com.arthurcortez.javaproject.dto.UpdateCategoryDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/list")
    public CategoryPaginatedInterfaceDto findAllCategories(Pageable pageable) {
        Page<CategoryEntity> categoriesPage = service.findAllCategories(pageable);

        CategoryPaginatedInterfaceDto categoryPaginatedInterface = new CategoryPaginatedInterfaceDto();
        categoryPaginatedInterface.setRows(categoriesPage.getContent());
        categoryPaginatedInterface.setCount(categoriesPage.getTotalElements());

        return categoryPaginatedInterface;
    }

    @GetMapping("/{id}")
    public CategoryEntity findCategoryById(@PathVariable("id") String id) {
        return service.findCategoryById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createCategory(@RequestBody @Valid CreateCategoryDto category) {
        service.createCategory(category);
        return ResponseEntity.ok(new ResponseMessage("Categoria", "Categoria criada com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateCategory(@RequestBody @Valid UpdateCategoryDto category) {
        service.updateCategory(category);
        return ResponseEntity.ok(new ResponseMessage("Categoria", "Categoria editada com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteCategory(@PathVariable("id") String id) {
        service.deleteCategory(id);
        return ResponseEntity.ok(new ResponseMessage("Categoria", "Categoria removida com sucesso"));
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
