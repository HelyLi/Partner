package com.hhly.partner.presentation.view.member;

import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.proxy.GetMembersRechargeSumResp;
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

public class MemberAdapter extends BaseQuickAdapter<GetMembersRechargeSumResp.DataBean, BaseViewHolder> {

    private int mType;
    private Context mContext;

    public MemberAdapter(Context context, int type, List<GetMembersRechargeSumResp.DataBean> data) {
        super(R.layout.member_recycle_view_item, data != null ? data : new ArrayList<GetMembersRechargeSumResp.DataBean>());
        mContext = context;
        mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetMembersRechargeSumResp.DataBean item) {
        if (mType == 1) {
            String date = "";
            try {
                date = DateUtils.longToString(item.getRegisterDate());
            } catch (ParseException e) {
                Logger.e(e.getMessage());
            }
            helper.setText(R.id.register_time_tv, R.string.home_member_register_time)
                    .setText(R.id.account_tv, mContext.getString(R.string.home_member_account_name))
                    .setText(R.id.money_tv, mContext.getString(R.string.home_member_money))
                    .setTextColor(R.id.register_time, 0xFFa8a8a8)
                    .setText(R.id.register_time, date)
                    .setTextColor(R.id.account, 0xFF7b69ff)
                    .setText(R.id.account, item.getRealName())
                    .setText(R.id.money, String.valueOf(item.getRechargeAmount()));
        } else if (mType == 3) {
            helper.setText(R.id.register_time_tv, R.string.home_member_account_name)
                    .setText(R.id.account_tv, mContext.getString(R.string.home_member_pay_count))
                    .setText(R.id.money_tv, mContext.getString(R.string.home_member_money))
                    .setTextColor(R.id.register_time, 0xFF7b69ff)
                    .setText(R.id.register_time, item.getRealName())
                    .setTextColor(R.id.account, 0xFFa8a8a8)
                    .setText(R.id.account, String.valueOf(item.getRechargeNum()))
                    .setText(R.id.money, String.valueOf(item.getRechargeAmount()));
        }
    }
}
