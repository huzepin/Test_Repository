package com.tencent.wxpayface;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/5/2.
 */

public class WxPayFace {

    public static final String TAG = "WxPayFace";
    public static final String KEY_PROXY = "proxy";

    private static WxPayFace instance = null;

    private boolean mIsServiceConnected = false;
    private boolean mIsServiceConnecting = false;
    private IWxPayFaceAIDL service;
    private IWxPayFaceCallbackAIDL mInitCallback;
    private Context mContext;
    private static Map PROXY_MAP = null;

    public static WxPayFace getInstance() {

        synchronized (WxPayFace.class) {
            if (instance == null) {
                instance = new WxPayFace();
            }
        }

        return instance;
    }

    public void initWxpayface(Context cxt,final IWxPayFaceCallbackAIDL wxpayfaceCallBack){

        PROXY_MAP = null;
        Intent intent=new Intent("com.tencent.faceservice");
        Intent eintent = new Intent(createExplicitFromImplicitIntent(cxt,intent));
        cxt.bindService(eintent, conn, Context.BIND_AUTO_CREATE);

        mInitCallback = wxpayfaceCallBack;
        mContext = cxt;
        mIsServiceConnecting = true;

    }

    public void initWxpayface(Context cxt,Map info,final IWxPayFaceCallbackAIDL wxpayfaceCallBack){

        PROXY_MAP = info;
        Intent intent=new Intent("com.tencent.faceservice");
        Intent eintent = new Intent(createExplicitFromImplicitIntent(cxt,intent));
        eintent.putExtra(KEY_PROXY,(Serializable)info);
        cxt.bindService(eintent, conn, Context.BIND_AUTO_CREATE);

        mInitCallback = wxpayfaceCallBack;
        mContext = cxt;
        mIsServiceConnecting = true;

    }

    public void getWxpayfaceRawdata(final IWxPayFaceCallbackAIDL wxpayfaceCallBack){
        try{
            if(!mIsServiceConnected){
                tryReconnectService(wxpayfaceCallBack);
                return;
            }
            service.getWxpayfaceRawdata(wxpayfaceCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getWxpayfaceCode(final Map bean, final IWxPayFaceCallbackAIDL wxpayfaceCallBack){
        Log.d(TAG,"client| getWxpayfaceCode");
        try{
            if(!mIsServiceConnected){
                tryReconnectService(wxpayfaceCallBack);
                return;
            }
            service.getWxpayfaceCode(bean,wxpayfaceCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void updateWxpayfacePayResult(final Map bean, final IWxPayFaceCallbackAIDL wxpayfaceCallBack){
        Log.d(TAG,"client| speed | updateWxpayfacePayResult");
        try{
            if(!mIsServiceConnected){
                tryReconnectService(wxpayfaceCallBack);
                return;
            }
            service.updateWxpayfacePayResult(bean,wxpayfaceCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void reportInfo(Map info,final IWxPayFaceCallbackAIDL wxpayfaceCallBack){
        try{
            service.reportInfo(info,wxpayfaceCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void reportOrder(Map info,final IWxPayFaceCallbackAIDL wxpayfaceCallBack){
        try{
            service.reportOrder(info,wxpayfaceCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getWxpayfaceUserInfo(final Map bean, final IWxPayFaceCallbackAIDL wxpayfaceCallBack){
        try{
            service.getWxpayfaceUserInfo(bean,wxpayfaceCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void stopWxpayface(final Map bean, final IWxPayFaceCallbackAIDL wxpayfaceCallBack){
        try{
            service.stopWxpayface(bean,wxpayfaceCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void updateWxpayfaceBannerState(final Map bean, final IWxPayFaceCallbackAIDL wxpayfaceCallBack){
        try{
            service.updateWxpayfaceBannerState(bean, wxpayfaceCallBack);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void releaseWxpayface(Context cxt){
        Log.d(TAG,"releaseWxpayface");
        try{
            cxt.unbindService(conn);
            mIsServiceConnected = false;
            mIsServiceConnecting = false;
            mInitCallback = null;
            mContext = null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mIsServiceConnected = false;
            mIsServiceConnecting = false;
            Log.d(TAG,"onServiceDisconnected");
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mIsServiceConnected = true;
            mIsServiceConnecting = false;
            Log.d(TAG,"onServiceConnected");
            service= IWxPayFaceAIDL.Stub.asInterface(binder);
            try{
                service.initWxpayface(mInitCallback);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    };

    private void tryReconnectService(IWxPayFaceCallbackAIDL callbackAIDL){
        try{

            HashMap map = new HashMap();
            map.put("return_code","FAIL");
            map.put("return_msg","Service Unconnected");
            callbackAIDL.response(map);

            if(mIsServiceConnecting){
                return;
            }
            mIsServiceConnecting = true;
            if(PROXY_MAP != null){
                initWxpayface(mContext,PROXY_MAP,mInitCallback);
            }else{
                initWxpayface(mContext,mInitCallback);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }

}


