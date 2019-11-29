package com.sunmi.extprinterservic;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * @author admin
 * @time 2019/7/19 10
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApp","MyApp_oncreate");
    }



    //只有再模拟器上有用
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("MyApp","MyApp_destory");
    }
}
