package com.restmvc.foodboard.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeId")
    private Long recipeId;
    private String title;
    private String description;
    private String imgLink;
    private Integer productsNum;

    @ManyToMany(mappedBy = "favRecipes", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private List<UserEntity> usersFavRecipes = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH}, mappedBy = "recipe")
    private List<ProdRecEntity> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "recipe_category", referencedColumnName = "cat_id")
    private RecipeCategoriesEntity category;

    public RecipeEntity() {
    }

    public RecipeCategoriesEntity getCategory() {
        return category;
    }

    public void setCategory(RecipeCategoriesEntity category) {
        this.category = category;
    }

    public List<UserEntity> getUsersFavRecipes() {
        return usersFavRecipes;
    }

    public void setUsersFavRecipes(List<UserEntity> usersFavRecipes) {
        this.usersFavRecipes = usersFavRecipes;
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

    public List<ProdRecEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProdRecEntity> products) {
        this.products = products;
    }


}
