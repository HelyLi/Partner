package com.hhly.partner.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ToastUtil;

public class NetworkConnectChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.isConnected()) {
//                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
//                        ToastUtil.showShort(context, R.string.partner_wifi_network_available);
//                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//                        ToastUtil.showShort(context, R.string.partner_mobile_network_available);
//                    }
                } else {
                    ToastUtil.showShort(context, R.string.partner_network_not_available);
                }
            } else {
                ToastUtil.showShort(context, R.string.partner_network_not_available);
            }
        }
    }

}
