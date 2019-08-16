package com.example.phone_9.uploadexpection;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.phone_9.R;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


/**
 * @author admin
 * @time 2019/7/23 16
 */
public class CrashHandlerManage implements Thread.UncaughtExceptionHandler {
    private  Context context;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private CrashHandlerManage(Context context){
         this.context = context;
    }
    public static CrashHandlerManage INSTANCE ;


    public Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public synchronized static CrashHandlerManage getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new CrashHandlerManage(context);
        }
        return INSTANCE;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("程序出现异常了", "Thread = " + t.getName() + "\nThrowable = " + e.getMessage());
        if (!handleException(e) && mDefaultHandler != null) {


            mDefaultHandler.uncaughtException(t, e);
        } else {
            SystemClock.sleep(1000);
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息; 否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null)
            return false;

        try {
            // 使用Toast来显示异常信息
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(context, context.getString(R.string.exception_tip),
                            Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }.start();
            // 收集设备参数信息
            //collectDeviceInfo(mContext);
            // 保存日志文件
            String stackTraceInfo = getStackTraceInfo(ex);
            Log.e("stackTraceInfo", stackTraceInfo);
            saveThrowableMessage(stackTraceInfo);

            // 重启应用（按需要添加是否重启应用）
//            Intent intent = context.getPackageManager()
//            .getLaunchIntentForPackage(context.getPackageName());
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            context.startActivity(intent);
//            SystemClock.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 获取错误的信息
     *
     * @param throwable
     * @return
     */
    private String getStackTraceInfo(final Throwable throwable) {
        PrintWriter pw = null;
        Writer writer = new StringWriter();
        try {
            pw = new PrintWriter(writer);
            throwable.printStackTrace(pw);
        } catch (Exception e) {
            return "";
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return writer.toString();
    }



    private void saveThrowableMessage(String errorMessage) {
         String logFilePath = Environment.getExternalStorageDirectory() + File.separator + "Android" +
                File.separator + "data" + File.separator + context.getPackageName() + File.separator + "crashLog";

        if (TextUtils.isEmpty(errorMessage)) {
            return;
        }
        File file = new File(logFilePath);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (mkdirs) {
                writeStringToFile(errorMessage, file);
            }
        } else {
            writeStringToFile(errorMessage, file);
        }
    }

    private void writeStringToFile(final String errorMessage, final File file) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream outputStream = null;
                try {
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(errorMessage.getBytes());
                    outputStream = new FileOutputStream(new File(file, System.currentTimeMillis() + ".txt"));
                    int len = 0;
                    byte[] bytes = new byte[1024];
                    while ((len = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
                    }
                    outputStream.flush();
                    Log.e("程序出异常了", "写入本地文件成功：" + file.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }


    public void initCrash(Context context)
    {
        this.context = context;
        //获取系统默认的UncaughtException处理器
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序得默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);

    }

}
