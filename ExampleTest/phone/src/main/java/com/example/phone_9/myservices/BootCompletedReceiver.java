package com.example.phone_9.myservices;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

/**
 * @author admin
 * @time 2019/7/23 14
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("BootCompletedReceiver","============"+intent.getAction());
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.v("BootCompletedReceiver","========手机开机");
            Intent sintent=new Intent();
           // sintent.setAction("MyService");
           // sintent.setPackage("com.example.phone_9");
          //  context.startService(sintent);


          //  sintent.setComponent(new ComponentName("com.sunmi.extprinterservic", "com.sunmi.extprinterservic.TestService"));
          //  context.startService(sintent);

        }

    }

}
