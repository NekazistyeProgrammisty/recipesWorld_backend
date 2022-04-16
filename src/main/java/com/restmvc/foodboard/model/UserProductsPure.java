package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.UserProductsEntity;
import com.restmvc.foodboard.entity_parts.EmbProdUser;

import java.time.LocalDate;

public class UserProductsPure {

    EmbProdUser embId;

    String title;

    Integer productsCount;

    Integer calorie;

    LocalDate expirationDate;


    public void toModel(UserProductsEntity usrProd){
        this.setEmbId(usrProd.getProdUserId());
        this.setTitle(usrProd.getTitle());
        this.setCalorie(usrProd.getCalorie());
        this.setProductsCount(usrProd.getProductsCount());
        this.setExpirationDate(usrProd.getExpirationDate());

    }


    public UserProductsPure() {
    }

    public EmbProdUser getEmbId() {
        return embId;
    }

    public void setEmbId(EmbProdUser embId) {
        this.embId = embId;
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

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
