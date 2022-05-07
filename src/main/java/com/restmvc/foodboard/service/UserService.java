package com.restmvc.foodboard.service;

import com.restmvc.foodboard.entity.*;
import com.restmvc.foodboard.entity_parts.EmbProdUser;
import com.restmvc.foodboard.exception.AlreadyExistException;
import com.restmvc.foodboard.exception.NotFoundedException;
import com.restmvc.foodboard.model.RecipeModelPure;
import com.restmvc.foodboard.model.UserModelProducts;
import com.restmvc.foodboard.model.UserModelPure;
import com.restmvc.foodboard.model.UserModelRecipes;
import com.restmvc.foodboard.repository.UserProductsRepo;
import com.restmvc.foodboard.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserProductsRepo userProductsRepo;

    public void registration(UserEntity user) throws AlreadyExistException {
        if(userRepo.findBynickName(user.getNickName())==null){
            userRepo.save(user);
        }else throw new AlreadyExistException("Пользователь с таким именем уже существует");
    }


    public UserEntity getOneUserById(Long id) throws NotFoundedException {
        Optional<UserEntity> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }else throw new NotFoundedException("Пользователь не найден");
    }


    public UserModelRecipes getUserWithFavouriteRecipes(Long userId) throws NotFoundedException{
        Optional<UserEntity> user = userRepo.findById(userId);
        if(user.isPresent()){
            UserModelRecipes model = new UserModelRecipes();
            model.toModel(user.get());
            return model;
        }else{throw new NotFoundedException("Пользователь не найден");}
    }


    public Long deleteById(Long id) throws NotFoundedException {
        Optional<UserEntity> userOpt = userRepo.findById(id);
        if(userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            ArrayList<RecipeEntity> recipesToDelete = new ArrayList<>(user.getFavRecipes());
            for (RecipeEntity recipe:recipesToDelete){
                user.removeFavRecipes(recipe);
            }
            userRepo.deleteById(id);
            return id;
        }else throw new NotFoundedException("Пользователя "+id+" не существует");
    }


    public void addFavRecipes(Long userId, String recIds) throws NotFoundedException{
        Optional<UserEntity> user = userRepo.findById(userId);
        if(user.isPresent()){
            UserEntity userEnt = user.get();
            for (Long recId:recipeService.parseIds(recIds)){
               try{
                   userEnt.addFavRecipes(recipeService.getOneRecById(recId));
               }catch (NotFoundedException e){throw e;}
            }
            userRepo.save(userEnt);
        }else throw new NotFoundedException("Пользователь не найден");
    }


    public UserProductsEntity addUsersProduct(UserEntity user, ProductEntity product){
        UserProductsEntity usrProd = new UserProductsEntity();
        EmbProdUser embId = new EmbProdUser();
        embId.setUserIdComp(user.getId());
        embId.setProdIdComp(product.getIdProd());
        usrProd.setProdUserId(embId);
        usrProd.setTitle(product.getTitle());
        usrProd.setCalorie(product.getCalorie());
        usrProd.setExpirationDate(LocalDate.now().plusDays(product.getFreshDays()));
        usrProd.setProductsCount(1);
        usrProd.setUser(user);
        usrProd.setProduct(product);
//        user.getProducts().add(usrProd);
//        product.getUserProd().add(usrProd);
        return usrProd;
    }

    public void addUsersProduct(Long userId, Long productId) throws NotFoundedException{
        Optional<UserEntity> usr = userRepo.findById(userId);
        if(usr.isPresent()){
            try {
                ProductEntity prod = productService.getProdById(productId);
                userProductsRepo.save(addUsersProduct(usr.get(), prod));
            }catch (NotFoundedException e){throw e;}
        }
    }

    public void addUsersProduct(Long userId, Long[] productIds)throws NotFoundedException{
        Optional<UserEntity> usr = userRepo.findById(userId);
        if(usr.isPresent()){
            try {
                UserEntity user = usr.get();
                ArrayList<UserProductsEntity> usersProds = new ArrayList<>();
                for(Long productId:productIds){
                    ProductEntity prod = productService.getProdById(productId);
                    usersProds.add(addUsersProduct(user,prod));
                }
                userProductsRepo.saveAll(usersProds);
            }catch (NotFoundedException e){throw e;}
        }
    }


    public void updateUsersProduct(EmbProdUser embId, Integer productsCount)throws NotFoundedException{
        Optional<UserProductsEntity> usersProd = userProductsRepo.findByprodUserId(embId);
        if (usersProd.isPresent()){
            UserProductsEntity usersProdEntity = usersProd.get();
            usersProdEntity.setProductsCount(productsCount);
            userProductsRepo.save(usersProdEntity);
        }else throw new NotFoundedException("у пользователя нет такого продукта");
    }

    public void updateUsersProduct(EmbProdUser embId, LocalDate expirationDate)throws NotFoundedException{
        Optional<UserProductsEntity> usersProd = userProductsRepo.findByprodUserId(embId);
        if (usersProd.isPresent()){
            UserProductsEntity usersProdEntity = usersProd.get();
            usersProdEntity.setExpirationDate(expirationDate);
            userProductsRepo.save(usersProdEntity);
        }else throw new NotFoundedException("у пользователя нет такого продукта");
    }

    public UserModelProducts getUserWithProducts(Long userId) throws NotFoundedException{
        Optional<UserEntity> usr = userRepo.findById(userId);
        if(usr.isPresent()){
            UserModelProducts model = new UserModelProducts();
            model.toModel(usr.get());
            return model;
        }else throw new NotFoundedException("Пользователь не найден");
    }


    public ArrayList<RecipeModelPure> getRecipesByProducts(Long id) throws NotFoundedException{
        Optional<UserEntity> user = userRepo.findById(id);
        if(user.isPresent()){
            UserEntity userEntity = user.get();
            ArrayList<UserProductsEntity> prods = new ArrayList<>();
            prods.addAll(userEntity.getProducts());
            if (!prods.isEmpty()) {
                return recipeService.findRecipesByProducts(prods);
            }else throw new NotFoundedException("У пользователя не добавлены никакие продукты");
        }else throw new NotFoundedException("Пользователь не найден");
    }

    public void removeRecipe(RecipeEntity recipe, UserEntity user){
        user.removeFavRecipes(recipe);
        userRepo.save(user);
    }
}
