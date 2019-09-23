package com.example.phone_9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author admin
 * @time 2019/9/19 15
 */
public class disPlayActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("WhiteService","===============");
        Intent intent = new Intent(this, WhiteService.class);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }
}
