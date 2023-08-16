package com.heartratemonitor.bmitrack.calculaterate.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.FirebaseADHandlers.AdUtils;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.Interfaces.AppInterfaces;
import com.heartratemonitor.bmitrack.calculaterate.R;

public class PrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        AdUtils.showNativeAd(PrivacyPolicy.this, findViewById(R.id.native_ads), false);
    }

    @Override
    public void onBackPressed() {
        AdUtils.showInterstitialAd(PrivacyPolicy.this, state_load -> {

            PrivacyPolicy.super.onBackPressed();
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        AdUtils.loadInitialInterstitialAds(PrivacyPolicy.this);
        AdUtils.loadAppOpenAds(PrivacyPolicy.this);
        AdUtils.loadInitialNativeList(this);
    }
}