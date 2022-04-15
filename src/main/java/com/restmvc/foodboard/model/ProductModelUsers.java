package com.restmvc.foodboard.model;

import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.entity.UserProductsEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductModelUsers extends ProductModelPure{
    private List<UserModelPure> users = new ArrayList<>();

    @Override
    public void toModel(ProductEntity product){
        super.toModel(product);
        for(UserProductsEntity usrProd:product.getUserProd()){
            UserModelPure user = new UserModelPure();
            user.toModel(usrProd.getUser());
            users.add(user);
        }

    }

    public ProductModelUsers() {
        super();
    }

    public List<UserModelPure> getUsers() {
        return users;
    }

    public void setUsers(List<UserModelPure> users) {
        this.users = users;
    }
}
