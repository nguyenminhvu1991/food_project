package com.cybersoft.food_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "material")
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy =  "materialEntity")
    Set<FoodMaterialEntity> foodMaterialEntitySet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<FoodMaterialEntity> getFoodMaterialEntitySet() {
        return foodMaterialEntitySet;
    }

    public void setFoodMaterialEntitySet(Set<FoodMaterialEntity> foodMaterialEntitySet) {
        this.foodMaterialEntitySet = foodMaterialEntitySet;
    }
}
