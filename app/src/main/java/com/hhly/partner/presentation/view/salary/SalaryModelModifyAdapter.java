package com.hhly.partner.presentation.view.salary;

import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.FindAllCommissionTypeResp;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public class SalaryModelModifyAdapter extends BaseQuickAdapter<FindAllCommissionTypeResp.DataBeanX.DataBean, BaseViewHolder> {

    private Context mContext;

    public SalaryModelModifyAdapter(Context context, List<FindAllCommissionTypeResp.DataBeanX.DataBean> data) {
        super(R.layout.salary_model_item, data != null ? data : new ArrayList<FindAllCommissionTypeResp.DataBeanX.DataBean>());
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FindAllCommissionTypeResp.DataBeanX.DataBean item) {
        helper.setText(R.id.type_name_tv, item.getGameTypeName());
        if (item.getCOMMISSION_TYPE() != null) {
            helper.setText(R.id.item_tv, getRebateModeStr(item.getCOMMISSION_TYPE()));
        } else {
            helper.setText(R.id.item_tv, mContext.getString(R.string.home_rebate_select_type));
        }
    }



    /**
     * 获取返佣方式字符串
     *
     * @param type 1按输赢总额 2按有效金额 3按充值总额
     * @return 1按输赢总额 2按有效金额 3按充值总额
     */
    private String getRebateModeStr(int type) {
        switch (type) {
            case 1:
                return mContext.getString(R.string.home_rebate_select_lose_win);
            case 2:
                return mContext.getString(R.string.home_rebate_select_effective);
            case 3:
                return mContext.getString(R.string.home_rebate_select_recharge);
        }
        return mContext.getString(R.string.home_rebate_select_type);
    }
}
