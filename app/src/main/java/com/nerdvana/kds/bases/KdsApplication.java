package com.nerdvana.kds.bases;

import android.app.Application;

import com.nerdvana.kds.custom.SharedPreferenceManager;

public class KdsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new SharedPreferenceManager(this);
    }
}
