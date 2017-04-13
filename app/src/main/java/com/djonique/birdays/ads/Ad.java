package com.djonique.birdays.ads;

import android.os.Build;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Ad {

    public static void showBanner(final View view, final AdView banner) {
        banner.loadAd(new AdRequest.Builder().build());

        banner.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setupContentViewPadding(view, banner.getHeight());
                } else {
                    setupContentViewPadding(view, banner.getHeight() - 34);
                }
            }
        });
    }

    private static void setupContentViewPadding(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(),
                padding);
    }
}
