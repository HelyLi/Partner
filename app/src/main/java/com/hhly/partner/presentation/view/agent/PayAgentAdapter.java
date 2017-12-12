package com.hhly.partner.presentation.view.agent;

import android.content.Context;
import android.text.Html;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.proxy.RechargeByUnderAgentsDetailResp;
import com.hhly.partner.presentation.utils.DateUtils;
import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public class PayAgentAdapter extends BaseQuickAdapter<RechargeByUnderAgentsDetailResp.DataBean, BaseViewHolder> {

    private Context mContext;

    public PayAgentAdapter(Context context, List<RechargeByUnderAgentsDetailResp.DataBean> data) {
        super(R.layout.pay_agent_recycle_view_item, data != null ? data : new ArrayList<RechargeByUnderAgentsDetailResp.DataBean>());
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RechargeByUnderAgentsDetailResp.DataBean item) {
        String date = "";
        try {
            date = DateUtils.longToString(item.getRegisterDate());
        } catch (ParseException e) {
            Logger.e(e.getMessage());
        }
        helper.setText(R.id.name_tv, item.getRealName())
                .setText(R.id.date_tv, date)
                .setText(R.id.count_tv, Html.fromHtml(mContext.getString(R.string.partner_home_pay_agent_count, item.getRechargeNum())))
                .setText(R.id.money_tv, Html.fromHtml(mContext.getString(R.string.partner_home_pay_agent_money_total, String.format("%.2f", item.getRechargeAmount()))));

    }
}
