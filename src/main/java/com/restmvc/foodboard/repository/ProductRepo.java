package com.restmvc.foodboard.repository;


import com.restmvc.foodboard.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<ProductEntity,Long> {
    ProductEntity findBytitle(String title);
}
