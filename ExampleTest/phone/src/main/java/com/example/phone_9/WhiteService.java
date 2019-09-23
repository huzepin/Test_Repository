package com.example.phone_9;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * @author admin
 * @time 2019/9/19 14
 */
public class WhiteService extends Service {

    static public boolean isPowerKeyPressed = false;
    private final static String TAG = WhiteService.class.getSimpleName();
    private final static int FOREGROUND_ID = 1000;
    private Notification mNotification;
    /**
     * 参考:     * 1 https://blog.csdn.net/q445697127/article/details/8432513     * 2 https://blog.csdn.net/lfdfhl/article/details/9903693
     */

    private PowerKeyObserver mPowerKeyObserver;


    private void init() {
        //////////////////////////////////////////
       mPowerKeyObserver = new PowerKeyObserver(this);
        mPowerKeyObserver.setPowerKeyListener(new PowerKeyObserver.OnPowerKeyListener() {
            @Override
            public void onPowerOffKeyPressed() {
                Log.i(TAG, "----> 按下电源键_关");

                isPowerKeyPressed = true;
            }

            @Override
            public void onPowerOnKeyPressed() {
                Log.i(TAG, "----> 按下电源键_开");
            }
        });
        mPowerKeyObserver.startListen();

    }

    public WhiteService() {

    }

    @Override
    public IBinder onBind(Intent intent) {        //return null;
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "WhiteService->onCreate");
        super.onCreate();
        init();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "WhiteService->onDestroy");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            //if(null !=mNotification)
        {
            stopForeground(Service.STOP_FOREGROUND_REMOVE);
        }

        mPowerKeyObserver.stopListen();

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null != mNotification) {
            Log.i(TAG, "WhiteService->onStartCommand->Notification exists");
            return super.onStartCommand(intent, flags, startId);
        }
        Log.i(TAG, "WhiteService->onStartCommand->Create Notification");
        //NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);
            // Configure the notification channel.notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Foreground");
        builder.setContentText("Text shown on notification bar");
        builder.setContentInfo("Content Info");
        builder.setWhen(System.currentTimeMillis());
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        mNotification = builder.build();
        startForeground(FOREGROUND_ID, mNotification);
        return super.onStartCommand(intent, flags, startId);
    }

}
