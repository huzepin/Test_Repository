package com.example.phone_9.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @author admin
 * @time 2019/8/9 13
 */
public class GroupViews extends ViewGroup {
    public GroupViews(Context context) {
        super(context);
    }

    public GroupViews(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
    //onInterceptTouchEvent




    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
