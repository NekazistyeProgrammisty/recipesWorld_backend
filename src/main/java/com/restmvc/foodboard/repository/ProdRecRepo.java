package com.restmvc.foodboard.repository;

import com.restmvc.foodboard.entity.ProdRecEntity;
import com.restmvc.foodboard.entity_parts.EmbProdRecId;
import org.springframework.data.repository.CrudRepository;

public interface ProdRecRepo extends CrudRepository<ProdRecEntity, EmbProdRecId> {

}
