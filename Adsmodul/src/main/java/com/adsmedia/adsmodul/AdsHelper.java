package com.adsmedia.adsmodul;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import static com.adsmedia.adsmodul.MyApplication.initSdk;

import android.app.Activity;

import android.widget.RelativeLayout;



import com.adsmedia.mastermodul.MasterAdsHelper;
import com.bytedance.sdk.openadsdk.api.banner.PAGBannerAd;
import com.bytedance.sdk.openadsdk.api.banner.PAGBannerAdLoadListener;
import com.bytedance.sdk.openadsdk.api.banner.PAGBannerRequest;
import com.bytedance.sdk.openadsdk.api.banner.PAGBannerSize;
import com.bytedance.sdk.openadsdk.api.interstitial.PAGInterstitialAd;
import com.bytedance.sdk.openadsdk.api.interstitial.PAGInterstitialAdLoadListener;
import com.bytedance.sdk.openadsdk.api.interstitial.PAGInterstitialRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class AdsHelper {
    public static boolean openads = false;
    public static boolean debugMode;
    public static boolean directData = false;
    public static void gdpr(Activity activity, Boolean childDirected, String keypos) {
    }

    public static void initializeAds(Activity activity, int pos) {
    }
    public static void initializeAds(Activity activity, String pos, String gameId, boolean test) {
        try {
            initSdk(gameId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MasterAdsHelper.initializeAds(activity, pos);
    }

    public static void initializeAds(Activity activity, String pos, String gameId) {
        try {
            initSdk(gameId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MasterAdsHelper.initializeAds(activity, pos);
    }
    public static void debugMode(Boolean debug) {
        debugMode = debug;
        MasterAdsHelper.debugMode(debug);
    }
    public static PAGBannerAd bannerAdPangle;
    public static void showBanner(Activity activity, RelativeLayout layout, String metaId) {
        PAGBannerSize bannerSize = PAGBannerSize.BANNER_W_320_H_50;
        PAGBannerRequest bannerRequest = new PAGBannerRequest(bannerSize);
        directData = true;
        PAGBannerAd.loadAd(metaId, bannerRequest, new PAGBannerAdLoadListener() {
            @Override
            public void onError(int code, String message) {
                MasterAdsHelper.showBanner(activity,layout);
            }

            @Override
            public void onAdLoaded(PAGBannerAd bannerAd) {
                if (bannerAd == null) {
                    return;
                }
                bannerAdPangle = bannerAd;
                if (bannerAdPangle != null) {
                    layout.addView(bannerAdPangle.getBannerView());
                }

            }
        });
    }
    public static PAGInterstitialAd interstitialAdPangle;
    public static void loadInterstitial(Activity activity, String admobId) {
        directData = true;
        try {
            PAGInterstitialRequest request = new PAGInterstitialRequest();
            PAGInterstitialAd.loadAd(admobId,
                    request,
                    new PAGInterstitialAdLoadListener() {
                        @Override
                        public void onError(int code, String message) {
                        }

                        @Override
                        public void onAdLoaded(PAGInterstitialAd pagInterstitialAd) {
                            interstitialAdPangle = pagInterstitialAd;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int count = 0;

    public static void showInterstitial(Activity activity, String admobId, int interval) {
        if (count >= interval) {
            if (interstitialAdPangle != null) {
                interstitialAdPangle.show(activity);
                interstitialAdPangle = null;

            } else {
                MasterAdsHelper.showInterstitial(activity);
            }
            count = 0;
        } else {
            count++;
        }
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            //Logger.logStackTrace(TAG,e);
        }
        return "";
    }
}
