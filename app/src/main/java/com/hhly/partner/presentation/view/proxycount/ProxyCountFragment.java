package com.hhly.partner.presentation.view.proxycount;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.proxy.*;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.agent.AgentActivity;
import com.hhly.partner.presentation.view.agent.PayAgentActivity;
import com.hhly.partner.presentation.view.chart.CustomLineEntry;
import com.hhly.partner.presentation.view.chart.CustomMarkerView;
import com.hhly.partner.presentation.view.main.MainTabActivity;
import com.hhly.partner.presentation.view.member.MemberActivity;
import com.hhly.partner.presentation.view.property.MyPropertyActivity;
import com.hhly.partner.presentation.view.widget.NestedSwipeRefreshLayout;
import com.hhly.partner.presentation.view.widget.RecycleViewItemDivider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/18
 */

public class ProxyCountFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnChartValueSelectedListener, ProxyCountContract.View {

    public final static String TYPE = "type";
    @BindView(R.id.time_7)
    TextView mTime7;
    @BindView(R.id.time_30)
    TextView mTime30;
    @BindView(R.id.time_90)
    TextView mTime90;
    @BindView(R.id.chart)
    LineChart mChart;
    @BindView(R.id.home_proxy_people_tv)
    TextView mPeopleTv;
    @BindView(R.id.home_proxy_people_num)
    TextView mPeopleNum;
    @BindView(R.id.home_proxy_people)
    RelativeLayout mPeople;
    @BindView(R.id.home_proxy_product_tv)
    TextView mProductTv;
    @BindView(R.id.home_proxy_product_num)
    TextView mProductNum;
    @BindView(R.id.home_proxy_product)
    RelativeLayout mProduct;
    @BindView(R.id.home_proxy_pay_count_tv)
    TextView mPayCountTv;
    @BindView(R.id.home_proxy_pay_count_num)
    TextView mPayCountNum;
    @BindView(R.id.home_proxy_pay_count)
    RelativeLayout mPayCount;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    NestedSwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.chart_bg)
    RelativeLayout mChartBg;
    @BindView(R.id.ll_num)
    LinearLayout mLlNum;
    @BindView(R.id.product_recycler_view)
    RecyclerView mProductRecyclerView;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;

    private ProxyCountContract.Presenter mPresenter;
    /**
     * 当前页  代理统计0 代理人数1 代理产品2 付费次数3
     */
    private int type;
    /**
     * 7天:0  30天:1  90天:2
     */
    private int mTimeType = 0;

    /**
     * 代理人数页面
     * 二级代理0  三级代理1  直属会员2
     * <p>
     * 付费次数
     * 直属会员0  发展代理1  合格会员2
     */
    private int mUnderAgentType = 0;

    /**
     * 代理产品页面
     * 产品对应的id
     */
    private String mProductId;

    private ProxyCountAdapter mAdapter;
    private ProxyProductAdapter mProductAdapter;
    /**
     * 收入统计页面图表数据缓存
     */
    private GetTodayRechargeSumResp.DataBean mTodayRechargeSumData;
    /**
     * 代理人数,付费次数界面图表数据缓存
     */
    private HashMap<String, AppUnderAgentsNumByTimeareaResp.DataBean> mAgentMap = new HashMap<>();
    /**
     * 代理产品界面图表数据缓存
     */
    private HashMap<String, AppAgentProductRechargeGraphResp.DataBean> mProductMap = new HashMap<>();

    public static ProxyCountFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        ProxyCountFragment fragment = new ProxyCountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new ProxyCountPresenter(this);
        initView();
        initChart();
        //        initChartData(5, 20f);
        mRefreshLayout.setOnRefreshListener(this);
        initRecycleView();
    }

    /**
     * 初始化图表基本信息
     */
    private void initChart() {
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false); //设置网格背景
        mChart.setScaleEnabled(false);//设置缩放
        mChart.setDoubleTapToZoomEnabled(false);//设置双击不进行缩放
        // no description text
        mChart.getDescription().setEnabled(false); //chart上的右下角加描述
        // enable touch gestures
        mChart.setTouchEnabled(true); //设置图表滑动是否可用
        mChart.getLegend().setEnabled(false); //设置表头不可用
        mChart.setDrawGridBackground(false);
        mChart.setNoDataTextColor(Color.WHITE);
        mChart.setNoDataText(getString(R.string.partner_no_data));

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        CustomMarkerView mv = new CustomMarkerView(getActivity(), R.layout.agent_custom_marker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        // enable scaling and dragging
        //        mChart.setDragEnabled(true); //打开或关闭对图表的拖动。
        // if disabled, scaling can be done on x- and y-axis separately
        //        mChart.setPinchZoom(false); //设置是否能扩大扩小

        //设置X轴
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        //        xl.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setGridLineWidth(1);
        xAxis.setGridColor(Color.WHITE);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setTextColor(Color.WHITE);
        //        xAxis.setAxisLineWidth(DisplayUtil.dip2px(getActivity(), 1));
        xAxis.setAxisLineWidth(1);
        //
//        xAxis.setAvoidFirstLastClipping(true);
        //        xl.setAxisMinimum(0f);
        //        xl.setEnabled(false);

        YAxis leftAxis = mChart.getAxisLeft();
        //        leftAxis.setLabelCount(5);
        leftAxis.setAxisLineColor(Color.WHITE);
        //        leftAxis.setAxisLineWidth(DisplayUtil.dip2px(getActivity(), 1));
        leftAxis.setDrawAxisLine(false);
        leftAxis.setAxisLineWidth(1);
        leftAxis.setDrawLabels(false);
        //        leftAxis.setInverted(false);
        leftAxis.setDrawGridLines(false);
        //        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
                rightAxis.setDrawAxisLine(false);//右侧坐标轴线
        rightAxis.setAxisLineColor(Color.WHITE);
        //        rightAxis.setAxisLineWidth(DisplayUtil.dip2px(getActivity(), 1));
        rightAxis.setAxisLineWidth(1);
        rightAxis.setDrawLabels(false);//右侧坐标轴数组Lable
        rightAxis.setDrawGridLines(false);
        //        rightAxis.setEnabled(false);

        // // restrain the maximum scale-out factor
        // mChart.setScaleMinima(3f, 3f);
        //
        // // center the view to a specific position inside the chart
        // mChart.centerViewPort(10, 50);

        // get the legend (only possible after setting data)
        //        Legend l = mChart.getLegend();

        // modify the legend ...
        //        l.setForm(Legend.LegendForm.LINE);

        // dont forget to refresh the drawing
        mChart.invalidate();
    }

    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        //        View view = View.inflate(getActivity(), R.layout.widget_empty_view, null);
        //        mAdapter.setEmptyView(view);
        ArrayList<ProxyItem> items = new ArrayList<>();

        switch (type) {
            case ProxyMainActivity.INCOMES:
                items.add(new ProxyItem(getString(R.string.agent_count_agent_my_money), myMoney));
                break;
            case ProxyMainActivity.AGENTPEOPLE:
                items.add(new ProxyItem(getString(R.string.agent_count_agent_my_agent), myAgent));
                items.add(new ProxyItem(getString(R.string.agent_count_agent_under), myUnder));
                break;
            case ProxyMainActivity.AGENTPRODUCT:
                items.add(new ProxyItem(getString(R.string.agent_count_agent_product_hall), productHall));
                break;
            case ProxyMainActivity.PAIDNUM:
                items.add(new ProxyItem(getString(R.string.agent_count_agent_my_agent), mPayAgent));
                items.add(new ProxyItem(getString(R.string.agent_count_agent_under), myUnder));
                break;
        }
        mAdapter = new ProxyCountAdapter(getActivity(), items);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewItemDivider().setDividerWith(2));
        mRecyclerView.setAdapter(mAdapter);
        //        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mRecyclerView.getLayoutParams();
        //        params.height = DisplayUtil.dip2px(getActivity(), 61 * (items.size() + 1));
        //        mRecyclerView.setLayoutParams(params);
        //
        //        postDelay(new Runnable() {
        //            @Override
        //            public void run() {
        //                mNestedScrollView.scrollTo(0, 0);
        //            }
        //        }, 100);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProxyItem item = (ProxyItem) adapter.getData().get(position);
                mHandler.post(item.getAction());
            }
        });
    }

    /**
     * 我的代理
     */
    private Runnable myAgent = new Runnable() {
        @Override
        public void run() {
            startActivity(AgentActivity.getCallIntent(getActivity()));
        }
    };

    /**
     * 付费界面 == > 我的代理
     */
    private Runnable mPayAgent = new Runnable() {
        @Override
        public void run() {
            startActivity(PayAgentActivity.getCallIntent(getActivity()));
        }
    };
    /**
     * 我的资产
     */
    private Runnable myMoney = new Runnable() {
        @Override
        public void run() {
            startActivity(MyPropertyActivity.getCallIntent(getActivity()));
        }
    };
    /**
     * 直属会员
     */
    private Runnable myUnder = new Runnable() {
        @Override
        public void run() {
            startActivity(MemberActivity.getCallIntent(getActivity(), type));
        }
    };
    /**
     * 产品大厅
     */
    private Runnable productHall = new Runnable() {
        @Override
        public void run() {
            startActivity(MainTabActivity.getCallIntent(getActivity(), 2));
        }
    };

    /**
     * 初始化基本View
     */
    private void initView() {
        Bundle bundle = getArguments();
        type = bundle.getInt(TYPE, 0);
        mTime7.setSelected(true);
        switch (type) {
            case ProxyMainActivity.INCOMES:
                mChartBg.setBackgroundResource(R.drawable.bg_income_statistic);
                mPeopleTv.setText(getString(R.string.agent_count_incomes_7));
                mProductTv.setText(getString(R.string.agent_count_incomes_day));
                mPayCountTv.setText(getString(R.string.agent_count_incomes_max));
                break;
            case ProxyMainActivity.AGENTPEOPLE:
                mChartBg.setBackgroundResource(R.drawable.bg_proxy_number);
                mPeopleTv.setText(getString(R.string.agent_count_people_sub));
                mProductTv.setText(getString(R.string.agent_count_people_sub_sub));
                mPayCountTv.setText(getString(R.string.agent_count_people_under));
                mPeople.setBackgroundResource(R.drawable.agent_under_agent_selector);
                mProduct.setBackgroundResource(R.drawable.agent_under_agent_selector);
                mPayCount.setBackgroundResource(R.drawable.agent_under_agent_selector);
                mPeople.setSelected(true);
                break;
            case ProxyMainActivity.AGENTPRODUCT:
                mChartBg.setBackgroundResource(R.drawable.bg_proxy_product);
                mPeopleTv.setText(getString(R.string.agent_count_incomes_7));
                mProductTv.setText(getString(R.string.agent_count_incomes_day));
                mPayCountTv.setText(getString(R.string.agent_count_incomes_max));
                mLlNum.setVisibility(View.GONE);
                mProductRecyclerView.setVisibility(View.VISIBLE);
                break;
            case ProxyMainActivity.PAIDNUM:
                mChartBg.setBackgroundResource(R.drawable.bg_paid_number);
                mPeopleTv.setText(getString(R.string.agent_count_agent_paid_under));
                mProductTv.setText(getString(R.string.agent_count_agent_paid_proxy));
                mPayCountTv.setText(getString(R.string.agent_count_agent_qualified_member));
                mPeople.setBackgroundResource(R.drawable.agent_under_agent_selector);
                mProduct.setBackgroundResource(R.drawable.agent_under_agent_selector);
                mPayCount.setBackgroundResource(R.drawable.agent_under_agent_selector);
                mPeople.setSelected(true);
                break;
        }
    }


    //     switch (type) {
    //     case ProxyMainActivity.INCOMES:
    //     break;
    //     case ProxyMainActivity.AGENTPEOPLE:
    //     break;
    //     case ProxyMainActivity.AGENTPRODUCT:
    //     break;
    //     case ProxyMainActivity.PAIDNUM:
    //     break;
    //     }


    @Override
    protected void fetchData(boolean isLoadMore) {
        mAgentMap.clear();
        mRefreshLayout.setRefreshing(!isLoadMore);
        switch (type) {
            case ProxyMainActivity.INCOMES:
                mPresenter.getTodayRechargeSum();
                break;
            case ProxyMainActivity.AGENTPEOPLE:
                mPresenter.getProxyNumsData();
                getUnderAgentData();
                break;
            case ProxyMainActivity.AGENTPRODUCT:
                mPresenter.queryAppAgentProductRechargeTopNine();
                break;
            case ProxyMainActivity.PAIDNUM:
                mPresenter.getMembersAgentsRechargeNum();
                getPayCountData();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_proxy_count;
    }

    /**
     * 获取代理人数界面数据
     */
    private void getUnderAgentData() {
        if (mAgentMap.get(mUnderAgentType + "_" + mTimeType) != null) {
            upDateChartData(mAgentMap.get(mUnderAgentType + "_" + mTimeType), 1);
            return;
        }
        mChart.clear();
        if (mUnderAgentType == 0) {//二级代理
            mPresenter.queryAppUnderAgentsNumByTimearea(getQueryDate(), mUnderAgentType + "_" + mTimeType);
        } else if (mUnderAgentType == 1) {//三级代理
            mPresenter.queryAppUunderAgentsNumByTimearea(getQueryDate(), mUnderAgentType + "_" + mTimeType);
        } else if (mUnderAgentType == 2) {//直属会员
            mPresenter.queryAppMemberNumByTimeArea(getQueryDate(), mUnderAgentType + "_" + mTimeType);
        }
    }

    /**
     * 获取付费次数
     */
    private void getPayCountData() {
        if (mAgentMap.get(mUnderAgentType + "_" + mTimeType) != null) {
            upDateChartData(mAgentMap.get(mUnderAgentType + "_" + mTimeType), 2);
            return;
        }
        mChart.clear();
        mPresenter.getMembersAgentsRechargeNumGraph(mUnderAgentType, getQueryDate(), mUnderAgentType + "_" + mTimeType);
    }

    private void getProductCountData() {
        if (mProductId == null) {
            return;
        }
        if (mProductMap.get(mProductId + "_" + mTimeType) != null) {
            upDateChartData(mProductMap.get(mProductId + "_" + mTimeType));
            return;
        }
        mChart.clear();
        mPresenter.queryAppAgentProductRechargeGraph(mProductId, getQueryDate(), mProductId + "_" + mTimeType);
    }

    private Integer getQueryDate() {
        if (mTimeType == 0) {
            return 7;
        } else if (mTimeType == 1) {
            return 30;
        } else if (mTimeType == 2) {
            return 90;
        }
        return 7;
    }


    @Override
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void initChartData() {
        switch (type) {
            case ProxyMainActivity.INCOMES:
                if (mTodayRechargeSumData == null) {
                    return;
                }
                List<GetTodayRechargeSumResp.DataBean.WeekListBean> list = new ArrayList<>();
                if (mTimeType == 0) {
                    list = mTodayRechargeSumData.getWeekList();
                } else if (mTimeType == 1) {
                    list = mTodayRechargeSumData.getMonthList();
                } else if (mTimeType == 2) {
                    list = mTodayRechargeSumData.getThreeMonthList();
                }
                setTodayRechargeSum(list);
                break;
            case ProxyMainActivity.AGENTPEOPLE:
                getUnderAgentData();
                break;
            case ProxyMainActivity.AGENTPRODUCT:
                getProductCountData();
                break;
            case ProxyMainActivity.PAIDNUM:
                getPayCountData();
                break;
        }
        //        mChart.setDescription("");

        //        // set data
        //        mChart.setData((LineData) mChartData);
        //        mChart.animateX(1500);//设置动画


    }

    private void setTodayRechargeSum(List<GetTodayRechargeSumResp.DataBean.WeekListBean> list) {
        if (list == null || list.size() == 0) {
            mChart.clear();
            return;
        }
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> m = new ArrayList<>();
        float max = 0;
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i).getQUERY_DATE().indexOf("-");
            String date = list.get(i).getQUERY_DATE().substring(index == -1 ? 0 : index + 1, list.get(i).getQUERY_DATE().length());
            m.add(date);
            entries.add(new CustomLineEntry(i, Float.parseFloat(list.get(i).getQUERY_COUNT()), date, getString(R.string.agent_count_chart_count)));
            max = max > Float.parseFloat(list.get(i).getQUERY_COUNT()) ? max : Float.parseFloat(list.get(i).getQUERY_COUNT());
        }

        //        entries.add(new Entry(0, 15));
        setChartData(entries, m, max);
    }

    private void setChartData(ArrayList<Entry> entries, ArrayList<String> m, float max) {
        mChart.clear();
        LineDataSet lineDataSet = new LineDataSet(entries, "");

        lineDataSet.setLineWidth(1.5f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setDrawValues(false);
        //点击后高亮的网格线
        lineDataSet.setDrawHighlightIndicators(false);

        // create a data object with the datasets
        LineData data = new LineData(lineDataSet);
        mChart.getXAxis().setLabelCount((m.size() == 0 || m.size() == 1) ? 3 : m.size(), true);
        mChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(m));
        mChart.getXAxis().setGranularity(1);
        mChart.getXAxis().setGranularityEnabled(true);
        //        mChart.getXAxis().setLabelRotationAngle(-10);
        mChart.getAxisLeft().setAxisMaximum(max * 1.3f);
        mChart.getAxisLeft().setAxisMinimum(0.00f);
        // set data
        //        mChart.setExtraBottomOffset(DisplayUtil.dip2px(getActivity(), 4));
        mChart.setExtraOffsets(DisplayUtil.dip2px(getActivity(), 8), 0, DisplayUtil.dip2px(getActivity(), 8), DisplayUtil.dip2px(getActivity(), 4));
        mChart.animateY(1000);
        //        mChart.animateXY(1500, 1000);
        mChart.setData(data);
        mChart.invalidate();
    }

    private ArrayList<String> getXAxisShowLabel() {
        ArrayList<String> m = new ArrayList<>();
        m.add("04-12");
        //        m.add("4-4");
        //        m.add("4-5");
        return m;
    }

    @Override
    public void setPresenter(ProxyCountContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getTodayRechargeSumSuccess(GetTodayRechargeSumResp.DataBean dataBean) {
        mRefreshLayout.setRefreshing(false);
        mPeopleNum.setText(TextUtils.isEmpty(dataBean.getWeek_recharge()) ? "0" : dataBean.getWeek_recharge());
        mProductNum.setText(TextUtils.isEmpty(dataBean.getToday_recharge()) ? "0" : dataBean.getToday_recharge());
        mPayCountNum.setText(TextUtils.isEmpty(dataBean.getMax_recharge()) ? "0" : dataBean.getMax_recharge());
        mTodayRechargeSumData = dataBean;
        initChartData();
    }

    private void setProductRecyclerViewData(List<AppAgentProductRechargeTopNineResp.DataBean> list) {
        mProductAdapter = new ProxyProductAdapter(list);
        mProductRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mProductRecyclerView.setAdapter(mProductAdapter);
        mProductRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mRefreshLayout.isRefreshing()) {
                    return;
                }
                List<AppAgentProductRechargeTopNineResp.DataBean> data = adapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    if (i == position) {
                        data.get(i).setChecked(true);
                    } else {
                        data.get(i).setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
                mProductId = data.get(position).getID();
                mTimeType = 0;
                setTimeTvSelect(true, false, false);
                getProductCountData();
            }
        });
        //        mRecyclerView.addItemDecoration(new RecycleViewItemDivider().setDividerWith(2));
    }

    @Override
    public void getTodayRechargeSumFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void getProxyNumsDataSuccess(GetProxyNumsDataResp.DataBean dataBean) {
        mRefreshLayout.setRefreshing(false);
        mPeopleNum.setText(String.valueOf(dataBean.getUnderAgentsNum()));
        mProductNum.setText(String.valueOf(dataBean.getUunderAgentsNum()));
        mPayCountNum.setText(String.valueOf(dataBean.getMemberUserids()));
    }

    @Override
    public void getProxyNumsDataFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void queryAgentsNumByTimeareaSuccess(AppUnderAgentsNumByTimeareaResp.DataBean dataBean, String key) {
        mRefreshLayout.setRefreshing(false);
        mAgentMap.put(key, dataBean);
        upDateChartData(dataBean, 1);
    }

    @Override
    public void getMembersAgentsRechargeNumGraphSuccess(AppUnderAgentsNumByTimeareaResp.DataBean dataBean, String key) {
        mRefreshLayout.setRefreshing(false);
        mAgentMap.put(key, dataBean);
        upDateChartData(dataBean, 2);
    }

    @Override
    public void queryAgentsNumByTimeareaFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        mChart.clear();
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void queryAppAgentProductRechargeTopNineSuccess(List<AppAgentProductRechargeTopNineResp.DataBean> list) {
        if (list != null && list.size() != 0) {
            setProductRecyclerViewData(list);
            list.get(0).setChecked(true);
            mProductId = list.get(0).getID();
            mPresenter.queryAppAgentProductRechargeGraph(mProductId, getQueryDate(), mProductId + "_" + mTimeType);
        } else {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void queryAppAgentProductRechargeTopNineFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getMembersAgentsRechargeNumSuccess(MembersAgentsRechargeNumResp.DataBean dataBean) {
        mRefreshLayout.setRefreshing(false);
        mPeopleNum.setText(String.valueOf(dataBean.getMemberNum()));
        mProductNum.setText(String.valueOf(dataBean.getAgentsNum()));
        mPayCountNum.setText(String.valueOf(dataBean.getQualifiedNum()));
    }

    @Override
    public void getMembersAgentsRechargeNumFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void queryAppAgentProductRechargeGraphSuccess(AppAgentProductRechargeGraphResp.DataBean dataBean, String key) {
        mRefreshLayout.setRefreshing(false);
        mProductMap.put(key, dataBean);
        upDateChartData(dataBean);
    }

    @Override
    public void queryAppAgentProductRechargeGraphFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        mChart.clear();
        ToastUtil.showShort(getActivity(), msg);
    }

    /**
     * 更新代理人数和付费次数界面数据
     *
     * @param type     1:更新代理人数界面数据  2:更新付费次数界面数据
     * @param dataBean 数据bean
     */
    private void upDateChartData(AppUnderAgentsNumByTimeareaResp.DataBean dataBean, int type) {
        List<AppUnderAgentsNumByTimeareaResp.DataBean.ListBean> list = dataBean.getList();
        if (list == null || list.size() == 0) {
            mChart.clear();
            return;
        }
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> m = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i).getQUERY_DATE().indexOf("-");
            String date = list.get(i).getQUERY_DATE().substring(index == -1 ? 0 : index + 1, list.get(i).getQUERY_DATE().length());
            m.add(date);
            entries.add(new CustomLineEntry(i, list.get(i).getQUERY_COUNT(), date, type == 1 ? getString(R.string.agent_count_chart_people) : getString(R.string.agent_count_chart_paid)));
            max = max > list.get(i).getQUERY_COUNT() ? max : list.get(i).getQUERY_COUNT();
        }

        //        entries.add(new Entry(0, 15));
        setChartData(entries, m, max);
    }

    /**
     * 更新代理产品界面图表数据
     *
     * @param dataBean
     */
    private void upDateChartData(AppAgentProductRechargeGraphResp.DataBean dataBean) {
        List<AppAgentProductRechargeGraphResp.DataBean.ListBean> list = dataBean.getList();
        if (list == null || list.size() == 0) {
            mChart.clear();
            return;
        }
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> m = new ArrayList<>();
        float max = 0;
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i).getQUERY_DATE().indexOf("-");
            String date = list.get(i).getQUERY_DATE().substring(index == -1 ? 0 : index + 1, list.get(i).getQUERY_DATE().length());
            m.add(date);
            entries.add(new CustomLineEntry(i, Float.parseFloat(list.get(i).getQUERY_COUNT()), date, getString(R.string.agent_count_chart_products)));
            max = max > Float.parseFloat(list.get(i).getQUERY_COUNT()) ? max : Float.parseFloat(list.get(i).getQUERY_COUNT());
        }

        //        entries.add(new Entry(0, 15));
        setChartData(entries, m, max);
    }

    @OnClick({R.id.time_7, R.id.time_30, R.id.time_90})
    public void onViewClicked(View view) {
        if (type == ProxyMainActivity.AGENTPRODUCT && mRefreshLayout.isRefreshing()) {
            return;
        }
        switch (view.getId()) {
            case R.id.time_7:
                mTimeType = 0;
                setTimeTvSelect(true, false, false);
                break;
            case R.id.time_30:
                mTimeType = 1;
                setTimeTvSelect(false, true, false);
                break;
            case R.id.time_90:
                mTimeType = 2;
                setTimeTvSelect(false, false, true);
                break;
        }
        initChartData();
    }

    /**
     * 改变7天 30天 90天 TV的select状态
     *
     * @param mTime7State  7天
     * @param mTime30State 30天
     * @param mTime90State 90天
     */
    private void setTimeTvSelect(boolean mTime7State, boolean mTime30State, boolean mTime90State) {
        mTime7.setSelected(mTime7State);
        mTime30.setSelected(mTime30State);
        mTime90.setSelected(mTime90State);
    }


    @OnClick({R.id.home_proxy_people, R.id.home_proxy_product, R.id.home_proxy_pay_count})
    public void onViewClick(View view) {
        if (type == 1) { //代理人数
            switch (view.getId()) {
                case R.id.home_proxy_people:
                    mUnderAgentType = 0;
                    setUnderAgentTvSelect(true, false, false);
                    break;
                case R.id.home_proxy_product:
                    mUnderAgentType = 1;
                    setUnderAgentTvSelect(false, true, false);
                    break;
                case R.id.home_proxy_pay_count:
                    mUnderAgentType = 2;
                    setUnderAgentTvSelect(false, false, true);
                    break;
            }
            mTimeType = 0;
            setTimeTvSelect(true, false, false);
            getUnderAgentData();
            return;
        }
        if (type == 3) { //付费次数
            switch (view.getId()) {
                case R.id.home_proxy_people:
                    mUnderAgentType = 0;
                    setUnderAgentTvSelect(true, false, false);
                    break;
                case R.id.home_proxy_product:
                    mUnderAgentType = 1;
                    setUnderAgentTvSelect(false, true, false);
                    break;
                case R.id.home_proxy_pay_count:
                    mUnderAgentType = 2;
                    setUnderAgentTvSelect(false, false, true);
                    break;
            }
            mTimeType = 0;
            setTimeTvSelect(true, false, false);
            getPayCountData();
            return;
        }


    }

    /**
     * 改变二级代理  三级代理 直属会员的选择状态
     *
     * @param mTime7State  7天
     * @param mTime30State 30天
     * @param mTime90State 90天
     */
    private void setUnderAgentTvSelect(boolean mTime7State, boolean mTime30State, boolean mTime90State) {
        mPeople.setSelected(mTime7State);
        mProduct.setSelected(mTime30State);
        mPayCount.setSelected(mTime90State);
    }
}
