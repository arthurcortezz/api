package com.arthurcortez.javaproject.seeds;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import com.arthurcortez.javaproject.entity.CategoryEntity;
import com.arthurcortez.javaproject.repository.CategoryRepository;

@Component
public class CategorySeed implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategorySeed(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            CategoryEntity category1 = new CategoryEntity();
            category1.setName("Frutos do mar");
            categoryRepository.save(category1);

            CategoryEntity category2 = new CategoryEntity();
            category2.setName("Massas");
            categoryRepository.save(category2);

            CategoryEntity category3 = new CategoryEntity();
            category3.setName("Bolos");
            categoryRepository.save(category3);
        }
    }
}