package com.hhly.partner.presentation.view.home.headview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.hhly.partner.R;
import com.hhly.partner.presentation.view.proxycount.ProxyMainActivity;

/**
 * description :
 * Created by Flynn
 * 2017/4/15
 */

public class HomeHeadView extends FrameLayout implements View.OnClickListener {


    @BindView(R.id.home_money)
    TextView mHomeMoney;
    @BindView(R.id.home_proxy_people_tv)
    TextView mHomeProxyPeopleTv;
    @BindView(R.id.home_proxy_people_num)
    TextView mHomeProxyPeopleNum;
    @BindView(R.id.home_proxy_people)
    RelativeLayout mHomeProxyPeople;
    @BindView(R.id.home_proxy_product_num)
    TextView mHomeProxyProductNum;
    @BindView(R.id.home_proxy_product)
    RelativeLayout mHomeProxyProduct;
    @BindView(R.id.home_proxy_pay_count_num)
    TextView mHomeProxyPayCountNum;
    @BindView(R.id.home_proxy_pay_count)
    RelativeLayout mHomeProxyPayCount;

    private Context mContext;

    public HomeHeadView(Context context) {
        super(context);
        mContext = context;
        View view = inflate(context, R.layout.include_home_head, null);
        ButterKnife.bind(this, view);
        mHomeMoney.setOnClickListener(this);
        mHomeProxyPeople.setOnClickListener(this);
        mHomeProxyProduct.setOnClickListener(this);
        mHomeProxyPayCount.setOnClickListener(this);
        addView(view);
    }

    public HomeHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void updateData(String money, String proxyCount, String productCount, String payCount) {
        mHomeMoney.setText(money);
        mHomeProxyPeopleNum.setText(proxyCount);
        mHomeProxyProductNum.setText(productCount);
        mHomeProxyPayCountNum.setText(payCount);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_money:
                mContext.startActivity(ProxyMainActivity.getCallIntent(mContext, ProxyMainActivity.INCOMES));
                break;
            case R.id.home_proxy_people:
                mContext.startActivity(ProxyMainActivity.getCallIntent(mContext, ProxyMainActivity.AGENTPEOPLE));
                break;
            case R.id.home_proxy_product:
                mContext.startActivity(ProxyMainActivity.getCallIntent(mContext, ProxyMainActivity.AGENTPRODUCT));
                break;
            case R.id.home_proxy_pay_count:
                mContext.startActivity(ProxyMainActivity.getCallIntent(mContext, ProxyMainActivity.PAIDNUM));
                break;
        }
    }
}
