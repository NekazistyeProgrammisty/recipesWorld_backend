package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.ProdRecEntity;
import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.entity.RecipeEntity;
import com.restmvc.foodboard.entity.UserEntity;
import com.restmvc.foodboard.entity_parts.EmbProdRecId;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.RecipeModelProd;
import com.restmvc.foodboard.model.RecipeModelPure;
import com.restmvc.foodboard.model.RecipeModelUsers;
import com.restmvc.foodboard.repository.ProdRecRepo;
import com.restmvc.foodboard.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    RecipeRepo recRepo;
    @Autowired
    ProductService productService;
    @Autowired
    ProdRecRepo prodRecRepo;

    public void newRecipe(RecipeEntity recipe) throws AlreadyExistException {
        if(recRepo.findBytitle(recipe.getTitle())==null){
                recRepo.save(recipe);
        }else throw new AlreadyExistException("Блюдо с таким названием уже существует");

    }


    public void addProduct(Long recId, Long prodId, Integer importance)throws NotFoundedException{
        ProductEntity product = productService.getProdById(prodId);
        Optional<RecipeEntity> rec= recRepo.findById(recId);
        if(rec.isPresent()){
            RecipeEntity recipe = rec.get();
            EmbProdRecId id = new EmbProdRecId();
            id.setProdIdComp(product.getIdProd());
            id.setRecIdComp(recipe.getRecipeId());
            ProdRecEntity pr = new ProdRecEntity();//создаем новую строку совместной таблицы и заполняем его
            pr.setProdRecId(id);
            pr.setProduct(product);
            pr.setProductImportance(importance);
            pr.setRecipe(recipe);
            prodRecRepo.save(pr);
        }else throw new NotFoundedException("Блюда "+recId+" нет в базе");
      }

      public RecipeModelProd getWithProducts(Long recId)throws NotFoundedException{
            try{
                RecipeEntity rec = getOneRecById(recId);
                RecipeModelProd recModel = new RecipeModelProd();
                recModel.toModel(rec);
                return recModel;
            }catch (NotFoundedException e){throw e;}

      }

    public RecipeEntity getOneRecById(Long id) throws NotFoundedException{
        Optional<RecipeEntity> recipe = recRepo.findById(id);
        if(recipe.isPresent()){
            return recipe.get();
        }else throw new NotFoundedException("Рецпт не найден");
    }


    public RecipeModelUsers getWithUsers(Long id) throws NotFoundedException{
        Optional<RecipeEntity> recipe = recRepo.findById(id);
        if(recipe.isPresent()){
            RecipeModelUsers model = new RecipeModelUsers();
            model.toModel(recipe.get());
            return model;
        }else throw new NotFoundedException("Рецепт не найден");
    }

    public void deleteRecipe(Long id)throws NotFoundedException{
        Optional<RecipeEntity> recipe = recRepo.findById(id);
        if (recipe.isPresent()){
            recRepo.deleteById(id);
        }else{
            throw new NotFoundedException("Рецепт" + id + "не найден");
        }
    }

    public ArrayList<RecipeModelPure> getAll() throws NotFoundedException{
        ArrayList<RecipeEntity> allEntities = (ArrayList<RecipeEntity>) recRepo.findAll();
        if(!allEntities.isEmpty()) {
            ArrayList<RecipeModelPure> pureRecs = new ArrayList<>();
            for (RecipeEntity recipe : allEntities) {
                RecipeModelPure model = new RecipeModelPure();
                model.toModel(recipe);
                pureRecs.add(model);
            }
            return pureRecs;
        }else throw new NotFoundedException("Рецепты не найдены");
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
