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
public class MyLinearLayoutInners extends LinearLayout {
    public MyLinearLayoutInners(Context context) {
        super(context);
    }

    public MyLinearLayoutInners(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayoutInners(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.d("MainActivity", "MyLinearLayoutInners____dispatchTouchEvent--end "+b);
        return true;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("MainActivity", "MyLinearLayoutInners____onInterceptTouchEvent--end ");
        return super.onInterceptTouchEvent(ev);
    }
}
