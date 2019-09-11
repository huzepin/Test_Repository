package com.sunmi.extprinterservic.testnetwork;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.EventLog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.sunmi.extprinterservic.R;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author admin
 * @time 2019/7/19 09
 */
public class NetWorkStatus extends AppCompatActivity implements View.OnClickListener {

    private Button bt_test,bt_nest,bt_install;
    public static boolean netWorkStatus = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.networkstatus);
        bt_test = findViewById(R.id.bt_netstatus);
        bt_test.setOnClickListener(this);
        bt_nest = findViewById(R.id.bt_next);
        bt_nest.setOnClickListener(this);
        bt_install = findViewById(R.id.bt_install);
        bt_install.setOnClickListener(this);

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(connectionReceiver, intentFilter);

        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
//            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
////            //验证是否许可权限
////            for (String str : permissions) {
////                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
////                    //申请权限
////                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
////                }
////            }
         //   requestPower();
        }
    }


    public void requestPower() {
        //判断是否已经赋予权限
         if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
             //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
             if (ActivityCompat.shouldShowRequestPermissionRationale(this,  Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                 //这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限
                 } else {
                 // 申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1);
             }
         }
    }



    public void installapp(){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/123.apk";
        File apkFile=new File(path);
        if (!apkFile.exists()){return;}
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.w("MyApp_NetWork", "版本大于 N ，开始使用 fileProvider 进行安装");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                boolean haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            }else {
                  Uri packageUri = Uri.parse("package:com.sunmi.extprinterservic");
                  Intent intents = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageUri);
            }

            Uri contentUri = FileProvider.getUriForFile(
                    getApplicationContext()
                    , "com.sunmi.extprinterservic.fileprovider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.w("MyApp_NetWork", "正常进行安装");
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        this.startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.bt_netstatus:
                 if (netWorkStatus){
                     Toast.makeText(NetWorkStatus.this,"网络正常",Toast.LENGTH_SHORT).show();
                 }else {
                     Toast.makeText(NetWorkStatus.this,"网络错误",Toast.LENGTH_SHORT).show();
                 }
                break;
            case R.id.bt_next:
                startActivity(new Intent(NetWorkStatus.this,NextActivity.class));
                break;
            case R.id.bt_install:
                installapp();
                break;
        }
    }


    BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                netWorkStatus = false;
            }else {
                netWorkStatus = true;
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(connectionReceiver);
        Log.d("MyApp_NetWork","ondestory");
    }



    private long lastBackTime = 0;
    private long currentBackTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //捕获返回键按下的事件
        if(keyCode == KeyEvent.KEYCODE_BACK){
            currentBackTime = System.currentTimeMillis();
            if(currentBackTime - lastBackTime > 2 * 1000){
                Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                lastBackTime = currentBackTime;
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
