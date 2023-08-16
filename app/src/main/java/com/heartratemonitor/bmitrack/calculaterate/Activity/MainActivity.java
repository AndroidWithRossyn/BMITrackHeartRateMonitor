package com.heartratemonitor.bmitrack.calculaterate.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.FirebaseADHandlers.AdUtils;
import com.heartratemonitor.bmitrack.calculaterate.Fragments.BmiCalFragment;
import com.heartratemonitor.bmitrack.calculaterate.Fragments.HeartRateFragment;
import com.heartratemonitor.bmitrack.calculaterate.Fragments.HistoryFragment;
import com.heartratemonitor.bmitrack.calculaterate.Fragments.TrackerFragment;
import com.heartratemonitor.bmitrack.calculaterate.R;

public class MainActivity extends AppCompatActivity {

    String fragmentName;
    ChipNavigationBar chipNavigationBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentName = getIntent().getStringExtra("fragmentName");
        chipNavigationBar = findViewById(R.id.bottom_nav_bar);

        textView = findViewById(R.id.title);
        ImageView back = findViewById(R.id.backbt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        switch (fragmentName) {
            case "heartRate":
                textView.setText("Heart Rate ");
                chipNavigationBar.setItemSelected(R.id.nav_heart_rate, true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HeartRateFragment()).commit();
                break;
            case "bmiCal":
                textView.setText("BMI Calculator");
                chipNavigationBar.setItemSelected(R.id.nav_bmi_cal, true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BmiCalFragment()).commit();
                break;
            case "tracker":
                textView.setText("Health Tracker");
                chipNavigationBar.setItemSelected(R.id.nav_health, true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrackerFragment()).commit();
                break;
            case "history":
                textView.setText("History");
                chipNavigationBar.setItemSelected(R.id.nav_history, true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                break;

        }

        bottomMenu();

    }

    @Override
    public void onBackPressed() {
        AdUtils.showInterstitialAd(MainActivity.this,state_load -> {
            MainActivity.super.onBackPressed();
        });
    }


    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener
                (new ChipNavigationBar.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int i) {
                        Fragment fragment = null;
                        switch (i) {
                            case R.id.nav_heart_rate:
                                fragment = new HeartRateFragment();
                                textView.setText("Heart Rate ");
                                break;
                            case R.id.nav_bmi_cal:
                                textView.setText("BMI Calculator");
                                fragment = new BmiCalFragment();
                                break;
                            case R.id.nav_health:
                                textView.setText("Health Tracker");
                                fragment = new TrackerFragment();
                                break;
                            case R.id.nav_history:
                                textView.setText("History");
                                fragment = new HistoryFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,
                                        fragment).commit();
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();

        AdUtils.loadInitialInterstitialAds(MainActivity.this);
        AdUtils.loadAppOpenAds(MainActivity.this);
        AdUtils.loadInitialNativeList(this);
    }

}