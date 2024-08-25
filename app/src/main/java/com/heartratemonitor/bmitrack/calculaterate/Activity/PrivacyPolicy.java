package com.heartratemonitor.bmitrack.calculaterate.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.heartratemonitor.bmitrack.calculaterate.R;

public class PrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

    }

    @Override
    public void onBackPressed() {
            PrivacyPolicy.super.onBackPressed();

    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}