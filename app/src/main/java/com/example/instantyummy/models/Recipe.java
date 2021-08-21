package com.example.instantyummy.models;

import java.util.List;

public class Recipe implements Comparable<Recipe>{
    public String recipeName;
    public List<String> ingredients;


    @Override
    public int compareTo(Recipe recipe) {
        //stuff to compare size of ingredient list, prep time, etc
        return this.recipeName.compareTo(recipe.recipeName);
    }
}
