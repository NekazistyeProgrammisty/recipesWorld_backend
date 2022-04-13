package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.ProdRecEntity;
import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.entity.RecipeEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductModelRecipes extends ProductModelPure{
    List<RecipeModelPure> recipes = new ArrayList<>();

    public ProductModelRecipes(){
        super();
    }

    @Override
    public void toModel(ProductEntity product){
        super.toModel(product);
        for(ProdRecEntity prRecEnt:product.getRecipes()){
            RecipeModelPure recModel = new RecipeModelPure();
            recModel.toModel(prRecEnt.getRecipe());
            recipes.add(recModel);
        }
    }



    public List<RecipeModelPure> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeModelPure> recipes) {
        this.recipes = recipes;
    }
}
