package com.hhly.partner.presentation.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.hhly.partner.R;


/**
 * Created by Simon on 16/9/6.
 */

public class RatioFrameLayout extends FrameLayout {

    protected float widthWeight;

    public RatioFrameLayout(Context context) {
        super(context);
    }

    public RatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightToMeasure = (int) (width * widthWeight + 0.5f);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightToMeasure, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray a = context.obtainStyledAttributes(attributeSet,
                    R.styleable.RatioLayout);
            this.widthWeight = a.getFloat(R.styleable.RatioLayout_ratio_width_percent, 1);
            a.recycle();
        }
    }

}
