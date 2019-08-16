package com.example.phone_9.myservices;


import android.app.Service;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {


    public MyService(){}

    @Override
    public void onCreate() {
        super.onCreate();  //app 被销毁时和第一次打看会调用一次
        Log.v("MyService","========================oncreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v("MyService","========================onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("MyService","========================onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
}
