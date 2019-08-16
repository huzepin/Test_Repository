package com.example.phone_9.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author admin
 * @time 2019/8/8 09
 */
public class RacView extends View {
    private Paint mpaint = new Paint();



    public RacView(Context context) {
        super(context);
        init();
    }

    public RacView(Context context,AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RacView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
       mpaint.setColor(Color.BLUE);
       mpaint.setStrokeWidth(2);
       mpaint.setStyle(Paint.Style.STROKE); //描边
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(100,100,100,mpaint);

       // mpaint.setColor(Color.TRANSPARENT);
       // mpaint.setStyle(Paint.Style.FILL);          //填充
        canvas.drawCircle(100,100,80,mpaint);

        //mpaint.setColor(Color.TRANSPARENT);
        mpaint.setStrokeWidth(20);
        mpaint.setColor(Color.RED);
        canvas.drawCircle(100,100,90,mpaint);
      // mpaint.setStyle(Paint.Style.FILL_AND_STROKE); //描边加填充

    }
}
