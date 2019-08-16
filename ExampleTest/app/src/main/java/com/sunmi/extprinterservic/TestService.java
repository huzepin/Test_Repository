package com.sunmi.extprinterservic;

import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.text.Format;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @time 2019/7/23 14
 */
public class TestService extends Service {
    public TestService(){}
    private boolean isWorking = false;
    private ScreenBroadcastReceiver receiver;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new ScreenBroadcastReceiver();
      //  if (receiver != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_USER_PRESENT);
            this.registerReceiver(receiver, filter);
       // }

        sharedPreferences = getSharedPreferences("Run_time",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Log.v("MyService","======================1==oncreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v("MyService","======================1==onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("MyService","======================1==onStartCommand");
         if (isWorking) return super.onStartCommand(intent, flags, startId);
         isWorking = true;
        if (mHandler != null)
        mHandler.postDelayed(runnable, 100);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregister();
    }

    Handler mHandler = new Handler();
       Runnable runnable = new Runnable() {
                  @Override
                  public void run() {
                          String packageName = getForegroundApp();
                          long time =System.currentTimeMillis();
                          long save_time = sharedPreferences.getLong("update_time",0);
                          if (save_time==0){
                              editor.putLong("update_time",time);
                              editor.commit();
                          }else if(save_time > 0) {
                              long in = (time - save_time) / 60000;  // 分钟
                              if (in > 5){
                                  editor.putLong("update_time",time);
                                  editor.commit();
                              }
                          }

                          Log.v("MyService", "======="+time+"======我是一个烦人的定时器" + packageName);
                          if (sharedPreferences != null && editor != null){
                              editor.putLong(packageName,sharedPreferences.getLong(packageName,0)+1);
                              editor.commit();
                          }

                          //每隔1s循环执行run方法
                      if (mHandler != null)
                          mHandler.postDelayed(this, 1000);

                   }
                };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getForegroundApp() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        long ts = System.currentTimeMillis();
        //第一个参数： 按照时间间隔来查询  第二个：开始时间 第三个：截止时间
        //通过给定的开始与结束时间  INTERVAL_BEST是按照最合适的时间间隔类型
        //还可以有：INTERVAL_DAILY  WEEKLY MONTHLY YEARLY
        List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, 0, ts);
        //返回结果中的UsageStats的官方解释是：包含特定时间范围内应用程序包的使用统计资料。
        if (queryUsageStats == null || queryUsageStats.isEmpty()) {
            return null;
        }

        UsageStats recentStats = null;
        for (UsageStats usageStats : queryUsageStats) {
            if (recentStats == null ||
                    recentStats.getLastTimeUsed() < usageStats.getLastTimeUsed()) {
                recentStats = usageStats;
            }
        }
        return recentStats.getPackageName();
    }

    public void unregister() {
        if (receiver != null) {
            this.unregisterReceiver(receiver);
            receiver = null;
        }
    }
    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
              //  if (Intent.ACTION_SCREEN_ON.equals(action)) {
                //    Log.v("MonitorReceiver","=========亮屏");
                if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                    Log.v("MonitorReceiver","=========息屏");
                  //   unregister();
                  //  mHandler.removeCallbacks(runnable);
                  //  mHandler = null;
                  //  try {
                  //      Thread.sleep(1000);
                  //   } catch (InterruptedException e) {
                  //      e.printStackTrace();
                  //   }
                  //   onDestroy();
                  //   Intent sintent= new Intent();
                  //   sintent.setComponent(new ComponentName("com.sunmi.extprinterservic", "com.sunmi.extprinterservic.TestService"));
                    // stopService(sintent);
                    // onDestroy();
                } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                    isWorking = false;
                    Log.v("MonitorReceiver","=========亮屏11");
                }
            }
        }
    }
}
