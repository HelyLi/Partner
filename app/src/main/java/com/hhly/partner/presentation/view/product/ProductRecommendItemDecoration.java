package com.hhly.partner.presentation.view.product;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hhly.partner.presentation.utils.DisplayUtil;

import static com.hhly.partner.presentation.view.product.ProductRecommendAdapter.RECOMMEND_TYPE_GRID_DISPLAY;

/**
 * 产品推荐中grid类型的ItemDecoration
 * Created by dell on 2017/5/10.
 */

public class ProductRecommendItemDecoration extends RecyclerView.ItemDecoration {
    private int itemCount;

    public ProductRecommendItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int itemType = parent.getAdapter().getItemViewType(position);

        if (itemType == RECOMMEND_TYPE_GRID_DISPLAY) {
            ++itemCount;
            if (itemCount % 2 == 0) {

                outRect.set(DisplayUtil.dip2px(view.getContext(), 7.5f),
                        DisplayUtil.dip2px(view.getContext(), 15),
                        DisplayUtil.dip2px(view.getContext(), 15),
                        0);
            } else {
                outRect.set(DisplayUtil.dip2px(view.getContext(), 15),
                        DisplayUtil.dip2px(view.getContext(), 15),
                        DisplayUtil.dip2px(view.getContext(), 7.5f),
                        0);
            }
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
