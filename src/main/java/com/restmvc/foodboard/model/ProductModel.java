package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.ProductEntity;

public class ProductModel {
    private Long idProd;
    private String title;
    private Integer calorie;
    private Integer freshDays;

    public ProductModel toModel(ProductEntity entity){
        ProductModel product = new ProductModel();
        product.setIdProd(entity.getIdProd());
        product.setTitle(entity.getTitle());
        product.setCalorie(entity.getCalorie());
        product.setFreshDays(entity.getFreshDays());
        return product;
    }

    public ProductModel() {
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
