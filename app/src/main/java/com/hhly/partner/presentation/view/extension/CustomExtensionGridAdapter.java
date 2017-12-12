package com.hhly.partner.presentation.view.extension;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;

import java.util.ArrayList;

/**
 * 自定义推广页gridAdapter
 * Created by dell on 2017/5/5.
 */

public class CustomExtensionGridAdapter extends BaseQuickAdapter<ExtensionDefaultGridItem,BaseViewHolder>{

    public CustomExtensionGridAdapter() {
        super(R.layout.custom_extension_grid_item_layout,new ArrayList<ExtensionDefaultGridItem>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ExtensionDefaultGridItem item) {
        ((ImageView)helper.getView(R.id.section_img)).setImageResource(item.getDrawableRes());
    }
}
