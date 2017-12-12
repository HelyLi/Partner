package com.hhly.partner.presentation.view.salary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.CommissionTypeResp;
import com.hhly.partner.data.net.protocol.user.FindAllCommissionTypeResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.account.LoginActivity;
import com.hhly.partner.presentation.view.main.MainTabActivity;
import com.hhly.partner.presentation.view.widget.BottomDialogItem;
import com.hhly.partner.presentation.view.widget.SalaryModelBottomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hhly.partner.presentation.view.account.RegisterStepTwoFragment.EXTRA_INVITE_CODE;
import static com.hhly.partner.presentation.view.account.RegisterStepTwoFragment.EXTRA_PASSWORD;
import static com.hhly.partner.presentation.view.account.RegisterStepTwoFragment.EXTRA_PHONE;

/**
 * description : 佣金模式修改
 * Created by dell on 2017/4/14.
 */

public class SalaryModelModifyFragment extends BaseFragment implements SalaryModelModifyContract.View
        , SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    //    @BindView(R.id.complete_btn)
    //    Button mCompleteBtn;
    //从注册进入
    public static final int REGISTER = 1;
    //从主页面进入
    public static final int REBATE = 2;
    public static final String KEY_TYPE = "key_type";

    /**
     * 从哪里跳转进来的 1表示从注册入口  2表示从主页面
     */
    private int mType = 2;

    private SalaryModelModifyContract.Presenter mPresenter;
    private SalaryModelModifyAdapter mAdapter;
    private Button completeBtn;


    public static SalaryModelModifyFragment newInstance(@NonNull Bundle bundle) {
        SalaryModelModifyFragment fragment = new SalaryModelModifyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new SalaryModelModifyPresenter(this);
        mRefreshLayout.setOnRefreshListener(this);
        Bundle bundle = getArguments();
        mType = bundle.getInt(KEY_TYPE, REBATE);
        initRecycleView();
        fetchData(false);
    }

    private void initRecycleView() {
        mAdapter = new SalaryModelModifyAdapter(getActivity(), null);
        //        View view = View.inflate(getActivity(), R.layout.widget_empty_view, null);
        //        mAdapter.setEmptyView(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                final FindAllCommissionTypeResp.DataBeanX.DataBean data = (FindAllCommissionTypeResp.DataBeanX.DataBean) adapter.getData().get(position);
                ArrayList<String> list = (ArrayList<String>) data.getList();
                ArrayList<BottomDialogItem> mlist = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    int type = Integer.valueOf(list.get(i));
                    if (type != 4) {
                        BottomDialogItem item = new BottomDialogItem(getRebateModeStr(type), String.valueOf(type), data.getCOMMISSION_TYPE() != null && type == data.getCOMMISSION_TYPE());
                        mlist.add(item);
                    }
                }
                SalaryModelBottomDialog dialog = new SalaryModelBottomDialog(getActivity(), new SalaryModelBottomDialog.OnBottomItemClickListener() {
                    @Override
                    public void onClick(String value) {
                        data.setCOMMISSION_TYPE(Integer.parseInt(value));
                        mAdapter.notifyDataSetChanged();
                    }
                }).builder();
                dialog.setBottomItem(mlist);
                dialog.show();
            }
        });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.findAllCommissionType();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_salary_model_modify;
    }


    @Override
    public void setPresenter(SalaryModelModifyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void findAllCommissionTypeSuccess(List<FindAllCommissionTypeResp.DataBeanX.DataBean> list) {
        if (list != null && list.size() != 0) {
            View footView = View.inflate(getActivity(), R.layout.salary_model_item_foot, null);
            completeBtn = (Button) footView.findViewById(R.id.complete_btn);
            completeBtn.setOnClickListener(this);
            mAdapter.removeAllFooterView();
            mAdapter.addFooterView(footView);
            mAdapter.setNewData(list);
        }
        switch (mType) {
            case REBATE://主页选择佣金模式
                mPresenter.getCommissionType();
                break;
            case REGISTER: //注册选择佣金模式
                mRefreshLayout.setRefreshing(false);
                break;
        }
    }

    @Override
    public void findAllCommissionTypeFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void getCommissionTypeSuccess(List<CommissionTypeResp.DataBeanX.DataBean> list) {
        mRefreshLayout.setRefreshing(false);
        if (list == null) {
            return;
        }
        List<FindAllCommissionTypeResp.DataBeanX.DataBean> mData = mAdapter.getData();
        for (int i = 0; i < mData.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (mData.get(i).getId() != null && mData.get(i).getId().equalsIgnoreCase(String.valueOf(list.get(j).getGAME_TYPE_ID()))) {
                    mData.get(i).setCOMMISSION_TYPE(list.get(j).getCOMMISSION_TYPE());
                    mData.get(i).setRECORD_ID(list.get(j).getRECORD_ID());
                    break;
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCommissionTypeFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void updateCommissionTypeSuccess() {
        ToastUtil.showShort(getActivity(), getString(R.string.home_rebate_select_change_success));
        startActivity(MainTabActivity.getCallIntent(getActivity(), 0));
    }

    @Override
    public void updateCommissionTypeFailure(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    /**
     * 获取返佣方式字符串
     *
     * @param type 1按输赢总额 2按有效金额 3按充值总额
     * @return 1按输赢总额 2按有效金额 3按充值总额
     */
    private String getRebateModeStr(int type) {
        switch (type) {
            case 1:
                return getString(R.string.home_rebate_select_lose_win);
            case 2:
                return getString(R.string.home_rebate_select_effective);
            case 3:
                return getString(R.string.home_rebate_select_recharge);
        }
        return getString(R.string.home_rebate_select_type);
    }

    @Override
    public void onClick(View v) {
        StringBuilder sb = new StringBuilder();
        switch (mType) {
            case REGISTER://注册过来的
                if (!isRegAllSelect()) {
                    ToastUtil.showShort(getActivity(), getString(R.string.home_rebate_select_all));
                    return;
                }
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    FindAllCommissionTypeResp.DataBeanX.DataBean data = mAdapter.getData().get(i);
                    if (i == 0) {
                        sb.append(data.getId() + "_" + data.getCOMMISSION_TYPE());
                    } else {
                        sb.append("," + data.getId() + "_" + data.getCOMMISSION_TYPE());
                    }
                }
                Bundle bundle = getArguments();
                mPresenter.register(bundle.getString(EXTRA_PHONE), bundle.getString(EXTRA_PASSWORD),
                        bundle.getString(EXTRA_INVITE_CODE), sb.toString());
                break;
            case REBATE:
                if (!isAllSelect()) {
                    ToastUtil.showShort(getActivity(), getString(R.string.home_rebate_select_all));
                    return;
                }
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    FindAllCommissionTypeResp.DataBeanX.DataBean data = mAdapter.getData().get(i);
                    if (i == 0) {
                        sb.append(data.getId() + "_" + data.getCOMMISSION_TYPE() + "_" + data.getRECORD_ID());
                    } else {
                        sb.append("," + data.getId() + "_" + data.getCOMMISSION_TYPE() + "_" + data.getRECORD_ID());
                    }
                }
                mPresenter.updateCommissionType(sb.toString());
                break;
        }
    }

    private boolean isAllSelect() {
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            FindAllCommissionTypeResp.DataBeanX.DataBean data = mAdapter.getData().get(i);
            if (data.getRECORD_ID() == null || data.getCOMMISSION_TYPE() == null || data.getCOMMISSION_TYPE() == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isRegAllSelect() {
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            FindAllCommissionTypeResp.DataBeanX.DataBean data = mAdapter.getData().get(i);
            if (data.getCOMMISSION_TYPE() == null || data.getCOMMISSION_TYPE() == 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void onRegisterSuccess(String msg) {
        ToastUtil.showShort(getContext(), msg);
        ActivityCompat.startActivity(getContext(), LoginActivity.getCallIntent(getContext()), null);
        getActivity().finish();
    }

    @Override
    public void onRegisterFail(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }
}
