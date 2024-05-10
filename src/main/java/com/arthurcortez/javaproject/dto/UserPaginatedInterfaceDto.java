package com.arthurcortez.javaproject.dto;

import java.util.List;

import com.arthurcortez.javaproject.entity.UserEntity;

public class UserPaginatedInterfaceDto {

    private List<UserEntity> rows;
    private long count;

    public List<UserEntity> getRows() {
        return rows;
    }

    public void setRows(List<UserEntity> rows) {
        this.rows = rows;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}