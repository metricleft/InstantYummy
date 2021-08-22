package com.example.instantyummy.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Recipe implements Comparable<Recipe>{
    public String recipeName;
    public HashSet<String> ingredients;
    public String photoFileName;
    public HashSet<String> displayIngredients;
    public String instructions;

    /*public Recipe(JSONObject recipe) {
        try {
            this.recipeName = recipe.getString("title");
            this.ingredients = new HashSet<>((List<String>) recipe.getJSONArray("ingredients"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        photoFileName = recipe.getString("photo");
        //cookingInstruments = new ArrayList<>();
    }*/

    public Recipe() {
        recipeName = "";
        ingredients = new HashSet<>();
        displayIngredients = new HashSet<>();
        photoFileName = "";
        instructions = "";
    }

    @Override
    public int compareTo(Recipe recipe) {
        //stuff to compare size of ingredient list, prep time, etc
        return this.recipeName.compareTo(recipe.recipeName);
    }
}
