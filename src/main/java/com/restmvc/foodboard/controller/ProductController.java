package com.restmvc.foodboard.controller;

import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.ProductModelPure;
import com.restmvc.foodboard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity newProduct(@RequestBody ProductEntity product){
        try{
            productService.newProduct(product);
            return new ResponseEntity("Продукт сохранен", HttpStatus.OK);
        }catch (AlreadyExistException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }
    @GetMapping("/get_by_id/{prodId}")
    public ResponseEntity getById(@PathVariable Long prodId){
        try {
            ProductModelPure model = new ProductModelPure();
            model.toModel(productService.getProdById(prodId));
            return new ResponseEntity(model, HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);}
    }

    @GetMapping("/get_with_recipes/{prodId}")
    public ResponseEntity getWithRecipes(@PathVariable Long prodId){
        try{
            return new ResponseEntity(productService.getWithRecipes(prodId), HttpStatus.OK);
        }catch (NotFoundedException e ){return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);}
    }
    @DeleteMapping("/delete/{prodId}")
    public ResponseEntity deleteById(@PathVariable Long prodId){
        try {
            productService.deleteProduct(prodId);
            return new ResponseEntity("Продукт успешно удален", HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);}
    }
}
