package com.restmvc.foodboard.controller;

import com.restmvc.foodboard.entity.UserEntity;
import com.restmvc.foodboard.exception.UserNickNameAlreadyExistException;
import com.restmvc.foodboard.exception.UserNotFoundedException;
import com.restmvc.foodboard.repository.UserRepo;
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
            return new ResponseEntity(userService.getOneUserById(id),HttpStatus.OK);
        }catch (UserNotFoundedException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }
    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
        }catch (UserNickNameAlreadyExistException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("SaveSuccess!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try{

            return new ResponseEntity("Пользователь "+userService.deleteById(id)+" успешно удален", HttpStatus.OK);
        }catch (UserNotFoundedException e){return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);}
    }

}
