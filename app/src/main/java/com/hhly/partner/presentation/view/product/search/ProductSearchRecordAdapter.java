package com.hhly.partner.presentation.view.product.search;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataByNameResp;
import com.hhly.partner.presentation.view.product.ProductDetailsActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * 产品搜索》历史搜索adapter
 * Created by dell on 2017/4/26.
 */

public class ProductSearchRecordAdapter extends TagAdapter<GameDataByNameResp.DataBeanX.DataBean> {
    private List<GameDataByNameResp.DataBeanX.DataBean> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public ProductSearchRecordAdapter(Context context, List<GameDataByNameResp.DataBeanX.DataBean> list) {
        super(list);
        mList = list;
        mInflater = LayoutInflater.from(context);
        mContext=context;
    }

    @Override
    public View getView(FlowLayout parent, int position, final GameDataByNameResp.DataBeanX.DataBean bean) {
        View convertView = mInflater.inflate(R.layout.product_search_record_item_layout, null);
        TextView textView = (TextView) convertView.findViewById(R.id.search_record_tv);
        textView.setText(bean.getNAME());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.startActivity(mContext, ProductDetailsActivity.getCallIntent(mContext,
                        bean.getID(),bean.getNAME()),null);
            }
        });
        return convertView;
    }

    public void setNewData(List data) {
        if (data == null) return;
        mList.clear();
        mList.addAll(data);
        notifyDataChanged();
    }


}
