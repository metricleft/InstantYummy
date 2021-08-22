package com.example.instantyummy.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instantyummy.R;
import com.example.instantyummy.databinding.ItemIngredientBinding;
import com.example.instantyummy.databinding.ItemRecipeBinding;
import com.example.instantyummy.fragments.DialogFragmentRecipeDetails;
import com.example.instantyummy.models.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe> recipes;
    private FragmentActivity fragmentActivity;

    public RecipeAdapter(FragmentActivity fragmentActivity, List<Recipe> recipes) {
        this.fragmentActivity = fragmentActivity;
        this.recipes = recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeBinding binding = ItemRecipeBinding.inflate(LayoutInflater.from(fragmentActivity), parent, false);
        return new RecipeAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemRecipeBinding binding;

        public ViewHolder(@NonNull ItemRecipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        public void bind(Recipe recipe) {
            Resources resources = fragmentActivity.getResources();
            binding.imageViewRecipePhoto.setImageDrawable(AppCompatResources.getDrawable(fragmentActivity, resources.getIdentifier(recipe.photoFileName, "drawable", fragmentActivity.getPackageName())));
            binding.textViewRecipeTitle.setText(recipe.recipeName);
            String ingredients = "";
            for (String ingredient : recipe.ingredients) {
                ingredients += ingredient + ", ";
            }
            if (!ingredients.isEmpty()) {
                ingredients = ingredients.substring(0, ingredients.length()-2);
            }
            binding.textViewIngredients.setText(ingredients);
        }

        @Override
        public void onClick(View view) {
            DialogFragmentRecipeDetails recipeDetailsFragment = DialogFragmentRecipeDetails.newInstance(fragmentActivity, recipes.get(getAdapterPosition()));
            recipeDetailsFragment.show(fragmentActivity.getSupportFragmentManager(), "fragment_recipe_details");
        }
    }
}