package com.restmvc.foodboard.repository;

import com.restmvc.foodboard.entity.RecipeEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepo extends CrudRepository<RecipeEntity,Long> {
    RecipeEntity findBytitle(String title);
}
