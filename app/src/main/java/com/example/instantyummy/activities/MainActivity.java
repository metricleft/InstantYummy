package com.example.instantyummy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.instantyummy.R;
import com.example.instantyummy.databinding.ActivityMainBinding;
import com.example.instantyummy.models.YummyUser;
import com.example.instantyummy.util.RecipeData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    private int selectedItem;
    public RecipeData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        YummyUser user = (YummyUser) ParseUser.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        data = new RecipeData();

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerDisplayFragment);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
        BottomNavigationView bottomNavigationView = binding.bottomNavigationBar;
        setCurrentSelectedItem();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                if (selectedItem == R.id.featured) {
                    navController.navigate(R.id.action_featured_to_home);
                }
                else if (selectedItem == R.id.pantry) {
                    navController.navigate(R.id.action_pantry_to_home);
                }
                else if (selectedItem == R.id.recipes) {
                    navController.navigate(R.id.action_recipes_to_home);
                }
                else if (selectedItem == R.id.profile) {
                    navController.navigate(R.id.action_profile_to_home);
                }
                selectedItem = R.id.home;
            }
            else if (item.getItemId() == R.id.featured) {
                if (selectedItem == R.id.home) {
                    navController.navigate(R.id.action_home_to_featured);
                }
                else if (selectedItem == R.id.pantry) {
                    navController.navigate(R.id.action_pantry_to_featured);
                }
                else if (selectedItem == R.id.recipes) {
                    navController.navigate(R.id.action_recipes_to_featured);
                }
                else if (selectedItem == R.id.profile) {
                    navController.navigate(R.id.action_profile_to_featured);
                }
                selectedItem = R.id.featured;
            }
            else if (item.getItemId() == R.id.pantry) {
                if (selectedItem == R.id.home) {
                    navController.navigate(R.id.action_home_to_pantry);
                }
                else if (selectedItem == R.id.featured) {
                    navController.navigate(R.id.action_featured_to_pantry);
                }
                else if (selectedItem == R.id.recipes) {
                    navController.navigate(R.id.action_recipes_to_pantry);
                }
                else if (selectedItem == R.id.profile) {
                    navController.navigate(R.id.action_profile_to_pantry);
                }
                selectedItem = R.id.pantry;
            }
            else if (item.getItemId() == R.id.recipes) {
                if (selectedItem == R.id.home) {
                    navController.navigate(R.id.action_home_to_recipes);
                }
                else if (selectedItem == R.id.featured) {
                    navController.navigate(R.id.action_featured_to_recipes);
                }
                else if (selectedItem == R.id.pantry) {
                    navController.navigate(R.id.action_pantry_to_recipes);
                }
                else if (selectedItem == R.id.profile) {
                    navController.navigate(R.id.action_profile_to_recipes);
                }
                selectedItem = R.id.recipes;
            }
            else if (item.getItemId() == R.id.profile) {
                if (selectedItem == R.id.home) {
                    navController.navigate(R.id.action_home_to_profile);
                }
                else if (selectedItem == R.id.featured) {
                    navController.navigate(R.id.action_featured_to_profile);
                }
                else if (selectedItem == R.id.pantry) {
                    navController.navigate(R.id.action_pantry_to_profile);
                }
                else if (selectedItem == R.id.recipes) {
                    navController.navigate(R.id.action_recipes_to_profile);
                }
                selectedItem = R.id.profile;
            }
            return true;
        });
    }

    private void setCurrentSelectedItem() {
        if (navController.getCurrentDestination() == null) {
            selectedItem = R.id.home;
            binding.bottomNavigationBar.setSelectedItemId(R.id.home);
        }
        else {
            int curId = Objects.requireNonNull(navController.getCurrentDestination()).getId();
            selectedItem = curId;
            binding.bottomNavigationBar.setSelectedItemId(curId);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setCurrentSelectedItem();
    }
}