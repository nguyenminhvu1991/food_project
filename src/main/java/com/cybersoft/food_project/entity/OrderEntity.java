package com.cybersoft.food_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "t_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "estimate_ship" )
    private Timestamp estimateShip;
    @Column (name = "deliver_address" )
    private String deliveryAddress;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "orderEntity")
    Set<OrderStatusEntity> orderStatusEntitySet;

    @JsonIgnore
    @OneToMany(mappedBy = "orderEntity")
    Set<FoodOrderEntity> foodOrderEntitySet;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getEstimateShip() {
        return estimateShip;
    }

    public void setEstimateShip(Timestamp estimateShip) {
        this.estimateShip = estimateShip;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Set<OrderStatusEntity> getOrderStatusEntitySet() {
        return orderStatusEntitySet;
    }

    public void setOrderStatusEntitySet(Set<OrderStatusEntity> orderStatusEntitySet) {
        this.orderStatusEntitySet = orderStatusEntitySet;
    }

    public Set<FoodOrderEntity> getFoodOrderEntitySet() {
        return foodOrderEntitySet;
    }

    public void setFoodOrderEntitySet(Set<FoodOrderEntity> foodOrderEntitySet) {
        this.foodOrderEntitySet = foodOrderEntitySet;
    }
}
