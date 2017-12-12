package com.hhly.partner.presentation.view.me;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;

import java.util.ArrayList;

/**
 * 个人adapter
 * Created by dell on 2017/4/17.
 */

public class MeAdapter extends BaseQuickAdapter<MeInfoItem, BaseViewHolder> {
    //头部的数量
    private int mHeaderCount;

    public MeAdapter() {
        super(R.layout.me_info_item, new ArrayList<MeInfoItem>());
    }

    @Override
    protected void convert(BaseViewHolder helper, final MeInfoItem item) {
        helper.setText(R.id.title_tv, item.getTitle());
        TextView titleText = helper.getView(R.id.title_tv);
        if (item.getIcon() != 0) {
            titleText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(titleText.getContext(),item.getIcon()), null, null, null);
        }
        if (!TextUtils.isEmpty(item.getContent())){
            helper.setText(R.id.content_tv, item.getContent());
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.getAction().run();
            }
        });
    }

    /**
     * 是不是某个组的第一个item
     * @param position position
     * @return true 是
     */
    public boolean isFirstItemOfGroup(int position) {
        if (position<mHeaderCount){
            return false;
        }
        return getData().get(position - mHeaderCount).isFirstItemOfGroup();
    }

    public void setHeaderCount(int headerCount) {
        mHeaderCount = headerCount;
    }
}
