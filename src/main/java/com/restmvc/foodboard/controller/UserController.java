package com.restmvc.foodboard.controller;

import com.restmvc.foodboard.entity.UserEntity;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.UserModelPure;
import com.restmvc.foodboard.service.RecipeService;
import com.restmvc.foodboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity getOneUserById(@PathVariable Long id){
        try{
            UserModelPure model = new UserModelPure();
            model.toModel(userService.getOneUserById(id));
            return new ResponseEntity(model,HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }
    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
            return new ResponseEntity("SaveSuccess!", HttpStatus.OK);
        }catch (AlreadyExistException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try{

            return new ResponseEntity("Пользователь "+userService.deleteById(id)+" успешно удален", HttpStatus.OK);
        }catch (NotFoundedException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }

            @PutMapping("/{id}/favorite/recipes/{recipes}")
            public ResponseEntity addFavouriteRecipes(@PathVariable(name = "id") Long userId,
                                                      @PathVariable(name = "recipes") String recs) {
                try {
                    Long idUser = userService.addFavRecipes(userId, recs);
                    return new ResponseEntity("Пользователь " + idUser + " успешно обновлен", HttpStatus.OK);
                } catch (NotFoundedException e) {
                    return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }

            @GetMapping("/{id}/favorite/recipes")
            public ResponseEntity getUserRecipes(@PathVariable(name = "id") Long id) {
                try {
                    return new ResponseEntity(userService.getUserWithFavouriteRecipes(id), HttpStatus.OK);
                } catch (NotFoundedException e) {
                    return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
        }


