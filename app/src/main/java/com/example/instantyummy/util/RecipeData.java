package com.example.instantyummy.util;

import android.content.res.Resources;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.example.instantyummy.R;
import com.example.instantyummy.models.Recipe;
import com.example.instantyummy.models.YummyUser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class RecipeData {
    public static List<Recipe> allRecipes;
    public static HashMap<String, Integer> commonValues;
    public static TreeSet<String> ingredients;
    public static TreeSet<String> allIngredients;
    private static final String RECIPES_FILENAME = "recipes.txt";
    private FragmentActivity fragmentActivity;

    public RecipeData(FragmentActivity fragmentActivity, YummyUser user) {
        this.fragmentActivity = fragmentActivity;
        allRecipes = new ArrayList<>();
        commonValues = new HashMap<>();
        allIngredients = new TreeSet<>();
        ingredients = new TreeSet<>();
        ingredients = new TreeSet<>(user.getIngredients());
        parseRecipes(RECIPES_FILENAME);
    }

    public void parseRecipes(String recipeFileName) {
        Resources resources = fragmentActivity.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.recipes);
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                Recipe recipe = new Recipe();
                String s = input.readLine();
                if (s == null) break;
                recipe.recipeName = s;
                s = input.readLine();
                recipe.photoFileName = s;
                s = input.readLine();
                List<String> curIngredients = Arrays.asList(s.split(", "));
                allIngredients.addAll(curIngredients);
                recipe.ingredients = new HashSet<>(curIngredients);
                s = input.readLine();
                recipe.displayIngredients = new HashSet<>(Arrays.asList(s.split(", ")));
                s = input.readLine();
                recipe.instructions = s;
                allRecipes.add(recipe);
            }
        } catch (IOException ie) {
            Log.e("RecipeData", "Error reading recipe file.");
        }
    }
}
