package com.hhly.partner.presentation.view.rule;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.extension.ExtensionActivity;
import com.hhly.partner.presentation.view.property.MyPropertyActivity;
import com.hhly.partner.presentation.view.property.settlement.SettlementActivity;
import com.hhly.partner.presentation.view.proxycount.ProxyCountAdapter;
import com.hhly.partner.presentation.view.proxycount.ProxyItem;
import com.hhly.partner.presentation.view.widget.RecycleViewItemDivider;

import java.util.ArrayList;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public class AgentRuleFragment extends BaseFragment {

    @BindView(R.id.image_bg)
    ImageView mImageBg;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ProxyCountAdapter mAdapter;
    public int mType = 1;

    public static AgentRuleFragment newInstance(int type) {
        Bundle args = new Bundle();
        AgentRuleFragment fragment = new AgentRuleFragment();
        args.putInt(AgentRuleActivity.KEY, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mType = getArguments().getInt(AgentRuleActivity.KEY, AgentRuleActivity.RULE);
        switch (mType) {
            case AgentRuleActivity.RULE:
                mImageBg.setImageResource(R.drawable.ic_proxy_rules_banner);
                break;
            case AgentRuleActivity.EXTENSION:
                mImageBg.setImageResource(R.drawable.ic_generalize_banner);
                break;
        }
        initRecycleView();
    }

    private void initRecycleView() {
        ArrayList<ProxyItem> items = new ArrayList<>();
        switch (mType) {
            case AgentRuleActivity.RULE:
                items.add(new ProxyItem(getString(R.string.home_agent_rule_growup), mGrowUp));
                items.add(new ProxyItem(getString(R.string.home_agent_rule_income), mIncome));
                //                items.add(new ProxyItem(getString(R.string.home_agent_rule_rules), mRules));
                break;
            case AgentRuleActivity.EXTENSION:
                items.add(new ProxyItem(getString(R.string.home_agent_extension_agent), mAgentExtension));
                items.add(new ProxyItem(getString(R.string.home_agent_extension_android), mAndroidExtension));
                items.add(new ProxyItem(getString(R.string.home_agent_extension_h5), mH5Extension));
                items.add(new ProxyItem(getString(R.string.home_agent_extension_pc), mPCExtension));
                break;
        }

        mAdapter = new ProxyCountAdapter(getActivity(), items);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewItemDivider().setDividerWith(2));
        mRecyclerView.setAdapter(mAdapter);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mRecyclerView.getLayoutParams();
        params.height = DisplayUtil.dip2px(getActivity(), 61 * (items.size() + 1));
        mRecyclerView.setLayoutParams(params);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProxyItem item = (ProxyItem) adapter.getData().get(position);
                mHandler.post(item.getAction());
            }
        });
    }

    /**
     * 合作伙伴成长步骤
     */
    private Runnable mGrowUp = new Runnable() {
        @Override
        public void run() {
            startActivity(PartnerAgentActivity.getCallIntent(getActivity(), 0));
        }
    };
    /**
     * 合作伙伴收入
     */
    private Runnable mIncome = new Runnable() {
        @Override
        public void run() {
            startActivity(PartnerAgentActivity.getCallIntent(getActivity(), 1));
        }
    };

    /**
     * 条款与规则
     */
    private Runnable mRules = new Runnable() {
        @Override
        public void run() {
            startActivity(SettlementActivity.getCallIntent(getActivity(), SettlementActivity.RULE));
        }
    };

    /**
     * 代理推广
     */
    private Runnable mAgentExtension = new Runnable() {
        @Override
        public void run() {
            startActivity(ExtensionActivity.getCallIntent(getActivity(), ExtensionActivity.EXTENSION_AGENT));
        }
    };

    /**
     * android推广
     */
    private Runnable mAndroidExtension = new Runnable() {
        @Override
        public void run() {
            ToastUtil.showShort(getActivity(), R.string.partner_expect);
        }
    };

    /**
     * H5推广
     */
    private Runnable mH5Extension = new Runnable() {
        @Override
        public void run() {
            startActivity(ExtensionActivity.getCallIntent(getActivity(), ExtensionActivity.EXTENSION_H5));
        }
    };


    /**
     * PC推广
     */
    private Runnable mPCExtension = new Runnable() {
        @Override
        public void run() {
            ToastUtil.showShort(getActivity(), R.string.partner_expect);
        }
    };


    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_agent_rule;
    }

}
