package com.hhly.partner.presentation.view.extension;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetBannerListResp;
import com.hhly.partner.presentation.utils.GlideUtils;

import java.util.ArrayList;

/**
 * 自定义推广页添加bannerAdapter
 * Created by dell on 2017/5/5.
 */

public class AddExtensionBannerAdapter extends BaseQuickAdapter<GetBannerListResp.DataBeanX.DataBean, BaseViewHolder> {
    public AddExtensionBannerAdapter() {
        super(R.layout.add_extension_banner_item_layout, new ArrayList<GetBannerListResp.DataBeanX.DataBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetBannerListResp.DataBeanX.DataBean item) {
        GlideUtils.loadImageViewLoading(mContext, item.getICON_URL(), (ImageView) helper.getView(R.id.banner_img)
                , R.drawable.ic_action_banner_loading, R.drawable.ic_action_banner_loading);
        ImageView stateImg = helper.getView(R.id.state_check_img);
        stateImg.setSelected(item.isSelected());
    }

    /**
     * 获取被选中的item数量
     *
     * @return 被选中的item数量
     */
    public int getSelectedCount() {
        int count = 0;
        for (GetBannerListResp.DataBeanX.DataBean bean : getData()) {
            if (bean.isSelected()) {
                count++;
            }
        }
        return count;
    }
}
