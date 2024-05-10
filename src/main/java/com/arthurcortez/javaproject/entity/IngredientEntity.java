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
    private String unitType;
    private Integer unitValue;

    public IngredientEntity(String name, String unitType, Integer unitValue) {
        this.name = name;
        this.unitType = unitType;
        this.unitValue = unitValue;
    }
}