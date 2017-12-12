package com.hhly.partner.presentation.view.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.presentation.utils.DisplayUtil;


/**
 * Created by freeman on 16/4/14.
 */
public class RecyclerViewGridDivide extends RecyclerView.ItemDecoration {

    private static final String TAG = "RecyclerViewGridDivide";

    private final Drawable mDrawable;
    private final int spacing;

    public RecyclerViewGridDivide(Context context, @ColorRes int color) {
        this(context, color, 0.5f);
    }

    public RecyclerViewGridDivide(Context context, @ColorRes int color, float space) {
        mDrawable = new ColorDrawable(context.getResources().getColor(color));
        spacing = DisplayUtil.dip2px(context, space);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = layoutManager.getSpanCount();
        int childCount = parent.getChildCount();
        if (parent.getAdapter() instanceof BaseQuickAdapter) {
            if (((BaseQuickAdapter) parent.getAdapter()).isLoadMoreEnable() && ((BaseQuickAdapter) parent.getAdapter()).isLoading()) {
                childCount--;
            }
        }

        View child = null;
        int row = (int) Math.ceil(childCount * 1.0f / spanCount);
        //draw row
        for (int i = 0; i < row - 1; ++i) {
            int left = parent.getLeft() + parent.getPaddingLeft();
            int right = parent.getRight() - parent.getPaddingRight();
            int top = parent.getChildAt(i * spanCount).getBottom();
            int bottom = top + spacing;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
        //draw column

        /*for (int i = 0; i < spanCount; ++i) {
            for (int j = 0; j < row; j++) {
                child = parent.getChildAt(spanCount * j + i);
                if (child != null) {
                    int left = child.getRight();
                    int right = left + ((i == 0 || i == (spanCount - 1)) ? spacing : spacing / 2);
                    int top = child.getTop()*//* + child.getPaddingTop()*//*;
                    int bottom = child.getBottom()*//* - child.getPaddingBottom()*//*;
                    mDrawable.setBounds(left, top, right, bottom);
                    mDrawable.draw(c);
                }
            }
        }*/
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = layoutManager.getSpanCount();
        int position = parent.getChildAdapterPosition(view); // item position
        boolean hadLoadMore = false;
        if (parent.getAdapter() instanceof BaseQuickAdapter) {
            if (((BaseQuickAdapter) parent.getAdapter()).isLoadMoreEnable()) {
                hadLoadMore = true;
            }
        }
        int column = position % spanCount; // item column
        int half = spanCount / 2;

        //spanCount = 4 1

        outRect.left = (column == 0 ? spanCount : half) * spacing / spanCount; // column * ((1f / spanCount) * spacing)
        outRect.right = ((column == (spanCount - 1)) ? spanCount : half) * spacing / spanCount;
//        outRect.right = spacing - ((column + 1) == spanCount ? 0 : (column + 1)) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
//        outRect.left = (column == 0 ? 43 : 21);
//        outRect.right = (column == (spanCount - 1)) ? 43 : 21;
        /*if (position >= spanCount) {
        }*/
        if ((!hadLoadMore && parent.getAdapter().getItemCount() - position <= spanCount) || (hadLoadMore && parent.getAdapter().getItemCount() - position < spanCount - 1)) {
            //last row
            outRect.bottom = spacing;
        }
        outRect.top = spacing; // item top

    }
}
