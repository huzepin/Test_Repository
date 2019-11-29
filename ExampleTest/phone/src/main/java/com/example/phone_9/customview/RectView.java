package com.example.phone_9.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import com.example.phone_9.R;

/**
 * @author admin
 * @time 2019/8/6 16
 */
public class RectView extends View {

    private Paint mpaint = new Paint();

    public RectView(Context context) {
        super(context);
        init();
    }

    public RectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 当前View的位置
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    public void init(){
        mpaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         // 以下是为了区分 view的 match_partent， warp_partent

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) { //当width 为wrap_content 默认为600 当height 为wrap_content 默认为100
            setMeasuredDimension(600, 100);
        } else if (widthMode == MeasureSpec.AT_MOST) {  //当height 为match_parent  当width 为wrap_content 默认为500
            setMeasuredDimension(500, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) { //当width 为match_parent  当height 为wrap_content 默认为200
            setMeasuredDimension(widthSize, 200);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingBottom - paddingTop;
        canvas.drawRect(paddingLeft,paddingTop,width+paddingLeft,height+paddingTop,mpaint);

    }
}
