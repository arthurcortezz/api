package com.arthurcortez.javaproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "ingredients")
@Entity(name = "ingredients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer unitValue;

    @ManyToOne
    @JoinColumn(name = "unity_type")
    private UnityTypeEntity unityType;

    public IngredientEntity(String name, Integer unitValue) {
        this.name = name;
        this.unitValue = unitValue;
    }

    public void setUnityType(UnityTypeEntity unityType) {
        this.unityType = unityType;
    }
}