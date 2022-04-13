package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.RecipeEntity;
import com.restmvc.foodboard.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class RecipeModelUsers extends RecipeModelPure{
    List<UserModelPure> users = new ArrayList<>();
    public RecipeModelUsers(){
        super();
    }

    @Override
    public void toModel(RecipeEntity entity) {
        super.toModel(entity);
        for(UserEntity user:entity.getUsersFavRecipes()){
            UserModelPure modelUser = new UserModelPure();
            modelUser.toModel(user);
            users.add(modelUser);
        }
    }

    public List<UserModelPure> getUsers() {
        return users;
    }

    public void setUsers(List<UserModelPure> users) {
        this.users = users;
    }
}
