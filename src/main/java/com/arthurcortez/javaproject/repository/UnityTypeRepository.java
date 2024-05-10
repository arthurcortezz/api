package com.arthurcortez.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurcortez.javaproject.entity.UnityTypeEntity;

public interface UnityTypeRepository extends JpaRepository<UnityTypeEntity, String> {
}