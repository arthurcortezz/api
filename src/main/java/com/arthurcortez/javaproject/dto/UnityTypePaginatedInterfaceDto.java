package com.arthurcortez.javaproject.dto;

import java.util.List;

import com.arthurcortez.javaproject.entity.UnitTypeEntity;

public class UnityTypePaginatedInterfaceDto {

    private List<UnitTypeEntity> rows;
    private long count;

    public List<UnitTypeEntity> getRows() {
        return rows;
    }

    public void setRows(List<UnitTypeEntity> rows) {
        this.rows = rows;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}