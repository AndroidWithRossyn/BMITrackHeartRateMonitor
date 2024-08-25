package com.heartratemonitor.bmitrack.calculaterate.Activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.heartratemonitor.bmitrack.calculaterate.R;

public class TermsAndUse extends AppCompatActivity {

    TextView agreebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_use);
        agreebtn = findViewById(R.id.agreebtn);



        agreebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

            }
        });

    }

    public void pp(View view) {
        gotoUrl("https://streamicmediainc.blogspot.com/p/privacy-policy.html");
//        startActivity(new Intent(getApplicationContext(), PrivacyPolicy.class));
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onBackPressed() {
            TermsAndUse.super.onBackPressed();

    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}