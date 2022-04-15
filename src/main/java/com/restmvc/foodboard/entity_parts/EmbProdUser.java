package com.restmvc.foodboard.entity_parts;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmbProdUser implements Serializable {
    private Long prodIdComp;

    private Long userIdComp;

    public EmbProdUser() {
    }

    public Long getProdIdComp() {
        return prodIdComp;
    }

    public void setProdIdComp(Long prodIdComp) {
        this.prodIdComp = prodIdComp;
    }

    public Long getUserIdComp() {
        return userIdComp;
    }

    public void setUserIdComp(Long userIdComp) {
        this.userIdComp = userIdComp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmbProdUser)) return false;
        EmbProdUser that = (EmbProdUser) o;
        return Objects.equals(prodIdComp, that.prodIdComp) && Objects.equals(userIdComp, that.userIdComp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodIdComp, userIdComp);
    }
}
