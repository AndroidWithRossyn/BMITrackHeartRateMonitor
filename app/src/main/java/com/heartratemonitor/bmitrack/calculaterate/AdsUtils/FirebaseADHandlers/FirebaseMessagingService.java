package com.heartratemonitor.bmitrack.calculaterate.AdsUtils.FirebaseADHandlers;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.RemoteMessage;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.PreferencesManager.AppPreferences;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.Utils.Constants;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    public FirebaseMessagingService() {
        super();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (!remoteMessage.getData().isEmpty()) {
            if (remoteMessage.getData().containsValue("10")) {
                FirebaseUtils.initiateAndStoreFirebaseRemoteConfig(getApplicationContext(), adsJsonPOJO -> {
                    AppPreferences appPreferencesManger = new AppPreferences(getApplicationContext());
                    appPreferencesManger.setAdsModel(adsJsonPOJO);
                    Constants.adsJsonPOJO = adsJsonPOJO;
                });
            }
        }

    }
}
