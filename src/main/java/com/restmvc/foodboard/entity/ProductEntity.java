package com.restmvc.foodboard.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProd;
    private String title;
    private Integer calorie;
    private Integer freshDays;
    private String imgLink;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product") //Каскад говорит что все операции которые будут происходить с продуктом, будут происходить с ним и в дочерней таблице =>
    List<ProdRecEntity> recipes = new ArrayList<>();       //=> Маппед говорит что колонка product в таблице ProdRecEntity будет взята отсюда

    public ProductEntity() {

    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public Integer getFreshDays() {
        return freshDays;
    }

    public void setFreshDays(Integer freshDays) {
        this.freshDays = freshDays;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public List<ProdRecEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<ProdRecEntity> recipes) {
        this.recipes = recipes;
    }
}
