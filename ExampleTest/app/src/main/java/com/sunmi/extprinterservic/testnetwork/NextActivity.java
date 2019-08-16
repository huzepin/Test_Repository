package com.sunmi.extprinterservic.testnetwork;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sunmi.extprinterservic.R;

import static com.sunmi.extprinterservic.testnetwork.NetWorkStatus.netWorkStatus;

/**
 * @author admin
 * @time 2019/7/19 10
 */
public class NextActivity extends Activity {

    private Button bt_NextWork;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);

        bt_NextWork= findViewById(R.id.bt_test_network);
        bt_NextWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (netWorkStatus){
                    Toast.makeText(NextActivity.this,"网络正常",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(NextActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Log.d("MyApp_NextWork","oncreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyApp_NextWork","onDestroy");
    }
}
