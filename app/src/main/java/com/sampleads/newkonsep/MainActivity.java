package com.sampleads.newkonsep;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.adsmedia.adsmodul.AdsHelper;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AdsHelper.initializeAds(this,BuildConfig.APPLICATION_ID,"8025677");
        if (BuildConfig.DEBUG){
            AdsHelper.debugMode(true);
        }
        AdsHelper.loadInterstitial(this,
                "980088188");
        AdsHelper.showBanner(this, findViewById(R.id.layAds),
                "980099802");
        this.findViewById(R.id.tbShow).setOnClickListener(v -> {
            AdsHelper.showInterstitial(MainActivity.this,
                    "980088188",0);
        });

    }
}