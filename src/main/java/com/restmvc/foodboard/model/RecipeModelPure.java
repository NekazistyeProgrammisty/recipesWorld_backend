package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.RecipeCategoriesEntity;
import com.restmvc.foodboard.entity.RecipeEntity;

public class RecipeModelPure {
    private Long recipeId;
    private String title;
    private String description;
    private String imgLink;
    private Integer productsNum;
    private String category;
    public RecipeModelPure(){}
    public void toModel(RecipeEntity entity){
        this.setRecipeId(entity.getRecipeId());
        this.setTitle(entity.getTitle());
        this.setDescription(entity.getDescription());
        this.setImgLink(entity.getImgLink());
        this.setProductsNum(entity.getProductsNum());
        this.setCategory(entity.getCategory().getCategory());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Integer getProductsNum() {
        return productsNum;
    }

    public void setProductsNum(Integer productsNum) {
        this.productsNum = productsNum;
    }


}
