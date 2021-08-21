package com.example.instantyummy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.instantyummy.R;
import com.example.instantyummy.databinding.ActivityRecipeDetailsBinding;

public class RecipeDetailsActivity extends AppCompatActivity {

    private ActivityRecipeDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}