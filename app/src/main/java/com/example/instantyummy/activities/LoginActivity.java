package com.example.instantyummy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.instantyummy.R;
import com.example.instantyummy.databinding.ActivityLoginBinding;
import com.example.instantyummy.models.YummyUser;
import com.example.instantyummy.util.ParseUtil;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private final static String TAG = "LoginActivity";
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Persist login
        YummyUser currentUser = (YummyUser) ParseUser.getCurrentUser();

        /*int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
            binding.imageViewLogo.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_taskify_logo_transparent_white));
        }
        else {
            binding.imageViewLogo.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_taskify_logo_transparent));
        }*/

        binding.buttonLogin.setOnClickListener(
                view -> ParseUser.logInInBackground(binding.editTextUsername.getText().toString(),
                        binding.editTextPassword.getText().toString(), (user, e) -> {
                            if (user != null) {
                                // The user is logged in.
                                goToMainActivity();
                            } else {
                                // Log in didn't succeed. Show returned error message to user.
                                Toast.makeText(this, ParseUtil.parseExceptionToErrorText(e), Toast.LENGTH_SHORT).show();
                                Log.e(TAG, ParseUtil.parseExceptionToErrorText(e), e);
                            }
                        }));

        binding.buttonSignup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
