package com.restmvc.foodboard.repository;

import com.restmvc.foodboard.entity.UserProductsEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserProductsRepo extends CrudRepository<UserProductsEntity, Long> {
}
