package com.djonique.birdays;


import android.app.Activity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Ads {

    public static void showBanner(final Activity activity) {
        final AdView banner = ((AdView) activity.findViewById(R.id.banner));
        banner.loadAd(new AdRequest.Builder().build());

        banner.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                setupContentViewPadding(activity, banner.getHeight());
            }
        });
    }

    private static void setupContentViewPadding(Activity activity, int padding) {
        View view = activity.findViewById(R.id.container);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(),
                padding);
    }
}
