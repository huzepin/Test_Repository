package com.example.phone_9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.Objects;

/**
 * @author admin
 * @time 2019/9/19 14
 */
public class PowerKeyObserver {
    private String TAG_POWER = "PowerKey";
    private Context mContext;
    private IntentFilter mIntentFilter;
    private OnPowerKeyListener mOnPowerKeyListener;
    private PowerKeyBroadcastReceiver mPowerKeyBroadcastReceiver;

    public PowerKeyObserver(Context context) {
        this.mContext = context;
    }
    //注册广播接收者
    public void startListen() {
        mIntentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        mIntentFilter.addAction(Intent.ACTION_SCREEN_ON);
        mPowerKeyBroadcastReceiver = new PowerKeyBroadcastReceiver();
        mContext.registerReceiver(mPowerKeyBroadcastReceiver, mIntentFilter);
    }
    //取消广播接收者

    public void stopListen() {
        if (mPowerKeyBroadcastReceiver != null) {
            mContext.unregisterReceiver(mPowerKeyBroadcastReceiver);

        }
    }     // 对外暴露接口

    public void setPowerKeyListener(OnPowerKeyListener powerKeyListener) {
        mOnPowerKeyListener = powerKeyListener;
    }

    // 回调接口
    //
    public interface OnPowerKeyListener {
        void onPowerOffKeyPressed();
        void onPowerOnKeyPressed();
    }

    //广播接收者
    class PowerKeyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Objects.equals(action, Intent.ACTION_SCREEN_OFF)) {
                mOnPowerKeyListener.onPowerOffKeyPressed();
                //abortBroadcast();
                }else if (Objects.equals(action, Intent.ACTION_SCREEN_ON)){
                mOnPowerKeyListener.onPowerOnKeyPressed();
            }
        }
    }

}
