package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.ProductEntity;

public class ProductModelPure {
    private Long idProd;
    private String title;
    private Integer calorie;
    private Integer freshDays;
    private String imgLink;

    public ProductModelPure(){}

    public void toModel(ProductEntity product){
        this.setIdProd(product.getIdProd());
        this.setCalorie(product.getCalorie());
        this.setFreshDays(product.getFreshDays());
        this.setImgLink(product.getImgLink());
        this.setTitle(product.getTitle());
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

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
