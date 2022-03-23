package com.restmvc.foodboard.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    private String email;
    private String password;
    private String avatarLink;
    private LocalDate regDate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "favoriteRecipes",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    List<RecipeEntity> favRecipes = new ArrayList<>();

    public UserEntity() {

    }

    public List<RecipeEntity> getFavRecipes() {
        return favRecipes;
    }

    public void setFavRecipes(List<RecipeEntity> favRecipes) {
        this.favRecipes = favRecipes;
    }

    public void addFavRecipes(RecipeEntity recipe){
        favRecipes.add(recipe); //сначала добавляем рецепт в наш список
        recipe.getUsersFavRecipes().add(this); //затем добавляем юзера в рецепты
    }
    public void removeFavRecipes(RecipeEntity recipe){
        favRecipes.remove(recipe);
        recipe.getUsersFavRecipes().remove(this);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }
}
