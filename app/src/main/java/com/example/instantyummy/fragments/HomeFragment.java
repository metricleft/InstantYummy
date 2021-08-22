package com.example.instantyummy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instantyummy.R;
import com.example.instantyummy.activities.MainActivity;
import com.example.instantyummy.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity mainActivity = (MainActivity) requireActivity();

        binding.buttonHomeViewFeaturedDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.navController.navigate(R.id.action_home_to_featured);
                mainActivity.selectedItem = R.id.featured;
                mainActivity.binding.bottomNavigationBar.setSelectedItemId(mainActivity.selectedItem);
            }
        });

        binding.buttonHomeViewPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.navController.navigate(R.id.action_home_to_pantry);
                mainActivity.selectedItem = R.id.pantry;
                mainActivity.binding.bottomNavigationBar.setSelectedItemId(mainActivity.selectedItem);
            }
        });

        binding.buttonHomeViewRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.navController.navigate(R.id.action_home_to_recipes);
                mainActivity.selectedItem = R.id.recipes;
                mainActivity.binding.bottomNavigationBar.setSelectedItemId(mainActivity.selectedItem);
            }
        });

    }
}