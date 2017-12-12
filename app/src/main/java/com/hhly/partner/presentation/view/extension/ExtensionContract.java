package com.hhly.partner.presentation.view.extension;

import com.hhly.partner.data.net.protocol.game.GetBannerListByUseridResp;
import com.hhly.partner.data.net.protocol.game.GetBannerListResp;
import com.hhly.partner.data.net.protocol.game.GetGameCustomizationResp;
import com.hhly.partner.data.net.protocol.user.MobileResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/5/3
 */

public interface ExtensionContract {

    interface Presenter extends BasePresenter {
        void getMobile();

        void getUserId();
    }

    interface View extends BaseView<Presenter> {

        void getMobileSuccess(List<MobileResp.DataBeanX.DataBean> list);

        void getMobileFailure(String msg);

        void getUserIdSuccess(String userId);

        void getUserIdFailure(String msg);
    }

    interface BaseCustomExtensionPresenter extends BasePresenter {
        //删除自定义推广页游戏
        void deleteGameCustomization(int modeId, int gameId, int position);

        //获取自定义推广页的游戏
        void getGameCustomization(int gameType);
    }

    interface BaseCustomExtensionView extends BaseView<BaseCustomExtensionPresenter> {
        void deleteGameCustomizationSuccess(int position);

        void deleteGameCustomizationFailure(String msg);

        void getGameCustomizationSuccess(List<GetGameCustomizationResp.DataBeanX.DataBean> list);

        void getGameCustomizationFailure(String msg);
    }

    interface CustomExtensionPresenter extends BaseCustomExtensionPresenter {
        void getBannerListByUserId();
    }

    interface CustomExtensionView extends BaseCustomExtensionView {
        void getBannerListSuccess(List<GetBannerListByUseridResp.DataBeanX.DataBean> list);

        void getBannerListFail(String msg);

    }


    interface AddBannerPresenter extends BasePresenter {
        void getBannerList(int page);

        void addBanner(String gameId);
    }

    interface AddBannerView extends BaseView<AddBannerPresenter> {
        void getBannerListSuccess(List<GetBannerListResp.DataBeanX.DataBean> list);

        void getBannerListFail(String msg);

        void addBannerSuccess();

        void addBannerFail(String msg);
    }
}
