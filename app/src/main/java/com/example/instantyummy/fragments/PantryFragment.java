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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    private IngredientAdapter adapter;

    public PantryFragment() {
        // Required empty public constructor
    }

    public static PantryFragment newInstance(FragmentActivity fragmentActivity) {
        PantryFragment fragment = new PantryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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

        adapter = new IngredientAdapter(getActivity(), new ArrayList<>(RecipeData.ingredients));

        binding.recyclerViewPantryItemsInPantry.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewPantryItemsInPantry.setLayoutManager(linearLayoutManager);

        binding.buttonPantryAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragmentAddIngredient addIngredientFragment = DialogFragmentAddIngredient.newInstance(getActivity(), adapter);
                addIngredientFragment.show(getActivity().getSupportFragmentManager(), "fragment_add_ingredient");
            }
        });

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
