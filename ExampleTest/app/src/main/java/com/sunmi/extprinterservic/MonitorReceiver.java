package com.sunmi.extprinterservic;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
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
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.v("MonitorReceiver_app","======app==手机开机了");  //com.sunmi.extprinterservic
            Intent sintent=new Intent();
            sintent.setComponent(new ComponentName("com.sunmi.extprinterservic", "com.sunmi.extprinterservic.TestService"));
            context.startService(sintent);
        }
        if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            Log.v("MonitorReceiver","=========解锁");
          //  Intent sintent=new Intent();
          //  sintent.setComponent(new ComponentName("com.sunmi.extprinterservic", "com.sunmi.extprinterservic.TestService"));
          //  context.startService(sintent);
        }

        if (Intent.ACTION_MEDIA_MOUNTED.equals(intent.getAction())){
            Log.v("MonitorReceiver","========扫描sd卡");
        }
        if (intent.getAction().equals(Intent.ACTION_SHUTDOWN)) {
            Log.i("MonitorReceiver", "========关机了");
        }
    }
}
