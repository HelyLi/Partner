package com.hhly.partner.presentation.utils;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by dell on 2017/4/13.
 */

public class ActivityUtil {
    private ActivityUtil() {
        throw new RuntimeException("con't instanse");
    }

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int containerId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commit();
    }

    public static void addFragment(FragmentManager fragmentManager, DialogFragment dialog) {
        FragmentTransaction mFragTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag("dialogFragment");
        if (fragment != null) {
            mFragTransaction.remove(fragment);
        }
        dialog.show(mFragTransaction, "dialogFragment");
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int containerId, boolean isAddToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        if (isAddToBackStack) {
            transaction.addToBackStack("fragment");
        }
        transaction.commitAllowingStateLoss();
    }
}
