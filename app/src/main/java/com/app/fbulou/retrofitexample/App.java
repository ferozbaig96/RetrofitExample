package com.app.fbulou.retrofitexample;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App Instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
    }

    public static Context getAppContext() {
        return Instance.getApplicationContext();
    }
}
