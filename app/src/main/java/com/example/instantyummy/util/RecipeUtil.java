package com.example.instantyummy.util;

import com.example.instantyummy.models.Recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class RecipeUtil {

    public List<Recipe> getExactRecipes(HashSet<String> ingredients) {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : RecipeData.allRecipes) {
            if (recipe.ingredients.equals(ingredients)) {
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    public List<Recipe> getPartialRecipes(HashSet<String> ingredients) {
        //assign every ingredient a "common" number
        //so basically spices and seasoning and stuff will have LOW common numbers
        //and obscure stuff like "horseradish" will have HIGH common numbers
        //add up missing ingredient common numbers
        //and only display recipes with LOW additive common numbers
        //so it'll allow recipes that are missing a lot of really common stuff like "sugar" "salt" "butter"
        //but won't really allow recipes that are missing stuff like "horseradish" "cherry juice" "peanut butter"
        TreeMap<Integer, List<Recipe>> recipes = new TreeMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Recipe recipe : RecipeData.allRecipes) {
            HashSet<String> exclusion = new HashSet<>(recipe.ingredients);
            exclusion.removeAll(ingredients);
            int commonValueSum = sumCommonValues(exclusion);
            min = Math.min(commonValueSum, min);
            max = Math.max(commonValueSum, max);
            if (!recipes.containsKey(commonValueSum)) {
                recipes.put(commonValueSum, new ArrayList<>());
            }
            recipes.get(commonValueSum).add(recipe);
        }
        List<Recipe> finalRecipes = new ArrayList<>();
        for (int i : recipes.keySet()) {
            Collections.sort(recipes.get(i));
            finalRecipes.addAll(recipes.get(i));
            if (finalRecipes.size() > 50) {
                break;
            }
        }
        Collections.sort(finalRecipes);
        return finalRecipes;
    }

    private int sumCommonValues(HashSet<String> ingredients) {
        int commonValueSum = 0;
        for (String ingredient : ingredients) {
            commonValueSum += RecipeData.commonValues.get(ingredient);
        }
        return commonValueSum;
    }
}
