package com.hhly.partner.presentation.view.proxycount;

import android.content.Context;
import android.support.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/20
 */

public class ProxyCountAdapter extends BaseQuickAdapter<ProxyItem, BaseViewHolder> {

    //    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public ProxyCountAdapter(Context context, @NonNull List<ProxyItem> data) {
        super(R.layout.proxy_count_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProxyItem item) {
        helper.setText(R.id.item_tv, item.getName());
    }
}
