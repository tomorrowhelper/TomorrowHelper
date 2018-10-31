package com.example.ymeng.tomorrowhelper.app;

import android.app.Application;

/**
 * Author:YMeng
 * Time:2018/10/30  10:48
 * This is App
 */
public class App extends Application {
    private static App instance;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
