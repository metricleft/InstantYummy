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

import com.example.instantyummy.adapters.RecipeAdapter;
import com.example.instantyummy.databinding.FragmentFeaturedBinding;
import com.example.instantyummy.models.Recipe;
import com.example.instantyummy.util.RecipeData;

import java.util.ArrayList;
import java.util.List;

public class FeaturedFragment extends Fragment {

    private FragmentFeaturedBinding binding;

    public FeaturedFragment() {
        // Required empty public constructor
    }

    public static FeaturedFragment newInstance() {
        FeaturedFragment fragment = new FeaturedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFeaturedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            recipes.add(RecipeData.allRecipes.get((int)(Math.random() * RecipeData.allRecipes.size())));
        }
        RecipeAdapter adapter = new RecipeAdapter(requireActivity(), recipes);
        binding.recyclerViewFeaturedRecipesList.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewFeaturedRecipesList.setLayoutManager(linearLayoutManager);

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recipes.clear();
                for (int i = 0; i < 3; i++) {
                    recipes.add(RecipeData.allRecipes.get((int)(Math.random() * RecipeData.allRecipes.size())));
                }
                adapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
