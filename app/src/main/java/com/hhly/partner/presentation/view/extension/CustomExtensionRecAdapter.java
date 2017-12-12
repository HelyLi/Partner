package com.hhly.partner.presentation.view.extension;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义推广页精品推荐adapter
 * Created by dell on 2017/5/5.
 */

public class CustomExtensionRecAdapter extends BaseQuickAdapter<CustomExtensionRecAdapter.RecommendItem, BaseViewHolder> {

    public CustomExtensionRecAdapter() {
        super(R.layout.custom_extension_rec_item_layout, new ArrayList<RecommendItem>());
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendItem item) {

    }

    static class RecommendItem {

    }

    public void updateItems() {
        List<RecommendItem> items = new ArrayList<>();
        RecommendItem item = null;
        for (int i = 0; i < 3; i++) {
            item = new RecommendItem();
            items.add(item);
        }
        setNewData(items);
    }
}
