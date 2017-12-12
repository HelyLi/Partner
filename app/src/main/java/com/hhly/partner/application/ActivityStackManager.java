package com.hhly.partner.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.util.LinkedList;

/**
 * ActivityStackManager
 * Created by dell on 2017/5/17.
 */

public class ActivityStackManager implements Application.ActivityLifecycleCallbacks {
    private static ActivityStackManager manager = new ActivityStackManager();

    public static ActivityStackManager get() {
        return manager;
    }

    private LinkedList<Activity> mActivityStack = new LinkedList<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (!mActivityStack.contains(activity)) {
            mActivityStack.add(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (mActivityStack.contains(activity)) {
            mActivityStack.remove(activity);
        }
    }

    public Activity getTopActivity() {
        Activity activity = null;
        if (!mActivityStack.isEmpty()) {
            activity = mActivityStack.getLast();
        }
        return activity;
    }

    public void registerSelf(Context context) {
        Application application = (Application) context.getApplicationContext();
        application.registerActivityLifecycleCallbacks(ActivityStackManager.get());
    }
}
