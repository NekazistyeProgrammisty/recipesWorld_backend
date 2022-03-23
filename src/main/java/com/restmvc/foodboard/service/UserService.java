package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.UserEntity;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.UserModel;
import com.restmvc.foodboard.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RecipeService recipeService;

    public UserEntity registration(UserEntity user) throws AlreadyExistException {
        if(userRepo.findBynickName(user.getNickName())==null){
            return userRepo.save(user);
        }else throw new AlreadyExistException("Пользователь с таким именем уже существует");

    }
    public UserModel getOneUserById(Long id) throws NotFoundedException {
        Optional<UserEntity> user = userRepo.findById(id);
        if(user.isEmpty()){
            throw  new NotFoundedException("Пользователя с таким Id не существует");
        }else{return UserModel.toModel(user.get());}
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
            for (Long recId:recipeService.parseIds(recIds)){
               try{
                   user.get().addFavRecipes(recipeService.getRecById(recId));
               }catch (NotFoundedException e){throw e;}
            }
            return userId;

        }else throw new NotFoundedException("Пользователь не найден");
    }

}
