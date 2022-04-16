package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.entity.RecipeEntity;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.ProductModelPure;
import com.restmvc.foodboard.model.ProductModelRecipes;
import com.restmvc.foodboard.model.RecipeModelPure;
import com.restmvc.foodboard.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo prodRepo;
    public void newProduct(ProductEntity product) throws AlreadyExistException {
        if(prodRepo.findBytitle(product.getTitle())==null){
                prodRepo.save(product);
        }else throw new AlreadyExistException("Продукт уже существует");
    }

    public ArrayList<ProductModelPure> getAll() throws NotFoundedException{
        ArrayList<ProductEntity> allEntities = (ArrayList<ProductEntity>) prodRepo.findAll();
        if(!allEntities.isEmpty()) {
            ArrayList<ProductModelPure> pureProds = new ArrayList<>();
            for (ProductEntity product : allEntities) {
                ProductModelPure model = new ProductModelPure();
                model.toModel(product);
                pureProds.add(model);
            }
            return pureProds;
        }else throw new NotFoundedException("Продукты не найдены");
    }

    public ProductEntity getProdById(Long id) throws NotFoundedException {
        Optional<ProductEntity> product = prodRepo.findById(id);
        if(product.isPresent()){
            return product.get();
        }else throw new NotFoundedException("Такого продукта не существует");
    }
    public ProductModelRecipes getWithRecipes(Long prodId)throws NotFoundedException{
        ProductEntity product = getProdById(prodId);
        ProductModelRecipes model = new ProductModelRecipes();
        model.toModel(product);
        return model;
    }

    public void deleteProduct(Long id) throws NotFoundedException{
        Optional<ProductEntity> pr = prodRepo.findById(id);
        if(pr.isPresent()) {
            prodRepo.delete(pr.get());
        }else throw new NotFoundedException("Продукта " + id + "не существует");
    }

    public ArrayList<ProductModelPure> getProdsLike(String originalWord) throws NotFoundedException{
        String patternLike = "%"+originalWord+"%";
        List<ProductEntity> productsEnts = new ArrayList<>();
        ArrayList<ProductModelPure> prodModels = new ArrayList<>();
        productsEnts = prodRepo.findByTitleLike(patternLike);
        if(!productsEnts.isEmpty()) {
            for (ProductEntity pr : productsEnts) {
                ProductModelPure model = new ProductModelPure();
                model.toModel(pr);
                prodModels.add(model);
            }
            return prodModels;
        }else throw new NotFoundedException("Подобные продукты не найдены");
    }



}
