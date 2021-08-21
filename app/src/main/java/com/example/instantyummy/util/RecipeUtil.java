package com.example.instantyummy.util;

import com.example.instantyummy.models.Recipe;

import java.util.HashSet;
import java.util.List;

public class RecipeUtil {

    public void getExactRecipes(HashSet<String> ingredients) {
        //for all recipes,
        //if size of ingredients not the same, ignore
        //if
    }

    public void getPartialRecipes(List<String> ingredients) {
        //assign every ingredient a "common" number
        //so basically spices and seasoning and stuff will have LOW common numbers
        //and obscure stuff like "cherry juice" will have HIGH common numbers
        //add up missing ingredient common numbers
        //and only display recipes with LOW additive common numbers
        //so it'll allow recipes that are missing a lot of really common stuff like "sugar" "salt" "butter"
        //but won't really allow recipes that are missing stuff like "horseradish"


    }
}
