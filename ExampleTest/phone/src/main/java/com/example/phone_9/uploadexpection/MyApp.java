package com.example.phone_9.uploadexpection;

import android.app.Application;

import com.example.phone_9.BuildConfig;

import java.security.PublicKey;

/**
 * @author admin
 * @time 2019/7/23 16
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(CrashHandlerManage.getInstance(this));
    }



}
