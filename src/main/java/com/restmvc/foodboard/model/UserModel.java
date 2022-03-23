package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.RecipeEntity;
import com.restmvc.foodboard.entity.UserEntity;

import java.util.List;

public class UserModel {
    private Long id;
    private String nickName;
    private String email;
    private List<RecipeEntity> favRecipes;
    public static UserModel toModel(UserEntity entity){
        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setNickName(entity.getNickName());
        user.setFavRecipes(entity.getFavRecipes());
        return user;
    }
    public UserModel() {
    }

    public List<RecipeEntity> getFavRecipes() {
        return favRecipes;
    }

    public void setFavRecipes(List<RecipeEntity> favRecipes) {
        this.favRecipes = favRecipes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
