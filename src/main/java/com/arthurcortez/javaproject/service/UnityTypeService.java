package com.arthurcortez.javaproject.service;

import com.arthurcortez.javaproject.dto.CreateUnityTypeDto;
import com.arthurcortez.javaproject.dto.UpdateUnityTypeDto;
import com.arthurcortez.javaproject.entity.UnitTypeEntity;
import com.arthurcortez.javaproject.repository.UnityTypeRepository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UnityTypeService {

    @Autowired
    private UnityTypeRepository unityTypeRepository;

    public Page<UnitTypeEntity> findAllUnitTypes(Pageable pageable) {
        return unityTypeRepository.findAll(pageable);
    }

    public UnitTypeEntity findUnityTypeById(String id) {
        return unityTypeRepository.findById(id).orElse(null);
    }

    public UnitTypeEntity createUnityType(CreateUnityTypeDto unityType) {
        UnitTypeEntity unityTypeEntity = new UnitTypeEntity();

        unityTypeEntity.setName(unityType.name());
        unityTypeEntity.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        unityTypeEntity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("UTC")));

        return unityTypeRepository.save(unityTypeEntity);
    }

    public void updateUnityType(UpdateUnityTypeDto unityType) {
        UnitTypeEntity unityTypeEntity = unityTypeRepository.findById(unityType.id())
                .orElseThrow(() -> new RuntimeException("Unidade de medida não encontrada."));

        unityTypeEntity.setName(unityType.name());
        unityTypeEntity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        unityTypeRepository.save(unityTypeEntity);
    }

    public void deleteUnityType(String id) {
        UnitTypeEntity unityTypeEntity = unityTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade de medida não encontrada."));
        unityTypeRepository.delete(unityTypeEntity);
    }
}