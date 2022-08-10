package com.eth.base.data_response.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.eth.base.utils.NetworkUtils;
import com.eth.base.utils.ToastUtils;

import java.util.Objects;

public class NetworkStateReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ConnectivityManager.CONNECTIVITY_ACTION)){
            if (!NetworkUtils.isConnected()){
                ToastUtils.showShortToast(context,"网络不给力");
            }
        }
    }
}
