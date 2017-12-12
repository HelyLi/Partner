package com.hhly.partner.presentation.view.extension;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetGameCustomizationResp;
import com.hhly.partner.presentation.view.common.RecyclerViewGridDivide;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义推广页adapter
 * Created by dell on 2017/5/3.
 */

public class CustomExtensionAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final int VIEW_TYPE_GRID = 101;//每日签到、积分任务....
    private static final int VIEW_TYPE_HOT = 102;//热门游戏
    private static final int VIEW_TYPE_REC = 103;//精品推荐

    private final RecyclerView.RecycledViewPool mRecycledViewPool;
    private SparseArray<RecyclerView.LayoutManager> mLayoutManagerSparseArray;
    private SparseArray<BaseQuickAdapter> mAdapterArray;
    private Context mContext;
    private CustomExtensionHotAdapter.OnClickCallBack mCallBack;

    public CustomExtensionAdapter(Context context) {
        super(new ArrayList<MultiItemEntity>());
        addItemType(VIEW_TYPE_GRID, R.layout.inside_recycler_view);
        addItemType(VIEW_TYPE_HOT, R.layout.custom_extension_hot_section);
        addItemType(VIEW_TYPE_REC, R.layout.custom_extension_rec_section);
        mContext = context;
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        mLayoutManagerSparseArray = new SparseArray<>();
        mAdapterArray = new SparseArray<>();
    }

    public void setCallBack(CustomExtensionHotAdapter.OnClickCallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setRecycledViewPool(mRecycledViewPool);
        initRecyclerViewByType(helper.getItemViewType(), recyclerView);
    }

    private void initRecyclerViewByType(int viewType, RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = mLayoutManagerSparseArray.get(viewType);
        if (layoutManager == null) {
            switch (viewType) {
                case VIEW_TYPE_GRID:
                    layoutManager = new GridLayoutManager(mContext, 2);
                    recyclerView.addItemDecoration(new RecyclerViewGridDivide(mContext, R.color.transparent, 6));
                    break;
                case VIEW_TYPE_HOT:
                    layoutManager = new GridLayoutManager(mContext, 4);
                    recyclerView.addItemDecoration(new RecyclerViewGridDivide(mContext, R.color.transparent, 10));
                    break;
                case VIEW_TYPE_REC:
                    layoutManager = new GridLayoutManager(mContext, 2);
                    recyclerView.addItemDecoration(new RecyclerViewGridDivide(mContext, R.color.transparent, 15));
                    break;
            }
            mLayoutManagerSparseArray.put(viewType, layoutManager);
        }

        BaseQuickAdapter adapter = initAdapterByType(viewType);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private BaseQuickAdapter initAdapterByType(int viewType) {
        BaseQuickAdapter adapter = mAdapterArray.get(viewType);
        if (adapter == null) {
            switch (viewType) {
                case VIEW_TYPE_GRID:
                    adapter = new CustomExtensionGridAdapter();
                    break;
                case VIEW_TYPE_HOT://热门游戏
                    adapter = new CustomExtensionHotAdapter();
                    ((CustomExtensionHotAdapter) adapter).setOnClickCallBack(mCallBack);
                    break;
                case VIEW_TYPE_REC://精品推荐
                    adapter = new CustomExtensionRecAdapter();
                    break;
            }
            mAdapterArray.put(viewType, adapter);
        }
        return adapter;
    }

    static class GridItem implements MultiItemEntity {

        @Override
        public int getItemType() {
            return VIEW_TYPE_GRID;
        }
    }

    static class HotGameItem implements MultiItemEntity {

        @Override
        public int getItemType() {
            return VIEW_TYPE_HOT;
        }
    }

    static class RecommendGameItem implements MultiItemEntity {
        @Override
        public int getItemType() {
            return VIEW_TYPE_REC;
        }
    }


    public void updateItems(List<GetGameCustomizationResp.DataBeanX.DataBean> list) {
        getData().clear();
        updateDefaultGridItems();
        updateHotGameItems(list);
        updateRecommendItems();
    }

    private void updateItemsByType(List<?> list, int type) {
        BaseQuickAdapter adapter = initAdapterByType(type);
        adapter.setNewData(list);
    }


    private void updateDefaultGridItems() {
        addData(new GridItem());
        List<ExtensionDefaultGridItem> defaultGridItems = new ArrayList<>();
        defaultGridItems.add(new ExtensionDefaultGridItem(R.drawable.ic_custom_sign_in));
        defaultGridItems.add(new ExtensionDefaultGridItem(R.drawable.ic_custom_integral_task));
        defaultGridItems.add(new ExtensionDefaultGridItem(R.drawable.ic_custom_activity_center));
        defaultGridItems.add(new ExtensionDefaultGridItem(R.drawable.ic_custom_partner));
        updateItemsByType(defaultGridItems, VIEW_TYPE_GRID);
    }

    /**
     * 更新热门游戏items
     *
     * @param list
     */
    private void updateHotGameItems(List<GetGameCustomizationResp.DataBeanX.DataBean> list) {
        addData(new HotGameItem());
        CustomExtensionHotAdapter hotAdapter = (CustomExtensionHotAdapter) initAdapterByType(VIEW_TYPE_HOT);
        List<CustomExtensionHotAdapter.GameAddedItem> gameAddedItems = new ArrayList<>();
        CustomExtensionHotAdapter.GameAddedItem gameAddedItem = null;
        for (GetGameCustomizationResp.DataBeanX.DataBean bean : list) {
            gameAddedItem = new CustomExtensionHotAdapter.GameAddedItem();
            gameAddedItem.setGameIconUrl(bean.getICON_URL());
            gameAddedItem.setGameName(bean.getNAME());
            gameAddedItem.setGameId(bean.getPROMOTION_GAME_ID());
            gameAddedItems.add(gameAddedItem);
        }
        hotAdapter.updateItems(gameAddedItems);
    }

    /**
     * 删除热门游戏的某个item
     *
     * @param position position
     */
    public void deleteHotGameItem(int position) {
        CustomExtensionHotAdapter adapter = (CustomExtensionHotAdapter) initAdapterByType(VIEW_TYPE_HOT);
        adapter.deleteAddedGameItem(position);
    }

    /**
     * 更新精品推荐items
     */
    private void updateRecommendItems() {
        addData(new RecommendGameItem());
        CustomExtensionRecAdapter recAdapter = (CustomExtensionRecAdapter) initAdapterByType(VIEW_TYPE_REC);
        recAdapter.updateItems();
    }
}
