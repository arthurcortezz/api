package com.arthurcortez.javaproject.dto;

import java.util.List;

import com.arthurcortez.javaproject.entity.RecipeEntity;

public class RecipePaginatedInterfaceDto {

    private List<RecipeEntity> rows;
    private long count;

    public List<RecipeEntity> getRows() {
        return rows;
    }

    public void setRows(List<RecipeEntity> rows) {
        this.rows = rows;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}