package com.hhly.partner.presentation.view.me.us;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hhly.partner.R;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.me.MeAdapter;
import com.hhly.partner.presentation.view.me.MeInfoItem;
import com.hhly.partner.presentation.view.me.MeInfoItemItemDecoration;
import com.hhly.partner.presentation.view.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 关于我们
 * Created by dell on 2017/4/20.
 */

public class AboutUsFragment extends BaseFragment implements IImmersiveApply {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private MeAdapter mAdapter;

    public static AboutUsFragment newInstance() {
        Bundle args = new Bundle();

        AboutUsFragment fragment = new AboutUsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mAdapter = new MeAdapter();
        View headerView = new AboutUsHeaderView(getContext());
        mAdapter.addHeaderView(headerView);
        mAdapter.setHeaderCount(1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new MeInfoItemItemDecoration(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        initInfo();
    }

    private void initInfo() {
        List<MeInfoItem> infoItems = new ArrayList<>();
        infoItems.add(new MeInfoItem.Builder().title(R.string.partner_personal_us_web_game_platform)
                .isFirstItemOfGroup(true).action(mWebGameAction).build());
        infoItems.add(new MeInfoItem.Builder().title(R.string.partner_personal_us_h5_game_platform)
                .isFirstItemOfGroup(false).action(mH5GameAction).build());
        infoItems.add(new MeInfoItem.Builder().title(R.string.partner_personal_us_android_game_platform)
                .isFirstItemOfGroup(false).action(mAndroidGameAction).build());
        infoItems.add(new MeInfoItem.Builder().title(R.string.partner_personal_us_ios_game_platform)
                .isFirstItemOfGroup(false).action(mIosGameAction).build());
        mAdapter.setNewData(infoItems);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    private Runnable mWebGameAction = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivity(getContext(), WebActivity.getCallingIntent(getContext(),
                    "http://m.game.13322.com/", ""), null);
        }
    };
    private Runnable mH5GameAction = new Runnable() {
        @Override
        public void run() {
            ActivityCompat.startActivity(getContext(), WebActivity.getCallingIntent(getContext(),
                    "http://m.game.13322.com/", ""), null);
        }
    };
    private Runnable mAndroidGameAction = new Runnable() {
        @Override
        public void run() {
        }
    };
    private Runnable mIosGameAction = new Runnable() {
        @Override
        public void run() {
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about_us;
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
