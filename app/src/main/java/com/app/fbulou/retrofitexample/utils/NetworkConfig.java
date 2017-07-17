package com.app.fbulou.retrofitexample.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.app.fbulou.retrofitexample.App;

public class NetworkConfig {

    private static NETWORK_TYPE networkType;

    private enum NETWORK_TYPE {
        WIFI, MOBILE, NOT_CONNECTED
    }

    private static void checkConnectivity() {
        Context context = App.getAppContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) { // connected to the internet
            switch (activeNetwork.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    networkType = NETWORK_TYPE.WIFI;
                    break;
                default:
                    networkType = NETWORK_TYPE.MOBILE;
                    break;
            }
        } else {
            networkType = NETWORK_TYPE.NOT_CONNECTED;
        }
    }

    public static boolean isConnectedToInternet() {
        checkConnectivity();
        return networkType != NETWORK_TYPE.NOT_CONNECTED;
    }
}
