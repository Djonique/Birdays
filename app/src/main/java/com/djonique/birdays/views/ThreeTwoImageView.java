package com.djonique.birdays.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


public class ThreeTwoImageView extends android.support.v7.widget.AppCompatImageView {

    public ThreeTwoImageView(Context context) {
        super(context);
    }

    public ThreeTwoImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ThreeTwoImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int threeTwoHeight = MeasureSpec.getSize(widthMeasureSpec) * 2 / 3;
        int threeTwoHeightSpec = MeasureSpec.makeMeasureSpec(threeTwoHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, threeTwoHeightSpec);
    }
}
