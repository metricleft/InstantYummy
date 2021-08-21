package com.example.instantyummy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.instantyummy.R;
import com.example.instantyummy.databinding.ActivitySignupBinding;
import com.example.instantyummy.models.YummyUser;
import com.example.instantyummy.util.ParseUtil;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            binding.imageViewLogo.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_taskify_logo_transparent_white));
        }
        else {
            binding.imageViewLogo.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_taskify_logo_transparent));
        }*/

        binding.buttonLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        binding.buttonSignup.setOnClickListener(v -> {
            YummyUser user = new YummyUser();
            // Set core properties
            if (binding.editTextName.getText().toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.error_empty_name_text), Toast.LENGTH_SHORT).show();
                return;
            }
            user.setName(binding.editTextName.getText().toString());
            user.setUsername(binding.editTextUsername.getText().toString());
            user.setPassword(binding.editTextPassword.getText().toString());

            // Invoke signUpInBackground
            user.signUpInBackground(e -> {
                if (e == null) {
                    goToMainActivity();
                } else {
                    // Sign up didn't succeed. Returns error message to user.
                    Toast.makeText(this, ParseUtil.parseExceptionToErrorText(e), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, ParseUtil.parseExceptionToErrorText(e), e);
                }
            });
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
