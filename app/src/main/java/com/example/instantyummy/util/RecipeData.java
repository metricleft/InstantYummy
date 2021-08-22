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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

public class RecipeData {
    public static List<Recipe> allRecipes;
    public static HashMap<String, Integer> commonValues;
    public static TreeSet<String> ingredients;
    public static HashMap<String, Integer> allIngredients;
    private static final String RECIPES_FILENAME = "recipes.txt";
    private FragmentActivity fragmentActivity;

    public RecipeData(FragmentActivity fragmentActivity, YummyUser user) {
        this.fragmentActivity = fragmentActivity;
        allRecipes = new ArrayList<>();
        commonValues = new HashMap<>();
        allIngredients = new HashMap<>();
        ingredients = new TreeSet<>();
        ingredients = new TreeSet<>(user.getIngredients());
        parseRecipes(RECIPES_FILENAME);
    }

    public void parseRecipes(String recipeFileName) {
        int max = Integer.MIN_VALUE;
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
                for (String ingredient : curIngredients) {
                    if (allIngredients.containsKey(ingredient)) {
                        allIngredients.put(ingredient, allIngredients.get(ingredient) + 1);
                    }
                    else {
                        allIngredients.put(ingredient, 1);
                    }
                    max = Math.max(max, allIngredients.get(ingredient));
                }
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
        max++;
        sortHashMapByValues(allIngredients);
        for (String ingredient : allIngredients.keySet()) {
            commonValues.put(ingredient, max - allIngredients.get(ingredient));
        }
    }

    private HashMap<String, Integer> sortHashMapByValues(HashMap<String, Integer> passedMap) {
        //https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

}
