package com.restmvc.foodboard.entity_parts;


import java.io.Serializable;
import java.util.Objects;

public class ProdRecId implements Serializable {
    private Long prodId;
    private Long recId;

    public ProdRecId() {
    }

    public ProdRecId(Long prodId, Long recId) {
        this.prodId = prodId;
        this.recId = recId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdRecId)) return false;
        ProdRecId prodRecId = (ProdRecId) o;
        return Objects.equals(prodId, prodRecId.prodId) && Objects.equals(recId, prodRecId.recId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodId, recId);
    }
}
