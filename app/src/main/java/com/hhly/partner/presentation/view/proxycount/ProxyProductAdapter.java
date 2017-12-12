package com.hhly.partner.presentation.view.proxycount;

import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.proxy.AppAgentProductRechargeTopNineResp;
import com.hhly.partner.presentation.view.home.HomeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/15
 */

public class ProxyProductAdapter extends BaseQuickAdapter<AppAgentProductRechargeTopNineResp.DataBean, BaseViewHolder> {


    public ProxyProductAdapter(List<AppAgentProductRechargeTopNineResp.DataBean> data) {
        super(R.layout.proxy_product_recycle_view_item, data != null ? data : new ArrayList<AppAgentProductRechargeTopNineResp.DataBean>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AppAgentProductRechargeTopNineResp.DataBean item) {
        baseViewHolder.setText(R.id.product_recycler_view_item, item.getNAME());
        TextView tv = (TextView) baseViewHolder.itemView.findViewById(R.id.product_recycler_view_item);
        tv.setSelected(item.isChecked());


    }
}
