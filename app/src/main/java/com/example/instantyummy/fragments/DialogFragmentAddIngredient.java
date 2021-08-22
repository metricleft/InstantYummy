package com.example.instantyummy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instantyummy.R;
import com.example.instantyummy.databinding.FragmentAddIngredientsBinding;

public class DialogFragmentAddIngredient extends DialogFragment {

    private FragmentAddIngredientsBinding binding;

    public DialogFragmentAddIngredient() {
        // Required empty public constructor
    }

    public static DialogFragmentAddIngredient newInstance() {
        DialogFragmentAddIngredient fragment = new DialogFragmentAddIngredient();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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

    //TODO
    }
}