package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.ProdRecEntity;
import com.restmvc.foodboard.entity.RecipeEntity;

import java.util.ArrayList;
import java.util.List;

public class RecipeModelProd extends RecipeModelPure{
    private List<ProductModelPure> productsPure = new ArrayList<>();
    public RecipeModelProd(){super();}
    @Override
    public void toModel(RecipeEntity recipeEntity){
        super.toModel(recipeEntity);
        for(ProdRecEntity pr:recipeEntity.getProducts()){
            ProductModelPure pureProd = new ProductModelPure();
            pureProd.toModel(pr.getProduct());
            this.getProducts().add(pureProd);
        }
    }



    public List<ProductModelPure> getProducts() {
        return productsPure;
    }

    public void setProducts(List<ProductModelPure> products) {
        this.productsPure = products;
    }
}
