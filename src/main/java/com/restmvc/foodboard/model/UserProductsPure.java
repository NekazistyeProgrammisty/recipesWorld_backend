package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.UserProductsEntity;

public class UserProductsPure {

    Long originalProductId;

    String title;

    Integer productsCount;

    Integer calorie;

    Integer freshDays;


    public void toModel(UserProductsEntity usrProd){
        this.setOriginalProductId(usrProd.getProdUserId().getProdIdComp());
        this.setTitle(usrProd.getTitle());
        this.setCalorie(usrProd.getCalorie());
        this.setProductsCount(usrProd.getProductsCount());
        this.setFreshDays(usrProd.getFreshDays());

    }


    public UserProductsPure() {
    }

    public Long getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(Long originalProductId) {
        this.originalProductId = originalProductId;
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
