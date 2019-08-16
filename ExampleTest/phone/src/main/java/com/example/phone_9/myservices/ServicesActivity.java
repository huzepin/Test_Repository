package com.example.phone_9.myservices;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.phone_9.R;


public class ServicesActivity extends AppCompatActivity implements View.OnClickListener {


    private Button bt_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_services);
        bt_start = findViewById(R.id.bt_start);
        bt_start.setOnClickListener(this);
        requestPermission();

    }

    @Override
    public void onClick(View v) {
        Intent sintent=new Intent();
        //sintent.setAction("MyService");
        //sintent.setPackage("com.example.phone_9");
        //startService(sintent);
       // Intent intent = new Intent(Intent.ACTION_VIEW);
       // String packageName = "com.sunmi.extprinterservic";
       // String className = "com.sunmi.extprinterservic.TestService";
       // intent.setClassName(packageName, className);

       sintent.setComponent(new ComponentName("com.sunmi.extprinterservic", "com.sunmi.extprinterservic.TestService"));
       startService(sintent);
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
