package com.tom.hunter;

import android.app.Application;

/**
 * Created by txu1 on 9/8/2016.
 */
public class JobApplication extends Application {

    private static JobApplication mInstance;

    public static JobApplication getInstance() {
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
