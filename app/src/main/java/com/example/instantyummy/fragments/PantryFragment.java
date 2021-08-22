package com.example.instantyummy.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.instantyummy.activities.MainActivity;
import com.example.instantyummy.adapters.IngredientAdapter;
import com.example.instantyummy.databinding.FragmentPantryBinding;
import com.example.instantyummy.util.RecipeData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class PantryFragment extends Fragment {

    private FragmentPantryBinding binding;
    private TreeSet<String> ingredients;
    private IngredientAdapter adapter;
    private FragmentActivity fragmentActivity;

    public PantryFragment() {
        // Required empty public constructor
    }

    public static PantryFragment newInstance(FragmentActivity fragmentActivity, RecipeData data) {
        PantryFragment fragment = new PantryFragment();
        Bundle args = new Bundle();
        fragment.setActivity(fragmentActivity);
        fragment.setArguments(args);
        return fragment;
    }

    private void setActivity(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPantryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ingredients = RecipeData.ingredients;
        adapter = new IngredientAdapter(getActivity(), new ArrayList<>(ingredients));

        binding.recyclerViewPantryItemsInPantry.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewPantryItemsInPantry.setLayoutManager(linearLayoutManager);

        binding.buttonPantryAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragmentAddIngredient addIngredientFragment = DialogFragmentAddIngredient.newInstance();
                addIngredientFragment.show(fragmentActivity.getSupportFragmentManager(), "fragment_add_ingredient");
            }
        });

        NavController navController = NavHostFragment.findNavController(this);
        // We use a String here, but any type that can be put in a Bundle is supported
        MutableLiveData<String> createRewardLiveData = Objects.requireNonNull(navController.getCurrentBackStackEntry())
                .getSavedStateHandle()
                .getLiveData("ingredient");
        createRewardLiveData.observe(getViewLifecycleOwner(), ingredient -> {
            if (!ingredients.contains(ingredient)) {
                ingredients.add(ingredient);
            }
            adapter.notifyDataSetChanged();
            binding.recyclerViewPantryItemsInPantry.smoothScrollToPosition(adapter.getItemCount()-1);
        });
    }
}
