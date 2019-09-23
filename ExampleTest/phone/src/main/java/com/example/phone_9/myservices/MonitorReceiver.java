package com.example.phone_9.myservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author admin
 * @time 2019/7/23 09
 */
public class MonitorReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("MonitorReceiver","============"+intent.getAction());
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.v("MonitorReceiver","=====phone===手机开机");
            Intent sintent=new Intent();
            sintent.setAction("MyService");
            sintent.setPackage("com.example.phone_9");
           // context.startService(sintent);


        }
        if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            Log.v("MonitorReceiver","=========解锁");
            Intent sintent=new Intent();
            sintent.setAction("MyService");
            sintent.setPackage("com.example.phone_9");
            context.startService(sintent);
        }
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())){
            Log.v("MonitorReceiver","=========息屏");
            Intent sintent=new Intent();
            sintent.setAction("MyService");
            sintent.setPackage("com.example.phone_9");
            context.stopService(sintent);
        }

        if (Intent.ACTION_MEDIA_MOUNTED.equals(intent.getAction())){
            Log.v("MonitorReceiver","========扫描sd卡");
        }

        if (intent.getAction().equals(Intent.ACTION_SHUTDOWN)) {
            Log.i("MonitorReceiver", "========关机了");
        }
    }
}
