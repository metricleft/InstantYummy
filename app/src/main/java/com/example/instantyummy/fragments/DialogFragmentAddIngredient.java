package com.example.instantyummy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.instantyummy.R;
import com.example.instantyummy.adapters.IngredientAdapter;
import com.example.instantyummy.databinding.FragmentAddIngredientsBinding;
import com.example.instantyummy.util.RecipeData;

import java.util.Objects;

public class DialogFragmentAddIngredient extends DialogFragment {

    private FragmentAddIngredientsBinding binding;
    private FragmentActivity fragmentActivity;
    private IngredientAdapter adapter;

    public DialogFragmentAddIngredient() {
        // Required empty public constructor
    }

    public static DialogFragmentAddIngredient newInstance(FragmentActivity fragmentActivity, IngredientAdapter adapter) {
        DialogFragmentAddIngredient fragment = new DialogFragmentAddIngredient();
        Bundle args = new Bundle();
        fragment.setActivity(fragmentActivity);
        fragment.setAdapter(adapter);
        fragment.setArguments(args);
        return fragment;
    }

    private void setActivity(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    private void setAdapter(IngredientAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddIngredientsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredient = binding.editTextIngredient.getText().toString();
                if (ingredient.isEmpty()) {
                    Toast.makeText(fragmentActivity, "You must enter an ingredient.", Toast.LENGTH_SHORT).show();
                    return;
                }
                RecipeData.ingredients.add(ingredient);
                adapter.notifyDataSetChanged();
                Log.i("DialogFragmentAddIngredient", RecipeData.ingredients.toString());
                dismiss();
            }
        });
    }
}