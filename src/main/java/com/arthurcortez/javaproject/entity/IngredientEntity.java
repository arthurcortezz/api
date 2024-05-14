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
    private Float unityValue;

    @ManyToOne
    @JoinColumn(name = "unity_type")
    private UnityTypeEntity unityType;

    public IngredientEntity(String name, Float unityValue) {
        this.name = name;
        this.unityValue = unityValue;
    }

    public void setUnityType(UnityTypeEntity unityType) {
        this.unityType = unityType;
    }
}