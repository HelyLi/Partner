package com.hhly.partner.presentation.view.me.cash;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.application.App;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.BankResp;

import java.util.ArrayList;

/**
 * Created by dell on 2017/4/17.
 */

public class CashAccountAdapter extends BaseQuickAdapter<BankResp.DataBeanX.DataBean, BaseViewHolder> {
    private String[] mBankNames;
    public CashAccountAdapter() {
        super(R.layout.cash_account_item, new ArrayList<BankResp.DataBeanX.DataBean>());
        mBankNames= App.getContext().getResources().getStringArray(R.array.bank_names);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankResp.DataBeanX.DataBean item) {
        for (int i = 0; i <mBankNames.length ; i++) {
            if (mBankNames[i].equals(item.getBANK_ID())){
                setIconAndBackground(i,helper);
                break;
            }
        }
        helper.setText(R.id.bank_name_tv, item.getBANK_ID());
        helper.setText(R.id.card_number_tv, setBankCardNum(item.getACC_NO()));
    }

    private String setBankCardNum(String bankCardNum){
        StringBuilder sb  =new StringBuilder();
        if(!TextUtils.isEmpty(bankCardNum)) {
            for (int i = 0; i < bankCardNum.length(); i++) {
                if (i >= 4 && i < bankCardNum.length()-4) {
                    sb.append('*');
                } else {
                    char c = bankCardNum.charAt(i);
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    private void setIconAndBackground(int bank,BaseViewHolder helper){
        int icon = 0;
        int bg=0;
        switch (bank){
            case 0://农业银行
                icon=R.drawable.ic_cash_account_abc;
                bg=R.drawable.bg_cash_account_abc;
                break;
            case 1://建设银行
                icon=R.drawable.ic_cash_account_ccb;
                bg=R.drawable.bg_cash_account_ccb;
                break;
            case 2://中国银行
                icon=R.drawable.ic_cash_account_boc;
                bg=R.drawable.bg_cash_account_icbc;
                break;
            case 3://工商银行
                icon=R.drawable.ic_cash_account_icbc;
                bg=R.drawable.bg_cash_account_icbc;
                break;
            case 4://招商银行
                icon=R.drawable.ic_cash_account_cmb;
                bg=R.drawable.bg_cash_account_cmb;
                break;
            case 5://交通银行
                icon=R.drawable.ic_cash_account_jtb;
                bg=R.drawable.bg_cash_account_ccb;
                break;
            case 6://邮政储蓄银行
                icon=R.drawable.ic_cash_account_postb;
                bg=R.drawable.bg_cash_account_abc;
                break;
            case 7://中信银行
                icon=R.drawable.ic_cash_account_cncb;
                bg=R.drawable.bg_cash_account_icbc;
                break;
            case 8://光大银行
                icon=R.drawable.ic_cash_account_ceb;
                bg=R.drawable.bg_cash_account_abc;
                break;
            case 9://民生银行
                icon=R.drawable.ic_cash_account_cmsb;
                bg=R.drawable.bg_cash_account_abc;
                break;
            case 10://平安银行
                icon=R.drawable.ic_cash_account_pab;
                bg=R.drawable.bg_cash_account_icbc;
                break;
            case 11://广发银行
                icon=R.drawable.ic_cash_account_cgb;
                bg=R.drawable.bg_cash_account_icbc;
                break;
            case 12://华夏银行
                icon=R.drawable.ic_cash_account_hxb;
                bg=R.drawable.bg_cash_account_icbc;
                break;
            case 13://浦发银行
                icon=R.drawable.ic_cash_account_spdb;
                bg=R.drawable.bg_cash_account_ccb;
                break;
        }
        helper.setImageResource(R.id.bank_iv,icon);
        helper.setBackgroundRes(R.id.root_layout,bg);
    }
}
