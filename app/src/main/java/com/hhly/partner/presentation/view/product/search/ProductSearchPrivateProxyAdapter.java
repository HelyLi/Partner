package com.hhly.partner.presentation.view.product.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.presentation.view.web.WebActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * 独代产品adapter
 * Created by dell on 2017/5/2.
 */

public class ProductSearchPrivateProxyAdapter extends TagAdapter<PrivateProxyProductItem> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public ProductSearchPrivateProxyAdapter(Context context, List<PrivateProxyProductItem> list) {
        super(list);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(FlowLayout parent, int position, final PrivateProxyProductItem item) {
        TextView tv = (TextView) mLayoutInflater.inflate(R.layout.product_search_private_proxy_item_layout, null);
        tv.setText(item.getGameName());
        Drawable drawable = mContext.getResources().getDrawable(item.getGameDrawable());
        tv.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(WebActivity.getCallingIntent(mContext, item.getJumpUrl(),
                        item.getGameName()));
            }
        });
        return tv;
    }
}
