package com.hhly.partner.presentation.view.me.notice;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.NoticeResp;

import java.util.ArrayList;

/**
 * 通知adapter
 * Created by dell on 2017/5/2.
 */

public class NoticeAdapter extends BaseQuickAdapter<NoticeResp.DataBean.PagerBean.ListBean, BaseViewHolder> {
    public NoticeAdapter() {
        super(R.layout.notice_item_layout, new ArrayList<NoticeResp.DataBean.PagerBean.ListBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeResp.DataBean.PagerBean.ListBean item) {
        helper.setText(R.id.notice_tv,item.getTitle());
    }
}
