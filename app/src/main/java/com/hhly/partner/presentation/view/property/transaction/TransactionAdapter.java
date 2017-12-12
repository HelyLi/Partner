package com.hhly.partner.presentation.view.property.transaction;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.WithdrawalInfoResp;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/25
 */

public class TransactionAdapter extends BaseQuickAdapter<WithdrawalInfoResp.DataBeanX.DataBean, BaseViewHolder> {


    public TransactionAdapter(List<WithdrawalInfoResp.DataBeanX.DataBean> data) {
        super(R.layout.transaction_recycle_view_item, data != null ? data : new ArrayList<WithdrawalInfoResp.DataBeanX.DataBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, WithdrawalInfoResp.DataBeanX.DataBean item) {
        helper.setText(R.id.transaction_time, item.getAPPLICATION_TIME())
                .setText(R.id.transaction_money, String.format("%.2f", item.getWITHDRAWAL_PRICE()));
    }
}
