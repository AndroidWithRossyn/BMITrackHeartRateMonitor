package com.heartratemonitor.bmitrack.calculaterate.Activity;

import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.heartratemonitor.bmitrack.calculaterate.AdsUtils.FirebaseADHandlers.AdUtils;
import com.heartratemonitor.bmitrack.calculaterate.BuildConfig;
import com.heartratemonitor.bmitrack.calculaterate.R;

public class DashboardActivity extends AppCompatActivity {

    ImageView appLogo, menu, close_drawer, heartRate, bmiCal, tracker, history;
    DrawerLayout drawerLayout;

    private final int REQUEST_CODE_STORAGE_PERMISSION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        appLogo = findViewById(R.id.app_logo);
        menu = findViewById(R.id.toggle_drawer);
        drawerLayout = findViewById(R.id.drawerLayout);
        close_drawer = findViewById(R.id.close_drawer);
        heartRate = findViewById(R.id.heartRate);
        bmiCal = findViewById(R.id.bmiCal);
        tracker = findViewById(R.id.tracker);
        history = findViewById(R.id.history);
        AdUtils.showNativeAd(DashboardActivity.this, findViewById(R.id.native_ad), false);
        AdUtils.showNativeAd(DashboardActivity.this, findViewById(R.id.native_ads), true);

        mCheckPermission();

//        Glide.with(this).load(R.drawable.logo).into(appLogo);
        appLogo.setImageResource(R.drawable.logotwo);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        close_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(DashboardActivity.this, state_load -> {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("fragmentName", "history"));
                });
            }
        });

        tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(DashboardActivity.this, state_load -> {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("fragmentName", "tracker"));
                });
            }
        });

        bmiCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(DashboardActivity.this,state_load -> {

                        startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("fragmentName", "bmiCal"));
                });
            }
        });

        heartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(DashboardActivity.this, state_load -> {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("fragmentName", "heartRate"));
                });
            }
        });

    }

    public boolean isLatestVersion() {
        return SDK_INT >= Build.VERSION_CODES.R;
    }

    private void mCheckPermission() {

        if (isLatestVersion()) {
            if (/*ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || */ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
                permissionDialog();
            } else {
                // Permission already granted, you can proceed with reading and writing external storage
                System.out.println("Permission Already Granted");
            }
        } else {

            if (/*ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||*/ ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
                permissionDialog();
            } else {
                // Permission already granted, you can proceed with reading and writing external storage
                System.out.println("Permission Already Granted");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can proceed with reading and writing external storage
//                OpenImageChooser();
                System.out.println("Permission Already Granted");
            } else {
                // Permission denied, you cannot proceed with reading and writing external storage
//                Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT).show();
//                DetailsDialog.showDetailsDialog(EditProfileActivity.this);
            }
        }
    }


    @Override
    public void onBackPressed() {
        exitDialog();
    }

    private void exitDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DashboardActivity.this);
        builder.setTitle(getResources().getString(R.string.app_name));
//        builder.setMessage(getResources().getString(R.string.if_you_like_this_app_than_don_t_forget_to_rate_this_app_it_won_t_take_more_than_minutes));
        builder.setMessage("Are you sure to exit?");

//        builder.setIcon(getResources().getDrawable(R.drawable.logo));

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                dialog.dismiss();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setNeutralButton("Rate App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rateapp();
                dialog.dismiss();
            }
        });

        /*builder.setNegativeButtonIcon(getResources().getDrawable(R.drawable.btn_share));
        builder.setPositiveButtonIcon(getResources().getDrawable(R.drawable.btn_rate));
        builder.setNeutralButtonIcon(getResources().getDrawable(R.drawable.btn_privacy));*/

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
        alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
        alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
        alertDialog.getButton(android.app.AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.black));


    }

    public void rateapp() {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        intent.addFlags(flags);
        return intent;
    }

    public void askPermissions() {

        if (isLatestVersion()) {
            if (/*ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || */ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{/*android.Manifest.permission.READ_EXTERNAL_STORAGE,*/ Manifest.permission.CAMERA}, REQUEST_CODE_STORAGE_PERMISSION);
//                permissionDialog();
            } else {
                // Permission already granted, you can proceed with reading and writing external storage
                System.out.println("Permission Already Granted");
            }
        } else {

            if (/*ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || */ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{/*android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, */Manifest.permission.CAMERA}, REQUEST_CODE_STORAGE_PERMISSION);
//                permissionDialog();
            } else {
                // Permission already granted, you can proceed with reading and writing external storage
                System.out.println("Permission Already Granted");
            }
        }
    }

    private void permissionDialog() {
        Dialog dialogOnBackPressed = new Dialog(DashboardActivity.this);
        dialogOnBackPressed.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogOnBackPressed.setCancelable(true);
        dialogOnBackPressed.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialogOnBackPressed.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        dialogOnBackPressed.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialogOnBackPressed.setContentView(R.layout.dialog_permission);
        ImageView textViewDeny, textViewAllow;
        textViewDeny = dialogOnBackPressed.findViewById(R.id.textViewDeny);
        textViewAllow = dialogOnBackPressed.findViewById(R.id.textViewAllow);

        textViewDeny.setOnClickListener(view -> {
            mCheckPermission();
            dialogOnBackPressed.dismiss();
        });

        textViewAllow.setOnClickListener(view -> {
            askPermissions();
            dialogOnBackPressed.dismiss();
        });

        dialogOnBackPressed.show();
    }

    public void shareApp(View view) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String shareMessage = "\nUnlock a healthier, happier you with our app. Calculate and evaluate your BMI effortlessly. Download now and begin your journey to a better you.\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    public void rateApp(View view) {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    public void tAndU(View view) {
        AdUtils.showInterstitialAd(DashboardActivity.this, state_load -> {
            startActivity(new Intent(getApplicationContext(), TermsAndUse.class));
        });
    }

    public void aboutUS(View view) {
        AdUtils.showInterstitialAd(DashboardActivity.this, state_load -> {
            startActivity(new Intent(getApplicationContext(), AboutActivity.class));
        });
    }

    public void history(View view) {
        AdUtils.showInterstitialAd(DashboardActivity.this, state_load -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("fragmentName", "history"));
        });
    }

    public void pp(View view) {
        gotoUrl("https://streamicmediainc.blogspot.com/p/privacy-policy.html");
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
    @Override
    protected void onResume() {
        super.onResume();

        AdUtils.loadInitialInterstitialAds(DashboardActivity.this);
        AdUtils.loadAppOpenAds(DashboardActivity.this);
        AdUtils.loadInitialNativeList(this);
    }
}