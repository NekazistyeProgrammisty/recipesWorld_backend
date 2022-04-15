package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.UserEntity;
import com.restmvc.foodboard.entity.UserProductsEntity;

import java.util.ArrayList;
import java.util.List;

public class UserModelProducts extends UserModelPure{
    private List<UserProductsPure> products = new ArrayList<>();

    @Override
    public void toModel(UserEntity user){
        super.toModel(user);
        for (UserProductsEntity usrProd:user.getProducts()){
            UserProductsPure usrProdPure = new UserProductsPure();
            usrProdPure.toModel(usrProd);
            products.add(usrProdPure);
        }
    }

    public UserModelProducts() {
        super();
    }

    public List<UserProductsPure> getProducts() {
        return products;
    }

    public void setProducts(List<UserProductsPure> products) {
        this.products = products;
    }
}
