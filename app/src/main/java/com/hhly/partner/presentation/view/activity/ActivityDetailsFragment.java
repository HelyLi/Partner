package com.hhly.partner.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.NoticeByIdResp;
import com.hhly.partner.presentation.utils.DateUtils;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.GlideUtils;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

import java.util.Date;

import butterknife.BindView;

/**
 * 活动详情fragment
 * Created by dell on 2017/4/25.
 */

public class ActivityDetailsFragment extends BaseFragment implements IImmersiveApply,
        ActivityCenterContract.ActivityDetailsView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.activity_title_tv)
    TextView mActivityTitleTv;
    @BindView(R.id.author_tv)
    TextView mAuthorTv;
    @BindView(R.id.date_tv)
    TextView mDateTv;
    @BindView(R.id.activity_img)
    ImageView mActivityImg;
    @BindView(R.id.activity_content_tv)
    TextView mActivityContentTv;
    private ActivityCenterContract.ActivityDetailsPresenter mPresenter;

    public static ActivityDetailsFragment newInstance(int activityId) {
        Bundle args = new Bundle();
        args.putInt("activityId", activityId);
        ActivityDetailsFragment fragment = new ActivityDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new ActivityDetailsPresenterImpl(this);
        mRefreshLayout.setOnRefreshListener(this);
        fetchData(false);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getActivityDetailsInfo(getArguments().getInt("activityId"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity_details;
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
    public void setPresenter(ActivityCenterContract.ActivityDetailsPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getActivityDetailsInfoSuccess(NoticeByIdResp.DataBean.NoticeBean noticeBean) {
        mRefreshLayout.setRefreshing(false);
        if (noticeBean == null) return;
        mActivityTitleTv.setText(noticeBean.getTitle());
        mAuthorTv.setText(getString(R.string.activity_details_author, noticeBean.getCreator()));
        mDateTv.setText(getString(R.string.activity_details_date, DateUtils.dateToString(new Date(noticeBean.getCreatorTime()), "yyyy-MM-dd")));
        GlideUtils.loadImageFitSize(getContext(), noticeBean.getImageUrl(),
                DisplayUtil.getScreenWidth(getContext()) - DisplayUtil.dip2px(getContext(), 30), mActivityImg,
                R.drawable.ic_action_banner_loading, R.drawable.ic_action_banner_loading);
        mActivityContentTv.setText(noticeBean.getExplain());
    }

    @Override
    public void getActivityDetailsInfoFail(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getContext(), TextUtils.isEmpty(msg) ? getString(R.string.partner_request_error) : msg);
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }
}
