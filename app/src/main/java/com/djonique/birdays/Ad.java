package com.djonique.birdays;


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
                setupContentViewPadding(view, banner.getHeight());
            }
        });
    }

    private static void setupContentViewPadding(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(),
                padding);
    }
}
