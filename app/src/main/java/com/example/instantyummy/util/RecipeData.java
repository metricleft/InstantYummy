package com.example.instantyummy.util;

import com.example.instantyummy.models.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeData {
    public static List<Recipe> allRecipes;
    public static HashMap<String, Integer> commonValues;

    public RecipeData() {
        //import allRecipes
        allRecipes = new ArrayList<>();
        //precompute all ingredient common values
        commonValues = new HashMap<>();
    }
}
