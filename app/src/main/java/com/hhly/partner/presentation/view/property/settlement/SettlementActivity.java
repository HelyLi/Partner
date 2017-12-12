package com.hhly.partner.presentation.view.property.settlement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;
import butterknife.BindView;
import com.hhly.partner.R;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

/**
 * description :我的资产 --> 结算规则
 * Created by Flynn
 * 2017/4/26
 */

public class SettlementActivity extends BaseActivity implements IImmersiveApply {

    @BindView(R.id.settlement_content)
    TextView mSettlementContent;

    /**
     * 我的资产 --> 结算规则
     */
    public static final int SETTLEMENT = 1;
    /**
     * 代理规则 --> 条款与规则
     */
    public static final int RULE = 2;

    public static final String KEY = "key";

    public static Intent getCallIntent(Context context, int type) {
        Intent intent = new Intent(context, SettlementActivity.class);
        intent.putExtra(KEY, type);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (getIntent().getIntExtra(KEY, SETTLEMENT)) {
            case SETTLEMENT:
                if (mToolbarHelper != null){
                    mToolbarHelper.setTitle(getString(R.string.property_settlement));
                }
                break;
            case RULE:
                if (mToolbarHelper != null){
                    mToolbarHelper.setTitle(getString(R.string.home_agent_rule_rules));
                }
                break;
        }
        mSettlementContent.setText(Html.fromHtml(getString(R.string.property_settlement_content)));
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settlement;
    }
}
