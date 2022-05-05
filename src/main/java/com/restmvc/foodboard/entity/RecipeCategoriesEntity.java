package com.restmvc.foodboard.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RecipeCategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    Long catId;

    String category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<RecipeEntity> recipes = new ArrayList<>();

    public RecipeCategoriesEntity() {
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<RecipeEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeEntity> recipes) {
        this.recipes = recipes;
    }
}
