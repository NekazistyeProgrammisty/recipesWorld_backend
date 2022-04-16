package com.restmvc.foodboard.repository;


import com.restmvc.foodboard.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<ProductEntity,Long> {
    ProductEntity findBytitle(String title);
    List<ProductEntity> findByTitleLike(String likePattern);
}
