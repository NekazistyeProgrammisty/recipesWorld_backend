package com.restmvc.foodboard.controller;

import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.entity.RecipeEntity;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.RecipeModelPure;
import com.restmvc.foodboard.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    RecipeService recipeService;


    @GetMapping("/get_by_id/{recipeId}")
    public ResponseEntity getById(@PathVariable(name = "recipeId") Long recipeId){
        try {
            RecipeModelPure model = new RecipeModelPure();
            model.toModel(recipeService.getRecById(recipeId));
            return new ResponseEntity(model, HttpStatus.BAD_REQUEST);
        }catch (NotFoundedException e){return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }


    @GetMapping("/all/")
    public ResponseEntity getAll(){
        try {
            return new ResponseEntity(recipeService.getAll(), HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }

    @GetMapping("/get_users/{recipeId}")
    public ResponseEntity getByIdWithUsers(@PathVariable(name = "recipeId") Long recId){
        try{
            return new ResponseEntity(recipeService.getWithUsers(recId),HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);}
    }


    @GetMapping("/get_included_products/{recipeId}")
    public ResponseEntity getWithProducts(@PathVariable(name = "recipeId") Long recId){
        try {
            return new ResponseEntity(recipeService.getWithProducts(recId),HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);}
    }


    @PostMapping
    public ResponseEntity newRecipe(@RequestBody RecipeEntity recipe){
        try{
            recipeService.newRecipe(recipe);
            return new ResponseEntity("Рецепт сохранен", HttpStatus.OK);
        }catch (AlreadyExistException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }


    @PutMapping("/{recId}/add_product/{prodId}/importance/{prodImp}")
    public ResponseEntity addProduct(@PathVariable(name = "prodId") Long prodId, @PathVariable(name = "prodImp")Integer importance, @PathVariable(name = "recId") Long recId){
        try{
            recipeService.addProduct(recId,prodId,importance);
            return new ResponseEntity("Продукт добавлен", HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);}
    }


    @DeleteMapping("/delete/{recipeId}")
    public ResponseEntity deleteRecipe(@PathVariable(name = "recipeId") Long recId){
        try {
            recipeService.deleteRecipe(recId);
            return new ResponseEntity("Рецепт удален",HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}

