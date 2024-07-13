package com.adsmedia.adsmodul;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.openadsdk.api.init.PAGSdk;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static OpenAds openAds;
    private static AdManagerHolder adManagerHolder;
    @Override
    public void onCreate() {
        super.onCreate();
        openAds = new OpenAds(this);
        adManagerHolder = new AdManagerHolder(this);
        try {
            PAGSdk.addPAGInitCallback(new PAGSdk.PAGInitCallback() {
                @Override
                public void success() {
                    Log.i(TAG, "PAGInitCallback success: addPAGInitCallback");
                }

                @Override
                public void fail(int code, String msg) {
                    Log.i(TAG, "PAGInitCallback fail: addPAGInitCallback");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void initSdk(String appid){
        AdManagerHolder.doInitNewApi(appid);
    }
}