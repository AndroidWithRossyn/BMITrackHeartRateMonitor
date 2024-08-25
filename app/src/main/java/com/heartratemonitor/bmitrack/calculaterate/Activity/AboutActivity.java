package com.heartratemonitor.bmitrack.calculaterate.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.heartratemonitor.bmitrack.calculaterate.R;

public class AboutActivity extends AppCompatActivity {

    TextView appVersion;
    ImageView backbt;
    LinearLayout native_ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        appVersion = findViewById(R.id.app_version);
        backbt = findViewById(R.id.backbt);
        native_ads=findViewById(R.id.native_ads);



        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            appVersion.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {

            AboutActivity.super.onBackPressed();

    }
}