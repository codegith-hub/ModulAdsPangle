package com.adsmedia.adsmodul;

import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.openadsdk.api.init.PAGConfig;
import com.bytedance.sdk.openadsdk.api.init.PAGSdk;

/**
 * You can use a singleton to save the TTFAdManager instance, and call it when you need to initialize sdk
 */
public class AdManagerHolder {
    public static MyApplication application;
    public static boolean sInit;

    private static final String TAG = "AdManagerHolder";

    public AdManagerHolder(MyApplication myApplication) {
        application = myApplication;
    }

    private static PangleSdkHasInitSuccess pangleSdkHasInitSuccess;

    //step1: Initialize sdk
    public static void doInitNewApi(String appid) {
        try {
            if (!sInit) {
                PAGConfig pAGInitConfig = new PAGConfig.Builder()
                        .appId(appid)
                        .debugLog(true)
                        .supportMultiProcess(false)
                        .build();;
                PAGSdk.init(application, pAGInitConfig, new PAGSdk.PAGInitCallback() {
                    @Override
                    public void success() {
                        sInit = true;
                        Log.i(TAG, "PAGInitCallback new api init success: ");
                        if (pangleSdkHasInitSuccess != null){
                            pangleSdkHasInitSuccess.initSuccess();
                            pangleSdkHasInitSuccess = null;
                        }
                    }

                    @Override
                    public void fail(int code, String msg) {
                        Log.i(TAG, "PAGInitCallback new api init fail: " + code);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public interface PangleSdkHasInitSuccess {
        void initSuccess();
    }

    public static void setPangleSdkHasInitSuccess(PangleSdkHasInitSuccess sdkHasInitSuccess) {
        pangleSdkHasInitSuccess = sdkHasInitSuccess;
    }
}
