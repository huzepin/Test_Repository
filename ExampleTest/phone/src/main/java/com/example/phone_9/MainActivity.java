package com.example.phone_9;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.phone_9.customview.HVListView;
import com.example.phone_9.customview.MyLinearLayoutInners;
import com.example.phone_9.customview.MyLinearLayouts;
import com.example.phone_9.customview.MyScrollView;

import java.util.ArrayList;
import java.util.Calendar;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyLinearLayouts linearLayouts;
    private MyLinearLayoutInners linearLayoutInners;
    private MyScrollView scroll ;
    private TextView tv_titlt;
    private ListView listView;
    private ArrayAdapter<String> adapter = null;
    private ArrayList mData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayouts = findViewById(R.id.ll_but);
        linearLayoutInners = findViewById(R.id.ll_zi);
        tv_titlt = findViewById(R.id.tv_title);
        listView = findViewById(R.id.listview);
        scroll = findViewById(R.id.scroll);
        tv_titlt.setOnClickListener(this);
        linearLayoutInners.setOnClickListener(this);
        linearLayouts.setOnClickListener(this);


        mData = new ArrayList();
        for (int i =0 ; i < 15; i ++){
            mData.add("ssssssas"+i);
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mData);
        // adapter.setDropDownViewResource(R.drawable.dropdown);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //dispatchTouchEvent和onTouchEvent

    //事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case ACTION_DOWN:
                scroll.setListView(listView);
                Log.d("MainActivity","=============dispatchTouchEvent=ACTION_DOWN");
                break;
            case  ACTION_MOVE:
                Log.d("MainActivity","=============dispatchTouchEvent=ACTION_MOVE");
                break;
            case  ACTION_UP:
                Log.d("MainActivity","=============dispatchTouchEvent=ACTION_UP");
                break;
        }
        boolean ins = super.dispatchTouchEvent(ev);
        Log.d("MainActivity","=============dispatchTouchEvent="+ins);
        return ins;
    }




    //事件消费
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case ACTION_DOWN:
                Log.d("MainActivity","=============onTouchEvent=ACTION_DOWN");
                break;
            case  ACTION_MOVE:
                Log.d("MainActivity","=============onTouchEvent=ACTION_MOVE");
                break;
            case  ACTION_UP:
                Log.d("MainActivity","=============onTouchEvent=ACTION_UP");
                break;
        }
        Log.d("MainActivity","=============onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_but){

            Calendar ca = Calendar.getInstance();
             final int mYear = ca.get(Calendar.YEAR);
             final int mMonth = ca.get(Calendar.MONTH);
             final int mDay = ca.get(Calendar.DAY_OF_MONTH);
             int hour = ca.get(Calendar.HOUR);//获取小时
             int minute = ca.get(Calendar.MINUTE);//获取分钟
             int seconds = ca.get(Calendar.SECOND);//获取秒钟

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            Toast.makeText(MainActivity.this,"="+year+"-"+month+"-"+dayOfMonth,Toast.LENGTH_SHORT).show();
                        }
                    },
                    mYear, mMonth, mDay);

            datePickerDialog.show();

          new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                //实现监听方法
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    //设置文本显示内容
                    Toast.makeText(MainActivity.this,"当前时间："+mYear+"年"+mMonth+"月"+mDay+"日   "+i+":"+i1,Toast.LENGTH_SHORT).show();
                }
            },hour,minute,true).show();//记得使用show才能显示


        }else if (v.getId() == R.id.ll_zi){

        }else if (v.getId() == R.id.tv_title){

        }
    }


}
