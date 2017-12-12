package com.hhly.partner.presentation.view.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.UserPrefsUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.me.cash.CashAccountActivity;
import com.hhly.partner.presentation.view.me.code.InviteCodeActivity;
import com.hhly.partner.presentation.view.me.notice.NoticeActivity;
import com.hhly.partner.presentation.view.me.setting.AccountSettingActivity;
import com.hhly.partner.presentation.view.me.us.AboutUsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 个人
 * Created by dell on 2017/4/12.
 */

public class MeFragment extends BaseFragment implements IImmersiveApply {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private BaseQuickAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        initToolBar();
        mAdapter = new MeAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new MeInfoItemItemDecoration(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        initInfo();
    }

    private void initInfo() {
        List<MeInfoItem> meInfoItems = new ArrayList<>();
        String userName = UserPrefsUtil.getInstance().getUserName(getContext());
        meInfoItems.add(new MeInfoItem.Builder().title(R.string.partner_personal_account_setting)
                .icon(R.drawable.ic_personal_account_setting).content(TextUtils.isEmpty(userName) ? "" : userName)
                .isFirstItemOfGroup(true).action(mAccountSetting).build());
        meInfoItems.add(new MeInfoItem.Builder().title(R.string.partner_personal_cash_account)
                .icon(R.drawable.ic_personal_cash_account)
                .isFirstItemOfGroup(false).action(mCashAccount).build());
        meInfoItems.add(new MeInfoItem.Builder().title(R.string.partner_personal_about_us)
                .icon(R.drawable.ic_personal_about_us)
                .isFirstItemOfGroup(false).action(mAboutUs).build());
        meInfoItems.add(new MeInfoItem.Builder().title(R.string.partner_personal_invite_code)
                .icon(R.drawable.ic_personal_generalize)
                .isFirstItemOfGroup(true).action(mPersonalPromotion).build());
//        meInfoItems.add(new MeInfoItem.Builder().title(R.string.partner_personal_modify_salary_model)
//                .icon(R.drawable.ic_personal_modify_salary_model)
//                .isFirstItemOfGroup(false).action(mSalaryModel).build());
        mAdapter.setNewData(meInfoItems);
    }

    private void initToolBar() {
        mToolbarHelper.getToolbar().getMenu().clear();
        mToolbarHelper.getToolbar().inflateMenu(R.menu.personal_menu);
        mToolbarHelper.getToolbar().getMenu().findItem(R.id.menu_personal_notice)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        ActivityCompat.startActivity(getContext(), NoticeActivity.getCallIntent(getContext()), null);
                        return true;
                    }
                });
    }

    private final Runnable mAccountSetting = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivity(getContext(), AccountSettingActivity.getCallIntent(getContext()), null);
        }
    };
    private final Runnable mCashAccount = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivity(getContext(), CashAccountActivity.getCallIntent(getContext()), null);
        }
    };
    private final Runnable mAboutUs = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivity(getContext(), AboutUsActivity.getCallIntent(getContext()), null);
        }
    };
    private final Runnable mPersonalPromotion = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivity(getContext(), InviteCodeActivity.getCallIntente(getContext()), null);
        }
    };
//    private final Runnable mSalaryModel = new Runnable() {
//        @Override
//        public void run() {
//
//        }
//    };

    @Override
    public void onResume() {
        super.onResume();
        if (mToolbarHelper != null) {
            mToolbarHelper.getToolbar().setNavigationIcon(null);
            mToolbarHelper.setTitle(getString(R.string.tab_me));
        }
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected boolean enableSetActionBar() {
        return false;
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
}
