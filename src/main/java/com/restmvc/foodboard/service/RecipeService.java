package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.RecipeEntity;
import com.restmvc.foodboard.entity.UserEntity;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    RecipeRepo recRepo;
    public void registration(RecipeEntity recipe) throws AlreadyExistException {
        if(recRepo.findBytitle(recipe.getTitle())==null){
                recRepo.save(recipe);
        }else throw new AlreadyExistException("Блюдо с таким именем уже существует");

    }
    public RecipeEntity getRecById(Long id) throws NotFoundedException{
        Optional<RecipeEntity> recipe = recRepo.findById(id);
        if(recipe.isPresent()){
            return recipe.get();
        }else throw new NotFoundedException("Такого блюда не существует");
    }
    public Long[] parseIds(String recIds){
        String[] s = recIds.split("I");
        Long[] ids = new Long[s.length];
        for(int i=0;i<s.length;i++){
            ids[i] = Long.parseLong(s[i]);
        }
        return ids;
    }
}
