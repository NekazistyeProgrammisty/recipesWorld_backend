package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepo prodRepo;
    public void registration(ProductEntity product) throws AlreadyExistException {
        if(prodRepo.findBytitle(product.getTitle())==null){
                prodRepo.save(product);
        }else throw new AlreadyExistException("Продукт уже существует");
    }
}
