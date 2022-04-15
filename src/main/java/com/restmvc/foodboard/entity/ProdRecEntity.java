package com.restmvc.foodboard.entity;

import com.restmvc.foodboard.entity_parts.EmbProdRecId;

import javax.persistence.*;

@Entity
public class ProdRecEntity {
    //ругается что нет айдишника....

   @EmbeddedId EmbProdRecId prodRecId;

//   @MapsId("recIdComp") //Говорит что айдишник этой таблице равен айди recipeEntity. =>
   @ManyToOne           // => Т.к. ключ тут составной то указываем какая именно часть равна внешнему ключу
   @JoinColumn(name = "recipe_id")
   private RecipeEntity recipe;

//    @MapsId("prodIdComp")
    @ManyToOne
    @JoinColumn(name = "product_id") // Говорим что эта сущность будет иметь столбец из Product Entity с названием "product_id" =>
    private ProductEntity product;        //=> Столбец будет ссылаться на столбец айдишника ProductEntity.

    private Integer productImportance;

    public ProdRecEntity() {
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getProductImportance() {
        return productImportance;
    }

    public void setProductImportance(Integer productImportance) {
        this.productImportance = productImportance;
    }

    public EmbProdRecId getProdRecId() {
        return prodRecId;
    }

    public void setProdRecId(EmbProdRecId prodRecId) {
        this.prodRecId = prodRecId;
    }
}
