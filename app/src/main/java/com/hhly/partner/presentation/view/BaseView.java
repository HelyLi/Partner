package com.hhly.partner.presentation.view;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * description :
 * Created by Flynn
 * 2017/4/11.
 */
public interface BaseView<T>  {
    void setPresenter(T presenter);

    Context getContext();

    <K> LifecycleTransformer<K> bindToLife();
}
