package com.example.phone_9;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.phone_9.utils.EventHandler;
import com.example.phone_9.bean.DownloadInfo;


/**
 * Created by zs
 * Date：2018年 09月 12日
 * Time：17:17
 * —————————————————————————————————————
 * About:单个下载
 * —————————————————————————————————————
 */
public class SingleActivity extends AppCompatActivity implements EventHandler
{

    private ProgressBar main_progress;
    private Button main_btn_down;
    private Button main_btn_pause;
    private Button main_btn_cancel;
    private DownloadInfo downloadInfo;
    private SingleActivity own ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
     //   EventBus.getDefault().register(this);
        main_progress = findViewById(R.id.main_progress);
        main_btn_down = findViewById(R.id.main_btn_down);
        main_btn_pause = findViewById(R.id.main_btn_pause);
        main_btn_cancel = findViewById(R.id.main_btn_cancel);
        own = this;

        main_btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.getInstance().download(Constant.URL_1,10,own);
            }
        });

        main_btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.getInstance().pauseDownload(Constant.URL_1,11,own);
            }
        });

        main_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.getInstance().cancelDownload(downloadInfo,12,own);
            }
        });


        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onEvent(int tag, Object value) {
        if (tag == 10) {
            downloadInfo = (DownloadInfo) value;
            if (downloadInfo == null) return;
            if (downloadInfo.getUrl() != Constant.URL_1) {
                return;
            }
            if (downloadInfo.getTotal() == 0) {
                main_progress.setProgress(0);
            } else if (DownloadInfo.DOWNLOAD.equals(downloadInfo.getDownloadStatus())){
                final float progress = downloadInfo.getProgress() * main_progress.getMax() / downloadInfo.getTotal();
                Log.d("SingleActivity","==================="+progress);
                main_progress.setProgress((int) progress);

            }else if (DownloadInfo.DOWNLOAD_OVER.equals(downloadInfo.getDownloadStatus())){
                main_progress.setProgress(main_progress.getMax());
                Log.d("SingleActivity","===================");
                Toast.makeText(this,"下载完成",Toast.LENGTH_SHORT).show();
            }else if (DownloadInfo.DOWNLOAD_ERROR.equals(downloadInfo.getDownloadStatus())){
                Toast.makeText(this,"下载出错",Toast.LENGTH_SHORT).show();
            }
        }else if (tag == 11){  //暂停
            Toast.makeText(this,"下载暂停",Toast.LENGTH_SHORT).show();
        }else if (tag == 12){ //取消
            main_progress.setProgress(0);
            Toast.makeText(this,"下载取消",Toast.LENGTH_SHORT).show();
        }
    }


   // @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(DownloadInfo info){
        if (info.getUrl() != Constant.URL_1){
          return;
        }
        if (DownloadInfo.DOWNLOAD.equals(info.getDownloadStatus())){
            downloadInfo = info;
            if (info.getTotal() == 0){
                main_progress.setProgress(0);
            }else{
                float progress = info.getProgress() * main_progress.getMax() / info.getTotal();
                main_progress.setProgress((int) progress);
            }

        }else if (DownloadInfo.DOWNLOAD_OVER.equals(info.getDownloadStatus())){

            main_progress.setProgress(main_progress.getMax());

        }else if (DownloadInfo.DOWNLOAD_PAUSE.equals(info.getDownloadStatus())){

            Toast.makeText(this,"下载暂停",Toast.LENGTH_SHORT).show();

        }else if (DownloadInfo.DOWNLOAD_CANCEL.equals(info.getDownloadStatus())){

            main_progress.setProgress(0);
            Toast.makeText(this,"下载取消",Toast.LENGTH_SHORT).show();

        }else if (DownloadInfo.DOWNLOAD_ERROR.equals(info.getDownloadStatus())){

            Toast.makeText(this,"下载出错",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // EventBus.getDefault().unregister(this);
    }

}
