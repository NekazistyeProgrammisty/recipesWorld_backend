package com.restmvc.foodboard.repository;

import com.restmvc.foodboard.entity.ProdRecEntity;
import com.restmvc.foodboard.entity.ProductEntity;
import com.restmvc.foodboard.entity_parts.EmbProdRecId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdRecRepo extends CrudRepository<ProdRecEntity, EmbProdRecId> {

}
