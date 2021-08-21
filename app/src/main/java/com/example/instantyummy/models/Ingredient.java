package com.example.instantyummy.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Ingredient")
public class Ingredient extends ParseObject {
    public final static String KEY_NAME = "name";

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }
}
