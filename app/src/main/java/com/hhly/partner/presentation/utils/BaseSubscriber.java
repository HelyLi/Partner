package com.hhly.partner.presentation.utils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by Flynn
 * 2017/4/14
 */

public abstract class BaseSubscriber<T> implements Subscriber<T> {

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onComplete() {

    }

}
