package com.example.phone_9.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author admin
 * @time 2019/9/12 11
 */
public class MyLinearLayouts extends LinearLayout {
    public MyLinearLayouts(Context context) {
        super(context);
    }

    public MyLinearLayouts(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayouts(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.d("MainActivity", "MyLinearLayouts____dispatchTouchEvent--end "+b);
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //boolean b = super.onInterceptTouchEvent(ev);
        Log.d("MainActivity", "MyLinearLayouts____onInterceptTouchEvent--end=");
        return true;   // 返回 true 表示当前视图消费了该事件不下发到子视图
    }
}
