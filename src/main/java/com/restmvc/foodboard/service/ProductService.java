package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.ProductModelRecipes;
import com.restmvc.foodboard.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
