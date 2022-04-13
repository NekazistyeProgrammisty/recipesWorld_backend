package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.RecipeEntity;
import com.restmvc.foodboard.entity.UserEntity;

import java.util.ArrayList;

public class UserModelPure {
    private Long id;
    private String nickName;
    private String email;
    public void toModel(UserEntity entity){
        this.setId(entity.getId());
        this.setEmail(entity.getEmail());
        this.setNickName(entity.getNickName());
    }
    public final void toModel(UserModelRecipes userModel){
        this.setId(userModel.getId());
        this.setEmail(userModel.getEmail());
        this.setNickName(userModel.getNickName());
    }
    public UserModelPure() {
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
