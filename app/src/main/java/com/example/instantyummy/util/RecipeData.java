package com.example.instantyummy.util;

import com.example.instantyummy.models.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeData {
    public static List<Recipe> allRecipes;
    public static HashMap<String, Integer> commonValues;
    public static List<String> ingredients;

    public RecipeData() {
        //import allRecipes
        allRecipes = new ArrayList<>();
        //precompute all ingredient common values
        commonValues = new HashMap<>();
        //ingredients pulled from parse
        ingredients = new ArrayList<>();
        //TODO: REMOVE FOLLOWING TESTING LINES
        ingredients.add("paprika");
    }
}
