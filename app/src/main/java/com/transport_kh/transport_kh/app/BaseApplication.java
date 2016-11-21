package com.transport_kh.transport_kh.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.backendless.Backendless;
import com.transport_kh.transport_kh.R;

/**
 * Created by Dmytro on 20.11.2016.
 */
public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.initApp(this, getString(R.string.APP_ID), getString(R.string.SECRET_KEY), getString(R.string.appVersion_Backendless));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
