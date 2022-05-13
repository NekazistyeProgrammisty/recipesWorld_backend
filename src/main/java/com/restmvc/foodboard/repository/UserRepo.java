package com.restmvc.foodboard.repository;

import com.restmvc.foodboard.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepo extends CrudRepository<UserEntity,Long> {
    Optional<UserEntity> findBynickName(String nickName);

}
