package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.UserEntity;
import com.restmvc.foodboard.exception.UserNickNameAlreadyExistException;
import com.restmvc.foodboard.exception.UserNotFoundedException;
import com.restmvc.foodboard.model.UserModel;
import com.restmvc.foodboard.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public UserEntity registration(UserEntity user) throws UserNickNameAlreadyExistException{
        if(userRepo.findBynickName(user.getNickName())==null){
            return userRepo.save(user);
        }else throw new UserNickNameAlreadyExistException("Пользователь с таким именем уже существует");

    }
    public UserModel getOneUserById(Long id) throws UserNotFoundedException {
        Optional<UserEntity> user = userRepo.findById(id);
        if(user.isEmpty()){
            throw  new UserNotFoundedException("Пользователя с таким Id не существует");
        }else{return UserModel.toModel(user.get());}
    }

    public Long deleteById(Long id) throws UserNotFoundedException{
        if(userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return id;
        }else throw new UserNotFoundedException("Пользователя "+id+" не существует");
    }
}
