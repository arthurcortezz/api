package com.arthurcortez.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurcortez.javaproject.entity.UnitTypeEntity;

public interface UnityTypeRepository extends JpaRepository<UnitTypeEntity, String> {
}