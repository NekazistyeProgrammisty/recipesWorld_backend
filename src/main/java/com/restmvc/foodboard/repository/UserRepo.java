package com.restmvc.foodboard.repository;

import com.restmvc.foodboard.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepo extends CrudRepository<UserEntity,Long> {
    UserEntity findBynickName(String nickName);


}
