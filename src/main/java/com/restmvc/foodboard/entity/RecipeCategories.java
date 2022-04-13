package com.restmvc.foodboard.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RecipeCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String category;

    @OneToMany(mappedBy = "category")
    List<RecipeEntity> recipes = new ArrayList<>();

}
