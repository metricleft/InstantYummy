package com.example.instantyummy.models;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class YummyUser extends ParseUser {
    public static final String KEY_INGREDIENTS = "ingredients";

    public YummyUser() {}

    public YummyUser(HashSet<String> ingredients) {
        setIngredients(ingredients);
    }

    public HashSet<String> getIngredients() {
        List<String> ingredients = getList(KEY_INGREDIENTS);
        if (ingredients == null) {
            return new HashSet<>();
        }
        return new HashSet<>(ingredients);
    }

    public void setIngredients(HashSet<String> ingredients) {
        List<String> ingredientsList = new ArrayList<>(ingredients);
        put(KEY_INGREDIENTS, ingredientsList);
    }
}
