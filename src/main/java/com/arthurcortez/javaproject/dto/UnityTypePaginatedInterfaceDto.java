package com.arthurcortez.javaproject.dto;

import java.util.List;

import com.arthurcortez.javaproject.entity.UnityTypeEntity;

public class UnityTypePaginatedInterfaceDto {

    private List<UnityTypeEntity> rows;
    private long count;

    public List<UnityTypeEntity> getRows() {
        return rows;
    }

    public void setRows(List<UnityTypeEntity> rows) {
        this.rows = rows;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}