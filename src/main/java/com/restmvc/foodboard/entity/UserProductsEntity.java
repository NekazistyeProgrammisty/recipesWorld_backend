package com.restmvc.foodboard.entity;

import com.restmvc.foodboard.entity_parts.EmbProdUser;

import javax.persistence.*;

@Entity
public class UserProductsEntity {
    @EmbeddedId
    EmbProdUser prodUserId;

    @ManyToOne
    @JoinColumn(name = "user")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "users_product")
    ProductEntity product;

    String title;

    Integer productsCount;

    Integer calorie;

    Integer freshDays;

    public UserProductsEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public EmbProdUser getProdUserId() {
        return prodUserId;
    }

    public void setProdUserId(EmbProdUser prodUserId) {
        this.prodUserId = prodUserId;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public Integer getFreshDays() {
        return freshDays;
    }

    public void setFreshDays(Integer freshDays) {
        this.freshDays = freshDays;
    }
}
