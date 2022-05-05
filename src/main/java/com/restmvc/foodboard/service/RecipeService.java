package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.*;
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

import java.util.*;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepo recRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProdRecRepo prodRecRepo;

    public void newRecipe(RecipeEntity recipe) throws AlreadyExistException {
        if(recRepo.findBytitle(recipe.getTitle())==null){
                recipe.setProductsNum(0);
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
            recipe.setProductsNum(recipe.getProductsNum()+1);
            prodRecRepo.save(pr);
            recRepo.save(recipe);
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

/*Алгоритм выдачи рецептов по продуктам пользователя:
* Если в рецепте есть хоть один продукт, которого нет у пользователя, и у этого продукта важность >5, то этот рецепт не подходит.
* Соответственно если рцепт состоит только из продуктов, которые есть у пользователя, или если в рецепте продукты, которых нет у пользователя, не важны(<5), то такой рецепт подходит
* Если поставить всем продуктам важность>5, то алгоритм будет выдавать только те рецепты, которые целиком состоят из продуктов пользователя*/
    public ArrayList<RecipeModelPure> findRecipesByProducts(ArrayList<UserProductsEntity> productsList){
        Integer productsCount = productsList.size();
        ArrayList<RecipeEntity> recipeEntities = new ArrayList<>();
        ArrayList<RecipeEntity> recipeEntitiesForDelition = new ArrayList<>();
        ArrayList<RecipeModelPure> finalRecipes = new ArrayList<>();
        ArrayList<ProdRecEntity> prodRecListOfProducts = new ArrayList<>();
        for(UserProductsEntity userProds:productsList){//составляем список ВСЕХ рецептов в которых используются переданные продукты.
            ArrayList<ProdRecEntity> prodRecList = new ArrayList<>(userProds.getProduct().getRecipes());
            prodRecListOfProducts.addAll(prodRecList);//вместе с тем составляем список prodRec сущностей переданных ПРОДУКТОВ
            for(ProdRecEntity prodRecEntity:prodRecList){
                if(!recipeEntities.contains(prodRecEntity.getRecipe())) {
                    recipeEntities.add(prodRecEntity.getRecipe());
                }
            }
        }
        for(RecipeEntity recipe:recipeEntities){
            ArrayList<ProdRecEntity> prodRecListOfRecipe = new ArrayList<>(recipe.getProducts());
            for(ProdRecEntity prodRecOfRecipe:prodRecListOfRecipe){//у каждого рецепта идем по списку prodRec сущностей
                if(!prodRecListOfProducts.contains(prodRecOfRecipe)){//если такой сущности нет в переданных продуктах и
                    if(prodRecOfRecipe.getProductImportance()>5){//и ее важность больше пяти, то мы не можем выдать этот рецепт
                        recipeEntitiesForDelition.add(prodRecOfRecipe.getRecipe());
                        break;
                    }
                }
            }
        }
        if(!recipeEntitiesForDelition.isEmpty()) {
            recipeEntities.removeAll(recipeEntitiesForDelition);
        }
        for(RecipeEntity recipe:recipeEntities) {
            RecipeModelPure model = new RecipeModelPure();
            model.toModel(recipe);
            if(!finalRecipes.contains(model)) {
                finalRecipes.add(model);
            }
        }
        return finalRecipes;
//        productsList.sort(new Comparator<UserProductsEntity>() {
//            @Override
//            public int compare(UserProductsEntity o1, UserProductsEntity o2) {
//                Integer size1 = o1.getProduct().getRecipes().size();
//                Integer size2 = o2.getProduct().getRecipes().size();
//                return size1.compareTo(size2);
//            }
//        });
//        UserProductsEntity usersProdFirst = productsList.get(0);
//        for(ProdRecEntity prodRec:usersProdFirst.getProduct().getRecipes()){
//
//            recipeEntities.add(prodRec.getRecipe());
//        }
//        productsList.remove(usersProdFirst);
//
//        for(UserProductsEntity usersProd:productsList){
//            for(RecipeEntity recipe:recipeEntities){
//            EmbProdRecId embId = new EmbProdRecId();
//                embId.setRecIdComp(recipe.getRecipeId());
//                embId.setProdIdComp(usersProd.getProduct().getIdProd());
//                Optional<ProdRecEntity> prodRec = prodRecRepo.findById(embId);
//                if(prodRec.isPresent()){
//                    if(!usersProd.getProduct().getRecipes().contains(prodRec.get())){
//                        recipeEntities.remove(recipe);
//                    };
//                }else {recipeEntities.remove
//            }
//        }
//        for(UserProductsEntity usersProd:productsList){
//            System.out.println(usersProd.getProduct().getRecipes().size());
//            for(ProdRecEntity prodRec:usersProd.getProduct().getRecipes()){
//                RecipeEntity recipe =prodRec.getRecipe();
//                if(recipeMap.containsKey(recipe)){
//                    recipeMap.replace(recipe,recipeMap.get(recipe)+1);
//                }
//            }
//        }
//        for(RecipeEntity key:recipeMap.keySet()){
//            if(recipeMap.get(key).equals(productsCount)){
//                RecipeModelPure model = new RecipeModelPure();
//                model.toModel(key);
//                finalRecipes.add(model);
//            }
//        }
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
