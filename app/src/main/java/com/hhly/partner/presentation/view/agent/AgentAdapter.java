package com.hhly.partner.presentation.view.agent;

import android.content.Context;
import android.text.Html;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.proxy.MyProxyDataResp;
import com.hhly.partner.presentation.utils.DateUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/15
 */

public class AgentAdapter extends BaseQuickAdapter<MyProxyDataResp.DataBean, BaseViewHolder> {

    private Context mContext;

    public AgentAdapter(Context context, List<MyProxyDataResp.DataBean> data) {
        super(R.layout.agent_recycle_view_item, data != null ? data : new ArrayList<MyProxyDataResp.DataBean>());
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyProxyDataResp.DataBean item) {
        try {
            String date = DateUtils.longToString(item.getRegisterDate());
            helper.setText(R.id.date_tv, date);
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
        helper.setText(R.id.name_tv, item.getRealName())
                .setText(R.id.grade_tv, Html.fromHtml(mContext.getString(R.string.partner_home_agent_grade, item.getPartnerNo())))
                .setText(R.id.sub_proxy_tv, Html.fromHtml(mContext.getString(R.string.partner_home_agent_sub_proxy, item.getAgentsNum())))
                .setText(R.id.total_money, Html.fromHtml(mContext.getString(R.string.partner_home_agent_total_money, item.getRechargeAmount())));
    }
}
