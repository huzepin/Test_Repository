package com.example.phone_9.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.phone_9.R;
import com.example.phone_9.WhiteService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author admin
 * @time 2019/9/18 13
 */
public class SocketActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mTextView,msend;
    private static final String TAG = "TAG";
    private static final String HOST = "192.168.56.1";
    private static final int PORT = 9999;
    private PrintWriter printWriter = null;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;

    private boolean isConnect = false;
    private Socket socket = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        mEditText = (EditText) findViewById(R.id.editText);
        mTextView = (Button) findViewById(R.id.textView);
        msend = (Button) findViewById(R.id.send);
        mExecutorService = Executors.newCachedThreadPool();

        new Thread(connect).start();

        Intent intent = new Intent(this, WhiteService.class);
        startService(intent);

    }


    Runnable connect = new Runnable() {
        @Override
        public void run() {
            while (!isConnect){
                try {
                    if (socket == null) {
                        socket = new Socket(HOST, PORT);      //步骤一
                        socket.setSoTimeout(60000);
                        printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(   //步骤二
                                socket.getOutputStream(), "UTF-8")), true);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                        receiveMsg();
                    }
                } catch (Exception e) {
                    Log.e(TAG, ("connectService:" + e.getMessage()));   //如果Socket对象获取失败，即连接建立失败，会走到这段逻辑
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public void connect(View view) {
   //     mExecutorService.execute(new connectService());  //在一个新的线程中请求 Socket 连接
    }

    public void send(View view) {
        String sendMsg = mEditText.getText().toString();
        Log.d("SocketActivity","===========send="+sendMsg);
        mExecutorService.execute(new sendService(sendMsg));
    }

    public void disconnect(View view) {
        mExecutorService.execute(new sendService("0"));
    }

    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            if (printWriter != null)
            printWriter.println(this.msg);

            Log.d("SocketActivity","===========run="+msg);
        }
    }

    private class connectService implements Runnable {
        @Override
        public void run() {//可以考虑在此处添加一个while循环，结合下面的catch语句，实现Socket对象获取失败后的超时重连，直到成功建立Socket连接
            try {
                if (!socket.isConnected() && socket == null) {
                    socket = new Socket(HOST, PORT);      //步骤一
                    socket.setSoTimeout(60000);
                    printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(   //步骤二
                            socket.getOutputStream(), "UTF-8")), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    receiveMsg();
                }
            } catch (Exception e) {
                Log.e(TAG, ("connectService:" + e.getMessage()));   //如果Socket对象获取失败，即连接建立失败，会走到这段逻辑
            }
        }
    }

    private void receiveMsg() {
        try {
            while (true) {                                      //步骤三
                if ((receiveMsg = in.readLine()) != null) {
                    Log.d(TAG, "receiveMsg:" + receiveMsg);
                    if (receiveMsg.equals("服务端断开连接")){
                        socket.close();
                        isConnect = false;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isConnect = true;
                            mTextView.setText(receiveMsg + "\n\n" + mTextView.getText());
                        }
                    });
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }

}
