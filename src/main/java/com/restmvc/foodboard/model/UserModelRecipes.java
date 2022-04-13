package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.RecipeEntity;
import com.restmvc.foodboard.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserModelRecipes extends UserModelPure{
    private List<RecipeModelPure> favRecipes = new ArrayList<>();

    public UserModelRecipes(){
        super();
    }
    @Override
    public void toModel(UserEntity user){
        super.toModel(user);
        for(RecipeEntity recipeEntity:user.getFavRecipes()){
            RecipeModelPure model = new RecipeModelPure();
            model.toModel(recipeEntity);
            favRecipes.add(model);
        }
    }
    public void toModel(UserModelPure userModel, List<RecipeEntity> recipes){
        this.setId(userModel.getId());
        this.setEmail(userModel.getEmail());
        this.setNickName(userModel.getNickName());
        for(RecipeEntity recipeEntity:recipes){
            RecipeModelPure model = new RecipeModelPure();
            model.toModel(recipeEntity);
            favRecipes.add(model);
        }
    }

    public List<RecipeModelPure> getFavRecipes() {
        return favRecipes;
    }

    public void setFavRecipes(List<RecipeModelPure> favouriteRecipes) {
        this.favRecipes = favouriteRecipes;
    }
}
