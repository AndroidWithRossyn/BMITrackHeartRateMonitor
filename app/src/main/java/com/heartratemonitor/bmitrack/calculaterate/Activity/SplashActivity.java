package com.heartratemonitor.bmitrack.calculaterate.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


import com.heartratemonitor.bmitrack.calculaterate.R;

public class SplashActivity extends AppCompatActivity {

    Activity splashActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashActivity = SplashActivity.this;
        nextActivity();
    }


    private void nextActivity() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), OnboardingActivity.class));
            finish();

        }, 1600);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}