package com.heartratemonitor.bmitrack.calculaterate.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.FirebaseADHandlers.AdUtils;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.FirebaseADHandlers.FirebaseUtils;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.PreferencesManager.AppPreferences;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.Utils.Constants;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.Utils.Global;
import com.heartratemonitor.bmitrack.calculaterate.R;

public class SplashActivity extends AppCompatActivity {

    Activity splashActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashActivity = SplashActivity.this;
        AppPreferences appPreferences = new AppPreferences(this);
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.ADSJSON);
        Constants.adsJsonPOJO = Global.getAdsData(appPreferences.getAdsModel());

        if (Constants.adsJsonPOJO != null && !Constants.isNull(Constants.adsJsonPOJO.getParameters().getApp_open_ad().getDefaultValue().getValue())) {
            Constants.adsJsonPOJO = Global.getAdsData(appPreferences.getAdsModel());
            Constants.hitCounter = Integer.parseInt(Constants.adsJsonPOJO.getParameters().getApp_open_ad().getDefaultValue().getHits());
//            Constants.adsJsonPOJO.getParameters().getShowAd().getDefaultValue().setValue("false");

            nextActivity();
        } else {
            FirebaseUtils.initiateAndStoreFirebaseRemoteConfig(this, adsJsonPOJO -> {
                appPreferences.setAdsModel(adsJsonPOJO);
                Constants.adsJsonPOJO = adsJsonPOJO;
                Constants.hitCounter = Integer.parseInt(Constants.adsJsonPOJO.getParameters().getApp_open_ad().getDefaultValue().getHits());
//                Constants.adsJsonPOJO.getParameters().getShowAd().getDefaultValue().setValue("false");

                nextActivity();

            });
        }


    }


    private void nextActivity() {
        AdUtils.showAdIfAvailable(this, state_load -> {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(getApplicationContext(), OnboardingActivity.class));
                finish();

            }, 1600);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        AdUtils.loadInitialInterstitialAds(this);
        AdUtils.loadAppOpenAds(this);
        AdUtils.loadInitialNativeList(this);

    }

}