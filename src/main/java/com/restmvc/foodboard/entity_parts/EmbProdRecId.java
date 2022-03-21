package com.restmvc.foodboard.entity_parts;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmbProdRecId implements Serializable {

    private Long prodIdComp;

    private Long recIdComp;

    public EmbProdRecId() {
    }



    public Long getProdIdComp() {
        return prodIdComp;
    }

    public void setProdIdComp(Long prodIdComp) {
        this.prodIdComp = prodIdComp;
    }

    public Long getRecIdComp() {
        return recIdComp;
    }

    public void setRecIdComp(Long recIdComp) {
        this.recIdComp = recIdComp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmbProdRecId)) return false;
        EmbProdRecId that = (EmbProdRecId) o;
        return Objects.equals(prodIdComp, that.prodIdComp) && Objects.equals(recIdComp, that.recIdComp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodIdComp, recIdComp);
    }
}
