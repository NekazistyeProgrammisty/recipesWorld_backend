package com.restmvc.foodboard.repository;

import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.entity.UserProductsEntity;
import com.restmvc.foodboard.entity_parts.EmbProdUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserProductsRepo extends CrudRepository<UserProductsEntity, Long> {
    Optional<UserProductsEntity> findByprodUserId(EmbProdUser prodUserId);

}
