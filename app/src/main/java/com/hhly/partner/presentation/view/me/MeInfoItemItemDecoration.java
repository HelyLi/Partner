package com.hhly.partner.presentation.view.me;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.hhly.partner.presentation.utils.DisplayUtil;

/**
 * 个人ItemDecoration
 * Created by dell on 2017/4/17.
 */

public class MeInfoItemItemDecoration extends RecyclerView.ItemDecoration {
    private int mGroupDivideHeight;
    private Drawable mGroupDivide;
    private int mItemDivideHeight;
    private Drawable mItemDivide;

    public MeInfoItemItemDecoration(Context context) {
        mGroupDivideHeight = DisplayUtil.dip2px(context, 12);
        mGroupDivide = new ColorDrawable(Color.parseColor("#F5F5F5"));
        mItemDivideHeight = DisplayUtil.dip2px(context, 1);
        mItemDivide = new ColorDrawable(Color.parseColor("#E9E9E9"));
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        MeAdapter adapter = (MeAdapter) parent.getAdapter();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if (adapter.isFirstItemOfGroup(parent.getChildAdapterPosition(child))) {
                int top = child.getTop() - params.topMargin - mGroupDivideHeight;
                int bottom = child.getTop();
                mGroupDivide.setBounds(left, top, right, bottom);
                mGroupDivide.draw(c);
            } else {
                int top = child.getTop() - params.topMargin - mItemDivideHeight;
                int bottom = child.getTop();
                mItemDivide.setBounds(left, top, right, bottom);
                mItemDivide.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        MeAdapter adapter = (MeAdapter) parent.getAdapter();
        boolean isFirstItem = adapter.isFirstItemOfGroup(position);
        if (isFirstItem) {
            outRect.set(0, mGroupDivideHeight, 0, 0);
        } else {
            outRect.set(0, mItemDivideHeight, 0, 0);
        }
    }
}
