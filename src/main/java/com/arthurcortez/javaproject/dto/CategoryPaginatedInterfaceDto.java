package com.arthurcortez.javaproject.dto;

import java.util.List;

import com.arthurcortez.javaproject.entity.CategoryEntity;

public class CategoryPaginatedInterfaceDto {

    private List<CategoryEntity> rows;
    private long count;

    public List<CategoryEntity> getRows() {
        return rows;
    }

    public void setRows(List<CategoryEntity> rows) {
        this.rows = rows;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}