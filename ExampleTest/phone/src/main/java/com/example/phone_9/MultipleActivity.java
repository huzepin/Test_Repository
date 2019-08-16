package com.example.phone_9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.widget.Toast;

import com.example.phone_9.adapter.DownloadAdapter;
import com.example.phone_9.bean.DownloadInfo;
import com.example.phone_9.utils.EventHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs
 * Date：2018年 09月 11日
 * Time：17:57
 * —————————————————————————————————————
 * About: 多个文件同时下载
 * —————————————————————————————————————
 */
public class MultipleActivity extends AppCompatActivity implements EventHandler {

    private RecyclerView recycler_view;
    private DownloadAdapter mAdapter;
    private List<DownloadInfo> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
      //  EventBus.getDefault().register(this);
        recycler_view = findViewById(R.id.recycler_view);

        mData.add(new DownloadInfo(Constant.URL_1));
        mData.add(new DownloadInfo(Constant.URL_2));
        mData.add(new DownloadInfo(Constant.URL_3));
        mData.add(new DownloadInfo(Constant.URL_4));
        mData.add(new DownloadInfo(Constant.URL_5));
        mData.add(new DownloadInfo(Constant.URL_6));
        mData.add(new DownloadInfo(Constant.URL_7));
        mData.add(new DownloadInfo(Constant.URL_8));
        mData.add(new DownloadInfo(Constant.URL_9));


        mAdapter = new DownloadAdapter(mData,this);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(mAdapter);
        // 取消item刷新的动画
        ((SimpleItemAnimator)recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);

    }

    @Override
    public void onEvent(int tag, Object value) {
         if (tag == 10){
             DownloadInfo info = (DownloadInfo) value;
        if (DownloadInfo.DOWNLOAD.equals(info.getDownloadStatus())){

            mAdapter.updateProgress(info);

        }else if (DownloadInfo.DOWNLOAD_OVER.equals(info.getDownloadStatus())){

            mAdapter.updateProgress(info);

        }else if (DownloadInfo.DOWNLOAD_PAUSE.equals(info.getDownloadStatus())){

            Toast.makeText(this,"下载暂停",Toast.LENGTH_SHORT).show();

        }else if (DownloadInfo.DOWNLOAD_CANCEL.equals(info.getDownloadStatus())){

            mAdapter.updateProgress(info);
            Toast.makeText(this,"下载取消",Toast.LENGTH_SHORT).show();

        }else if (DownloadInfo.DOWNLOAD_ERROR.equals(info.getDownloadStatus())){

            Toast.makeText(this,"下载出错",Toast.LENGTH_SHORT).show();
        }

    }else if (tag == 11){  //暂停
        Toast.makeText(this,"下载暂停",Toast.LENGTH_SHORT).show();
    }else if (tag == 12){ //取消
        DownloadInfo info = (DownloadInfo) value;
        mAdapter.updateProgress(info);
        Toast.makeText(this,"下载取消",Toast.LENGTH_SHORT).show();

    }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
       // EventBus.getDefault().unregister(this);
    }


}
