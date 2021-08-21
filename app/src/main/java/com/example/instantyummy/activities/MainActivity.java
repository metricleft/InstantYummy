package com.example.instantyummy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.instantyummy.R;
import com.example.instantyummy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}
