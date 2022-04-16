package com.restmvc.foodboard.entity;

import com.restmvc.foodboard.entity_parts.EmbProdUser;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "favoriteRecipes",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    List<RecipeEntity> favRecipes = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
    List<UserProductsEntity> products = new ArrayList<>();


    public UserEntity() {

    }


    public void addFavRecipes(RecipeEntity recipe){
        favRecipes.add(recipe); //сначала добавляем рецепт в наш список
        recipe.getUsersFavRecipes().add(this); //затем добавляем юзера в рецепты
    }
    public void removeFavRecipes(RecipeEntity recipe){
        favRecipes.remove(recipe);
        recipe.getUsersFavRecipes().remove(this);
    }

//    public void addUsersProduct(ProductEntity prod){
//        UserProductsEntity prodEnt = new UserProductsEntity();
//        EmbProdUser embId = new EmbProdUser();
//        embId.setProdIdComp(prod.getIdProd());
//        embId.setUserIdComp(this.getId());
//        prodEnt.setProdUserId(embId);
//        prodEnt.setProduct(prod);
//        prodEnt.setUser(this);
//        prodEnt.setTitle(prod.getTitle());
//        prodEnt.setCalorie(prod.getCalorie());
//        prodEnt.setFreshDays(prod.getFreshDays());
//        prodEnt.setProductsCount(1);
//        this.getProducts().add(prodEnt);
//        prod.getUserProd().add(prodEnt);
//    }
//
//    public void removeUsersProduct(ProductEntity prod){
//        UserProductsEntity prodEnt = new UserProductsEntity();
//        EmbProdUser embId = new EmbProdUser();
//        embId.setProdIdComp(prod.getIdProd());
//        embId.setUserIdComp(this.getId());
//        prodEnt.setProdUserId(embId);
//        prodEnt.setProduct(prod);
//        prodEnt.setUser(this);
//        prodEnt.setTitle(prod.getTitle());
//        prodEnt.setCalorie(prod.getCalorie());
//        prodEnt.setFreshDays(prod.getFreshDays());
//        prodEnt.setProductsCount(1);
//        this.getProducts().add(prodEnt);
//        prod.getUserProd().add(prodEnt);
//    }

    public List<UserProductsEntity> getProducts() {
        return products;
    }

    public void setProducts(List<UserProductsEntity> products) {
        this.products = products;
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
