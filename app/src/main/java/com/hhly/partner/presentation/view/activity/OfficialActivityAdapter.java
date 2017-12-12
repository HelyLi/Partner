package com.hhly.partner.presentation.view.activity;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;
import com.hhly.partner.presentation.utils.DateUtils;
import com.hhly.partner.presentation.utils.GlideUtils;

import java.util.Date;

/**
 * 官方活动adapter
 * Created by dell on 2017/4/15.
 */

public class OfficialActivityAdapter extends BaseQuickAdapter<GetIndexlbtResp.DataBean.ListBean, BaseViewHolder> {

    public OfficialActivityAdapter() {
        super(R.layout.official_activity_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetIndexlbtResp.DataBean.ListBean item) {
        helper.setText(R.id.title_tv, item.getRemarks());
        helper.setText(R.id.date_tv, DateUtils.format(new Date(item.getTime())));
        ImageView imageView = helper.getView(R.id.content_img);
        GlideUtils.loadImageViewLoading(imageView.getContext(), item.getPictureUrl(),
                imageView, R.drawable.ic_activity_loading, R.drawable.ic_activity_loading);
    }
}
