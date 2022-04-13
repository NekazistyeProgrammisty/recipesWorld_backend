package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.UserEntity;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.UserModelPure;
import com.restmvc.foodboard.model.UserModelRecipes;
import com.restmvc.foodboard.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RecipeService recipeService;

    public void registration(UserEntity user) throws AlreadyExistException {
        if(userRepo.findBynickName(user.getNickName())==null){
            userRepo.save(user);
        }else throw new AlreadyExistException("Пользователь с таким именем уже существует");
    }


    public UserEntity getOneUserById(Long id) throws NotFoundedException {
        Optional<UserEntity> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }else throw new NotFoundedException("Пользователь не найден");
    }


    public UserModelRecipes getUserWithFavouriteRecipes(Long userId) throws NotFoundedException{
        Optional<UserEntity> user = userRepo.findById(userId);
        if(user.isPresent()){
            UserModelRecipes model = new UserModelRecipes();
            model.toModel(user.get());
            return model;
        }else{throw new NotFoundedException("Пользователь не найден");}
    }
    public Long deleteById(Long id) throws NotFoundedException {
        if(userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return id;
        }else throw new NotFoundedException("Пользователя "+id+" не существует");
    }
    public Long addFavRecipes(Long userId, String recIds) throws NotFoundedException{
        Optional<UserEntity> user = userRepo.findById(userId);
        if(user.isPresent()){
            UserEntity userEnt = user.get();
            for (Long recId:recipeService.parseIds(recIds)){
               try{
                   userEnt.addFavRecipes(recipeService.getRecById(recId));
               }catch (NotFoundedException e){throw e;}
            }
            userRepo.save(userEnt);
            return userId;

        }else throw new NotFoundedException("Пользователь не найден");
    }

}
