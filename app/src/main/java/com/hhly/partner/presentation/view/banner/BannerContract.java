package com.hhly.partner.presentation.view.banner;


import java.util.List;

/**
 * Created by Simon on 16/9/6.
 */
public interface BannerContract {
    
    interface View<T>  {
        void showError(String msg);
        void updateBanner(List<T> bannerData);
    }
}
