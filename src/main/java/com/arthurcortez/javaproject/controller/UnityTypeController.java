package com.arthurcortez.javaproject.controller;

import com.arthurcortez.javaproject.entity.UnitTypeEntity;
import com.arthurcortez.javaproject.service.UnityTypeService;

import jakarta.validation.Valid;

import com.arthurcortez.javaproject.dto.CreateUnityTypeDto;
import com.arthurcortez.javaproject.dto.UnityTypePaginatedInterfaceDto;
import com.arthurcortez.javaproject.dto.UpdateUnityTypeDto;

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
@RequestMapping("/unity-type")
public class UnityTypeController {

    @Autowired
    private UnityTypeService service;

    @GetMapping("/list")
    public UnityTypePaginatedInterfaceDto findAllRecipes(Pageable pageable) {
        Page<UnitTypeEntity> recipesPage = service.findAllUnitTypes(pageable);

        UnityTypePaginatedInterfaceDto recipePaginatedInterface = new UnityTypePaginatedInterfaceDto();
        recipePaginatedInterface.setRows(recipesPage.getContent());
        recipePaginatedInterface.setCount(recipesPage.getTotalElements());

        return recipePaginatedInterface;
    }

    @GetMapping("/{id}")
    public UnitTypeEntity findUnityTypeById(@PathVariable("id") String id) {
        return service.findUnityTypeById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createUnityType(@RequestBody @Valid CreateUnityTypeDto unityType) {
        service.createUnityType(unityType);
        return ResponseEntity.ok(new ResponseMessage("Unidade de medida", "Unidade de medida criada com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateUnityType(@RequestBody @Valid UpdateUnityTypeDto unityType) {
        service.updateUnityType(unityType);
        return ResponseEntity.ok(new ResponseMessage("Unidade de medida", "Unidade de medida editada com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteUnityType(@PathVariable("id") String id) {
        service.deleteUnityType(id);
        return ResponseEntity.ok(new ResponseMessage("Unidade de medida", "Unidade de medida removida com sucesso"));
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
