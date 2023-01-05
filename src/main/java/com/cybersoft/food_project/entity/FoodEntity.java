package com.cybersoft.food_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "food")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "prive")
    private int price;

    //map  với 2 cột từ 1 bảng
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name="ADDR_ID", referencedColumnName="ID"),
//            @JoinColumn(name="ADDR_ZIP", referencedColumnName="ZIP")
//    })

    //map với 2 cột từ 2 bảng
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryEntity categoryEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity restaurantEntity;

    @JsonIgnore//ko để json biến đối tượng này thành Json để tránh lỗi vòng lặp vô tận
    @OneToOne(mappedBy = "foodEntity", cascade = CascadeType.ALL) //cho nay thi dung ten Class thay cho ten entity
    @PrimaryKeyJoinColumn //tham khao them tu bai cua nguoi khac
    private FoodDetailEntity foodDetailEntity; //trả về 1 doi tuong do OneToOne

    @JsonIgnore
    @OneToMany(mappedBy =  "foodEntity")
    Set<FoodReviewEntity> foodReviewEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy =  "foodEntity")
    Set<FoodAddOnEntity> foodAddOnEntities;

    @JsonIgnore
    @OneToMany(mappedBy =  "foodEntity")
    Set<FoodMaterialEntity> foodMaterialEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy =  "foodEntity")
    Set<FoodOrderEntity> foodOrderEntitySet;



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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }

    public FoodDetailEntity getFoodDetailEntity() {
        return foodDetailEntity;
    }

    public void setFoodDetailEntity(FoodDetailEntity foodDetailEntity) {
        this.foodDetailEntity = foodDetailEntity;
    }

    public Set<FoodReviewEntity> getFoodReviewEntitySet() {
        return foodReviewEntitySet;
    }

    public void setFoodReviewEntitySet(Set<FoodReviewEntity> foodReviewEntitySet) {
        this.foodReviewEntitySet = foodReviewEntitySet;
    }

    public Set<FoodAddOnEntity> getFoodAddOnEntities() {
        return foodAddOnEntities;
    }

    public void setFoodAddOnEntities(Set<FoodAddOnEntity> foodAddOnEntities) {
        this.foodAddOnEntities = foodAddOnEntities;
    }

    public Set<FoodMaterialEntity> getFoodMaterialEntitySet() {
        return foodMaterialEntitySet;
    }

    public void setFoodMaterialEntitySet(Set<FoodMaterialEntity> foodMaterialEntitySet) {
        this.foodMaterialEntitySet = foodMaterialEntitySet;
    }

    public Set<FoodOrderEntity> getFoodOrderEntitySet() {
        return foodOrderEntitySet;
    }

    public void setFoodOrderEntitySet(Set<FoodOrderEntity> foodOrderEntitySet) {
        this.foodOrderEntitySet = foodOrderEntitySet;
    }
}
