package com.hhly.partner.presentation.view.product.search;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataByNameResp;
import com.hhly.partner.presentation.utils.SearchRecordPrefsUtil;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 产品搜索初始化header
 * Created by dell on 2017/4/26.
 */

public class ProductSearchInitHeaderView extends FrameLayout {
    @BindView(R.id.record_layout)
    TagFlowLayout mRecordLayout;
    @BindView(R.id.private_proxy_layout)
    TagFlowLayout mPrivateProxyLayout;
    @BindView(R.id.search_record_empty_tv)
    TextView mSearchRecordEmptyTv;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ProductSearchRecordAdapter mRecordTagAdapter;
    private ProductSearchPrivateProxyAdapter mPrivateProxyTagAdapter;

    public ProductSearchInitHeaderView(@NonNull Context context) {
        this(context, null);
    }

    public ProductSearchInitHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductSearchInitHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        View headerView = mLayoutInflater.inflate(R.layout.product_search_init_header_layout, null);
        ButterKnife.bind(this, headerView);
        addView(headerView);
        mRecordTagAdapter = new ProductSearchRecordAdapter(context, new ArrayList<GameDataByNameResp.DataBeanX.DataBean>());
        mRecordLayout.setAdapter(mRecordTagAdapter);
        List<PrivateProxyProductItem> privateProxyProductItems = new ArrayList<>();
        privateProxyProductItems.add(new PrivateProxyProductItem(mContext.getString(R.string.partner_product_private_proxy_dj),
                R.drawable.ic_product_search_dj, "http://hdj.13322.com/guess/index"));
        privateProxyProductItems.add(new PrivateProxyProductItem(mContext.getString(R.string.partner_product_private_proxy_sports),
                R.drawable.ic_product_search_guess, "http://m.13322.com/#/"));
        privateProxyProductItems.add(new PrivateProxyProductItem(mContext.getString(R.string.partner_product_private_proxy_dz),
                R.drawable.ic_product_search_lm, "http://www.haiyao.13322.com/vg/mobile/index.html"));
        mPrivateProxyTagAdapter = new ProductSearchPrivateProxyAdapter(context, privateProxyProductItems);
        mPrivateProxyLayout.setAdapter(mPrivateProxyTagAdapter);
    }

    @OnClick(R.id.search_clear_tv)
    public void onViewClicked() {
        SearchRecordPrefsUtil.getInstance().clear(getContext());
        mRecordLayout.removeAllViews();
        showOrHideSearchRecordEmptyTv(true);
    }


    /**
     * 更新历史搜索
     *
     * @param recordList
     */
    public void updateRecordTags(List<String> recordList) {
        mRecordLayout.removeAllViews();
        if (recordList != null && !recordList.isEmpty()) {
            mRecordTagAdapter.setNewData(getRecordGameBeanList(recordList));
            showOrHideSearchRecordEmptyTv(false);
        } else {
            showOrHideSearchRecordEmptyTv(true);
        }
    }

    /**
     * 获取保存的游戏列表（名字跟id是一起保存的）
     *
     * @param recordList 所保存的名字跟id列表
     * @return
     */
    private List<GameDataByNameResp.DataBeanX.DataBean> getRecordGameBeanList(List<String> recordList) {
        List<GameDataByNameResp.DataBeanX.DataBean> recordGameList = new ArrayList<>();
        GameDataByNameResp.DataBeanX.DataBean gameBean = null;
        for (String record : recordList) {
            gameBean = SearchRecordPrefsUtil.parseRecordContent(record);
            recordGameList.add(gameBean);
        }
        return recordGameList;
    }

    /**
     * 是否显示暂无历史搜索
     *
     * @param isShow true显示
     */
    private void showOrHideSearchRecordEmptyTv(boolean isShow) {
        mRecordLayout.setVisibility(isShow ? View.GONE : View.VISIBLE);
        mSearchRecordEmptyTv.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

}
