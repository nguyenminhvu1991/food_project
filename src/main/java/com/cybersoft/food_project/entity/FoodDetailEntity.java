package com.cybersoft.food_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "food_detail")
public class FoodDetailEntity {

    @Id
    @Column(name = "id_food")
    private int idFood;
    @Column(name = "description")
    private String description;
    @Column(name = "create_date")
    private Timestamp creatDate;
    @Column(name = "rating")
    private float rating;
    @JsonIgnore
    @OneToOne//bảng nào chứa khóa ngoại thì chứa JoinColumn và bảng kia chứa mappedBy
    @MapsId//tham khao them tu bai cua nguoi khac
    @JoinColumn(name = "id_food")
    private FoodEntity foodEntity;

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Timestamp creatDate) {
        this.creatDate = creatDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public FoodEntity getFoodEntity() {
        return foodEntity;
    }

    public void setFoodEntity(FoodEntity foodEntity) {
        this.foodEntity = foodEntity;
    }


}
