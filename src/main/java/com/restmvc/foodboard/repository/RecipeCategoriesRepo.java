package com.restmvc.foodboard.repository;

import com.restmvc.foodboard.entity.RecipeCategoriesEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecipeCategoriesRepo extends CrudRepository<RecipeCategoriesEntity, Long> {
}
