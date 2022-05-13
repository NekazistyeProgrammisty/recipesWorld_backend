package com.restmvc.foodboard.controller;

import com.restmvc.foodboard.entity.UserEntity;
import com.restmvc.foodboard.entity_parts.EmbProdUser;
import com.restmvc.foodboard.entity_parts.JsonArray;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.UserModelPure;
import com.restmvc.foodboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get_user_data/{id}")
    public ResponseEntity getOneUserById(@PathVariable Long id){
        try{
            UserModelPure model = new UserModelPure();
            model.toModel(userService.getOneUserById(id));
            return new ResponseEntity(model,HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }


    @GetMapping("/{id}/favorite/recipes")
    public ResponseEntity getUserRecipes(@PathVariable(name = "id") Long id) {
        try {
            return new ResponseEntity(userService.getUserWithFavouriteRecipes(id), HttpStatus.OK);
        } catch (NotFoundedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}/user_products/all")
    public ResponseEntity getUsersProducts(@PathVariable(name = "id") Long userId){
        try{
            return new ResponseEntity(userService.getUserWithProducts(userId), HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);}
    }


    @GetMapping("/auth")
    public ResponseEntity authorization(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password){
        try {
            return new ResponseEntity(userService.authorization(login, password), HttpStatus.OK);
        }catch (NotFoundedException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/auth/register/")
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
            return new ResponseEntity("SaveSuccess!", HttpStatus.OK);
        }catch (AlreadyExistException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }


    @PutMapping("/{id}/favorite/recipes/{recipes}")
    public ResponseEntity addFavouriteRecipes(@PathVariable(name = "id") Long userId,
                                              @PathVariable(name = "recipes") String recs) {
        try {
            userService.addFavRecipes(userId, recs);
            return new ResponseEntity("Пользователь " + userId + " успешно обновлен", HttpStatus.OK);
        } catch (NotFoundedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}/add_product/{prodId}")
    public ResponseEntity addUsersProduct(@PathVariable(name = "userId") Long userId,
                                              @PathVariable(name = "prodId") Long prodId) {
        try {
            userService.addUsersProduct(userId,prodId);
            return new ResponseEntity("Продукт добавлен пользователю", HttpStatus.OK);
        } catch (NotFoundedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}/add_product/")
    public ResponseEntity addUsersProducts(@PathVariable(name = "userId") Long userId, @RequestBody JsonArray prodIds){
        try {
            userService.addUsersProduct(userId,prodIds.getArray());
            return new ResponseEntity("Продукт добавлен пользователю", HttpStatus.OK);
        } catch (NotFoundedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/user_products/change")
    public ResponseEntity updateUsersProduct(@RequestParam(name = "productsCount", required = false)Optional<Integer> productsCount,
                                             @RequestParam(name = "expirationDate", required = false)Optional<String> expirationDate,
                                             @RequestBody EmbProdUser embId){
        try {
             if(productsCount.isPresent()){
                 userService.updateUsersProduct(embId, productsCount.get());
             }
            if(expirationDate.isPresent()){
                userService.updateUsersProduct(embId,LocalDate.parse(expirationDate.get()));
            }
            return new ResponseEntity("Продукт обновлен", HttpStatus.OK);
        } catch (NotFoundedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try{

            return new ResponseEntity("Пользователь "+userService.deleteById(id)+" успешно удален", HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }

    @GetMapping("/{id}/user_products/get_recipes")
    public ResponseEntity getRecipesByProducts(@PathVariable Long id){
        try{
            return new ResponseEntity(userService.getRecipesByProducts(id), HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }
}


