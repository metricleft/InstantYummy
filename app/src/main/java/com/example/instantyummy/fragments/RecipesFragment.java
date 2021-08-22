package com.example.instantyummy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.instantyummy.adapters.IngredientAdapter;
import com.example.instantyummy.adapters.RecipeAdapter;
import com.example.instantyummy.databinding.FragmentRecipesBinding;
import com.example.instantyummy.models.Recipe;
import com.example.instantyummy.util.RecipeData;
import com.example.instantyummy.util.RecipeUtil;

import java.util.HashSet;
import java.util.List;

public class RecipesFragment extends Fragment {

    private FragmentRecipesBinding binding;
    private List<Recipe> recipes;
    private RecipeAdapter adapter;

    public RecipesFragment() {
        // Required empty public constructor
    }

    public static RecipesFragment newInstance() {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipes = RecipeUtil.getPartialRecipes(new HashSet<>(RecipeData.ingredients));
        adapter = new RecipeAdapter(getActivity(), recipes);

        binding.recyclerViewUserAvailableRecipesListBasedOnPantry.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewUserAvailableRecipesListBasedOnPantry.setLayoutManager(linearLayoutManager);

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*recipes.clear();
                recipes.addAll(RecipeUtil.getPartialRecipes(new HashSet<>(RecipeData.ingredients)));*/
                adapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
