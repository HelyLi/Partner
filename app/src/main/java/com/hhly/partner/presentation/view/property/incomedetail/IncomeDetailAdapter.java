package com.hhly.partner.presentation.view.property.incomedetail;

import android.content.Context;
import android.text.Html;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.AccPriceResp;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/25
 */

public class IncomeDetailAdapter extends BaseQuickAdapter<AccPriceResp.DataBeanX.DataBean, BaseViewHolder> {

    private Context mContext;

    public IncomeDetailAdapter(Context context, List<AccPriceResp.DataBeanX.DataBean> data) {
        super(R.layout.income_detail_view_item, data != null ? data : new ArrayList<AccPriceResp.DataBeanX.DataBean>());
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AccPriceResp.DataBeanX.DataBean item) {

        helper.setText(R.id.income_time, item.getCreatime())
                .setText(R.id.income_agent_money, Html.fromHtml(mContext.getString(R.string.income_detail_agent_money, String.format("%.2f", item.getRecharge_amount()))))
                .setText(R.id.income_member_money, Html.fromHtml(mContext.getString(R.string.income_detail_member_money, String.format("%.2f", item.getAcc_amount()))));

        helper.addOnClickListener(R.id.income_agent_money);
        helper.addOnClickListener(R.id.income_member_money);
    }
}
