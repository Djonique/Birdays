/*
 * Copyright 2017 Evgeny Timofeev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eblis.whenwasit.ad;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class Ad {

    /**
     * Loads AdMob banner into MainActivity
     */
    public static void showBannerAd(final Activity activity, final ViewGroup viewGroup, final AdView banner, final View view) {
        banner.loadAd(new AdRequest.Builder().build());

        banner.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Toast.makeText(activity, "Banner AD failed to load: " + loadAdError.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                setupContentViewPadding(viewGroup, banner.getHeight());
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    setBottomMargin(view, -(banner.getHeight() / 4));
                }
            }
        });
    }

    /**
     * Set up bottom padding of view under AdMob banner
     */
    private static void setupContentViewPadding(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(),
                padding);
    }

    private static void setBottomMargin(View view, int margin) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.bottomMargin = margin;
            view.requestLayout();
        }
    }
}