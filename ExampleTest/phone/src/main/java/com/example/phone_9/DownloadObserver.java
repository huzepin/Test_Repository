package com.example.phone_9;

import android.util.Log;

import com.example.phone_9.utils.EventHandler;
import com.example.phone_9.bean.DownloadInfo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zs
 * Date：2018年 09月 12日
 * Time：13:50
 * —————————————————————————————————————
 * About: 观察者
 * —————————————————————————————————————
 */
public class DownloadObserver implements Observer<DownloadInfo> {

    public Disposable d;//可以用于取消注册的监听者
    public DownloadInfo downloadInfo;
    public int type;
    public EventHandler handler;

    public DownloadObserver(int type, EventHandler eventHandler) {
        this.type = type;
        this.handler = eventHandler;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(DownloadInfo value) {
        this.downloadInfo = value;
        downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD);
       // EventBus.getDefault().post(downloadInfo);
        handler.onEvent(type,downloadInfo);
    }

    @Override
    public void onError(Throwable e) {
        Log.d("My_Log","onError");
        if (DownloadManager.getInstance().getDownloadUrl(downloadInfo.getUrl())){
            DownloadManager.getInstance().pauseDownload(downloadInfo.getUrl());
            downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD_ERROR);
          //  EventBus.getDefault().post(downloadInfo);
            handler.onEvent(type,downloadInfo);
            Log.d("DownloadObserver","downing________DOWNLOAD_ERROR");
        }else{
            downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD_PAUSE);
            handler.onEvent(type,downloadInfo);
          //  EventBus.getDefault().post(downloadInfo);
            Log.d("DownloadObserver","downing________DOWNLOAD_PAUSE");
        }

    }

    @Override
    public void onComplete() {
        Log.d("My_Log","onComplete");
        if (downloadInfo != null){
            downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD_OVER);
            handler.onEvent(type,downloadInfo);
          //  EventBus.getDefault().post(downloadInfo);
            Log.d("DownloadObserver","Downing________________DOWNLOAD_OVER");
        }
    }
}
