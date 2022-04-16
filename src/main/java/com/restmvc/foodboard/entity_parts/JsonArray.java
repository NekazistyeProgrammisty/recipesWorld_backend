package com.restmvc.foodboard.entity_parts;

import java.io.Serializable;
import java.util.Arrays;

public class JsonArray implements Serializable {
    private Long[] array;

    public JsonArray() {
    }

    public Long[] getArray() {
        return array;
    }

    public void setArray(Long[] array) {
        this.array = array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsonArray)) return false;
        JsonArray jsonArray = (JsonArray) o;
        return Arrays.equals(array, jsonArray.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
