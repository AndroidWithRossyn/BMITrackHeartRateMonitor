package com.heartratemonitor.bmitrack.calculaterate.AdsUtils.FirebaseADHandlers;


import static com.heartratemonitor.bmitrack.calculaterate.AdsUtils.Utils.Constants.isNull;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.PreferencesManager.AppPreferences;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.Utils.Constants;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.Utils.Global;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        AppPreferences appPreferencesManger = new AppPreferences(this);
        Constants.adsJsonPOJO = Global.getAdsData(appPreferencesManger.getAdsModel());

        MobileAds.initialize(this, initializationStatus -> {});


    }

}