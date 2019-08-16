package com.example.phone_9.customview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.phone_9.R;

/**
 * @author admin
 * @time 2019/8/6 16
 */
public class CustomViewActivity extends Activity implements DownLoadButton.OnDownLoadButtonClickListener {

    private LineTextView tv_title;
    private com.example.phone_9.customview.ProgressBar progress_bar;
    private DownLoadButton downloadbutton;
    private DownLoadHandler downLoadHandler;
    private int downLoadedPrecent = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("asdfjiosjefijsdiojf");
        progress_bar = findViewById(R.id.progress_bar);
        downloadbutton = findViewById(R.id.download);
        downLoadHandler = new DownLoadHandler();
        downloadbutton.setOnDownLoadButtonClickListener(this);

        progress_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 100);
                valueAnimator.setDuration(4000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float currentValue = (float) animation.getAnimatedValue();
                        progress_bar.setProgress((int) currentValue);
                    }
                });
                valueAnimator.start();
            }
        });

    }


    @Override
    public void onClick(View v, int curState) {
        if (curState == DownLoadButton.STATE_NORMAL) {
            //开始下载
            downloadbutton.setState(DownLoadButton.STATE_DOWNLOADING);
            downLoadHandler.sendEmptyMessageDelayed(1,500);
        }else if(curState == DownLoadButton.STATE_DOWNLOADING){
            //下载中如果被点击时停止下载，这里可以根据自个的需求换成暂停或者其他
            downloadbutton.setState(DownLoadButton.STATE_NORMAL);
            downLoadedPrecent = 0;
            downLoadHandler.removeMessages(1);
        }
    }

    private class DownLoadHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //模拟下载
                    downLoadedPrecent+=1;
                    if(downLoadedPrecent>=100){
                        downloadbutton.setState(DownLoadButton.STATE_COMPLETE);
                    }else{
                        downloadbutton.setDownLoadProgress(downLoadedPrecent);
                        sendEmptyMessageDelayed(1,50);
                    }
                    break;
            }
        }
    }

}