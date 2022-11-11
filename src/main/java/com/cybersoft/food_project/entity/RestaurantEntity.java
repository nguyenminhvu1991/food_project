package com.cybersoft.food_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy= "restaurantEntity") //thay ten cua Entity "restaurant" bang doi tuong cua Class
    private Set<RestaurantReviewEntity> restaurantReviewEntities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RestaurantReviewEntity> getRestaurantReviewEntities() {
        return restaurantReviewEntities;
    }

    public void setRestaurantReviewEntities(Set<RestaurantReviewEntity> restaurantReviewEntities) {
        this.restaurantReviewEntities = restaurantReviewEntities;
    }

}
