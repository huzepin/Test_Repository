package com.example.phone_9.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * @author admin
 * @time 2019/9/12 14
 */
public class MyScrollView extends ScrollView {
    private int left;
    private int top;
    private int right;
    private int bottom;

    private boolean isNoIntercept = false;
    private int adjustHeight;
    private int adjustWidth;
    private GestureDetector gestureDetector;
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
       // gestureDetector = new GestureDetector(context,new myYS());
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setAdjustDistance(int width, int height){
        adjustWidth = width;
        adjustHeight = height;
    }

    public void setListView(ListView lv){
        if (lv == null){
            top = 0;
            left = 0;
            right = 0;
            bottom = 0;
        }else {
            top = adjustHeight + lv.getTop();
            bottom = adjustHeight + lv.getBottom();
            left = adjustWidth + lv.getLeft();
            right = adjustWidth + lv.getRight();
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       // boolean is = super.onInterceptTouchEvent(ev);
       // boolean xy = gestureDetector.onTouchEvent(ev); //这样写无效

       // return is && gestureDetector.onTouchEvent(ev);

        //抬起结束触摸，事件重新交给ScrollView
        if (ev.getAction() == MotionEvent.ACTION_UP){
            isNoIntercept = false;
        }

        //为true则事件交给子View消费
        if (isNoIntercept){
            return false;
        }

        float x = ev.getX();
        float y = ev.getY();


        Log.d("MainActivity","MyScrollView==onInterceptTouchEventX="+x+"=Y="+y+"="+left+"="+right+"="+top+"="+bottom);

        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            if (x>left && x<right && y>top && y<bottom){
                //按下时选中ListView区域，则事件交给子View处理
                isNoIntercept = true;
                return false;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }


    class myYS extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return   Math.abs(distanceY) > Math.abs(distanceX);
          //  return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
}
