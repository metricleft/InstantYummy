package com.example.instantyummy.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.instantyummy.adapters.IngredientAdapter;
import com.example.instantyummy.databinding.FragmentAddIngredientsBinding;
import com.example.instantyummy.databinding.FragmentRecipeDetailsBinding;
import com.example.instantyummy.models.Recipe;
import com.example.instantyummy.util.RecipeData;

public class DialogFragmentRecipeDetails extends DialogFragment {

    private FragmentRecipeDetailsBinding binding;
    private FragmentActivity fragmentActivity;
    private IngredientAdapter adapter;
    private Recipe recipe;

    public DialogFragmentRecipeDetails() {
        // Required empty public constructor
    }

    public static DialogFragmentRecipeDetails newInstance(FragmentActivity fragmentActivity, Recipe recipe) {
        DialogFragmentRecipeDetails fragment = new DialogFragmentRecipeDetails();
        Bundle args = new Bundle();
        fragment.setActivity(fragmentActivity);
        fragment.setRecipe(recipe);
        fragment.setArguments(args);
        return fragment;
    }

    private void setActivity(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    private void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.textViewRecipeDetailsRecipeTitleLabel.setText(recipe.recipeName);
        binding.imageViewRecipePhoto.setImageDrawable(AppCompatResources.getDrawable(fragmentActivity, fragmentActivity.getResources().getIdentifier(recipe.photoFileName, "drawable", fragmentActivity.getPackageName())));
        StringBuilder displayIngredients = new StringBuilder();
        for (String ingredient : recipe.displayIngredients) {
            displayIngredients.append(ingredient).append("\n");
        }
        binding.textViewRecipeDetailsIngredientsList.setText(displayIngredients.toString());
        binding.textViewRecipeDetailsPreparationStepsList.setText(recipe.instructions);
    }
}