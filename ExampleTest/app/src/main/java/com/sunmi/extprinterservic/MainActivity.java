package com.sunmi.extprinterservic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.wxpayface.IWxPayfaceCallback;
import com.tencent.wxpayface.WxPayFace;
import com.tencent.wxpayface.WxfacePayCommonCode;

import java.util.HashMap;
import java.util.Map;

import static com.tencent.wxpayface.WxPayFace.TAG;


public class MainActivity extends Activity {
    public static final String RETURN_CODE = "return_code";
    public static final String RETURN_SUCCESS = "SUCCESS";
    public static final String RETURN_FAILE = "SUCCESS";
    public static final String RETURN_MSG = "return_msg";
    Button bt_into_adapter,bt_into_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_into_adapter = findViewById(R.id.btn_animator);
        bt_into_animation =findViewById(R.id.btn_adapter);


        bt_into_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AnimatorSampleActivity.class));
            }
        });

        bt_into_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> m1 = new HashMap<>();
//                m1.put("ip", "192.168.1.1"); //若没有代理,则不需要此行
//                m1.put("port", "8888");//若没有代理,则不需要此行
                WxPayFace.getInstance().initWxpayface(MainActivity.this, m1, new IWxPayfaceCallback() {
                    @Override
                    public void response(Map info) throws RemoteException {
                        if (!isSuccessInfo(info)) {
                            return;
                        }
                        showToast("初始化完成");
                    }
                });
               // startActivity(new Intent(MainActivity.this,AdapterSampleActivity.class));
            }
        });
    }

    private boolean isSuccessInfo(Map info) {
        if (info == null) {
            showToast("调用返回为空, 请查看日志");
            new RuntimeException("调用返回为空").printStackTrace();
            return false;
        }
        String code = (String)info.get(RETURN_CODE);
        String msg = (String)info.get(RETURN_MSG);
        Log.d(TAG, "response | getWxpayfaceRawdata " + code + " | " + msg);
        if (code == null || !code.equals(WxfacePayCommonCode.VAL_RSP_PARAMS_SUCCESS)) {
            showToast("调用返回非成功信息, 请查看日志");
            new RuntimeException("调用返回非成功信息: " + msg).printStackTrace();
            return false;
        }
        Log.d(TAG, "调用返回成功");
        return true;
    }

    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }

}
