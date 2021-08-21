package com.example.instantyummy.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Recipe implements Comparable<Recipe>{
    public String recipeName;
    public HashSet<String> ingredients;
    //public List<String> cookingInstruments;

    public Recipe(JSONObject recipe) {
        try {
            this.recipeName = recipe.getString("title");
            this.ingredients = new HashSet<>((List<String>) recipe.getJSONArray("ingredients"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        //cookingInstruments = new ArrayList<>();
    }

    @Override
    public int compareTo(Recipe recipe) {
        //stuff to compare size of ingredient list, prep time, etc
        return this.recipeName.compareTo(recipe.recipeName);
    }
}
