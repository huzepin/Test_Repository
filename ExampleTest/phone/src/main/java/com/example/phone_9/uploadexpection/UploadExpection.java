package com.example.phone_9.uploadexpection;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.example.phone_9.R;

/**
 * @author admin
 * @time 2019/7/23 16
 */
public class UploadExpection extends Activity implements View.OnClickListener {

    private Button bt_test ;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadexpection);
        requestPermission();
        bt_test = findViewById(R.id.bt_test);
        bt_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      String string = null;
       char[] chars = string.toCharArray();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                int s = 10 / i;
            }
        });
        thread.start();

    }

    private static final int PERMISSION_READ_EXTERNAL_STORAGE = 101;
    private static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 102;
    private static final int PERMISSION_CAMERA = 103;
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_BOOT_COMPLETED) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_BOOT_COMPLETED}, PERMISSION_CAMERA);
        }
    }
}
