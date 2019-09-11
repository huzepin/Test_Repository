package com.examples.one;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sunmi.extprinterservic.R;

import java.util.TooManyListenersException;

/**
 * @author admin
 * @time 2019/9/6 14
 */
public class ExamplesOne extends Activity implements View.OnClickListener {

    private Button btText;
    private TextView tv_Tip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exampleone);

        btText = findViewById(R.id.bt_test1);
        tv_Tip = findViewById(R.id.tv_tip);

        System.out.println("main thread1 id " + Thread.currentThread().getId());
        btText.setOnClickListener(this);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 0:
                   System.out.println("main thread2 id " + Thread.currentThread().getId());
                   Toast.makeText(ExamplesOne.this,"0",Toast.LENGTH_SHORT).show();
                   break;
               case 1:
                   Toast.makeText(ExamplesOne.this,"1",Toast.LENGTH_SHORT).show();
                   break;
           }
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("zi thread id 正在等待");
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
            System.out.println("zi thread id " + Thread.currentThread().getId());
            tv_Tip.setText("000009999999999999999999999"); //通过Handler 执行了runnable 是在主线程中运行的
            Message message = Message.obtain();
            message.what=0;
            handler.sendMessage(message);
        }
    };

   class  MyThread extends Thread{
       @Override
       public void run() {
           super.run();
           try {
               System.out.println("zi thread2 id 正在等待");
               Thread.sleep(600000);
               System.out.println("zi thread2 id " + Thread.currentThread().getId());
           }catch (Exception e){

           }
       }
   }

   class MyThread1 implements Runnable{
       @Override
       public void run() {
           try {
               System.out.println("zi thread3 id 正在等待");
               Thread.sleep(30000);
               System.out.println("zi thread3 id " + Thread.currentThread().getId());
           }catch (Exception e){

           }
       }
   }

    /*
SharedPreferences sharedPreferences = this.getSharedPreferences("order", Context.MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPreferences.edit();
editor.putString("order","02154651615564");
editor.commit();
*/

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_test1){
            handler.post(runnable);  //依然是在主线程中运行 不超过29秒
           // handler.postDelayed(runnable,1000);
          //  Thread thread = new Thread(runnable);

          // thread.start();

           // MyThread thread = new MyThread();
          //  thread.start();

//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            tv_Tip.setText("113456698");
            //handler.removeCallbacks(runnable);
           // Toast.makeText(this,"sssss",Toast.LENGTH_SHORT).show();
        }else {

        }
    }
}
