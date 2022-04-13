package com.restmvc.foodboard.entity;

import com.restmvc.foodboard.entity_parts.EmbProdRecId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(mappedBy = "favRecipes")
    private List<UserEntity> usersFavRecipes = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "recipe")
    private List<ProdRecEntity> products = new ArrayList<>();

    @ManyToOne
    private RecipeCategories category;

    public RecipeEntity() {
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

//    public List<ProdRecEntity> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<ProdRecEntity> products) {
//        this.products = products;
//    }
}
