package com.hhly.partner.presentation.view.product.search;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataByNameResp;

import java.util.ArrayList;

/**
 * 产品搜索结果adapter
 * Created by dell on 2017/4/26.
 */

public class ProductSearchResultAdapter extends BaseQuickAdapter<GameDataByNameResp.DataBeanX.DataBean,BaseViewHolder>{
    public ProductSearchResultAdapter() {
        super(R.layout.product_search_result_item_layout, new ArrayList<GameDataByNameResp.DataBeanX.DataBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, GameDataByNameResp.DataBeanX.DataBean item) {
        helper.setText(R.id.game_name_tv,item.getNAME());
    }
}
