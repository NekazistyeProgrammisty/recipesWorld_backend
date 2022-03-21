package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.RecipeEntity;

public class RecipeModel {
    private Long recipeId;
    private String title;
    private String description;
    private String imgLink;
    private Integer productsNum;

    public static RecipeModel toModel(RecipeEntity entity){
        RecipeModel recipe = new RecipeModel();
        recipe.setRecipeId(entity.getRecipeId());
        recipe.setTitle(entity.getTitle());
        recipe.setDescription(entity.getDescription());
        recipe.setImgLink(entity.getImgLink());
        recipe.setProductsNum(entity.getProductsNum());
        return recipe;
    }

    public RecipeModel() {
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
