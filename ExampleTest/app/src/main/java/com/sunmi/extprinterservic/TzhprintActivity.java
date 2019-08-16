package com.sunmi.extprinterservic;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.printer.sdk.PrinterConstants;
import com.printer.sdk.PrinterInstance;
import com.printer.sdk.usb.USBPort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TzhprintActivity extends Activity implements View.OnClickListener {
    /**
     * 天之河打印
     */
    private List<UsbDevice> deviceList;
    private static UsbDevice mUSBDevice;
    public static String devicesName = "未知设备";
    private static String devicesAddress;
    private ProgressDialog dialog;
    public static PrinterInstance myPrinter;
    private Context mContext;
    private boolean isConnected = false;

    private static final String ACTION_USB_PERMISSION = "com.android.usb.USB_PERMISSION";

    private TextView connect_status ,print_status;
    private Button connect_print,test_print,getPrint_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_jcprint);
        connect_status = findViewById(R.id.tv_connect_status);
        connect_print = findViewById(R.id.bt_connect);
        test_print = findViewById(R.id.bt_test_print);
        print_status = findViewById(R.id.tv_print_status);
        getPrint_status = findViewById(R.id.bt_query_status);
        connect_print.setOnClickListener(this);
        test_print.setOnClickListener(this);
        getPrint_status.setOnClickListener(this);
        mContext = TzhprintActivity.this;
        PrinterConstants.paperWidth = 576;
        /*
        switch (PrinterConstants.paperWidth) {
            case 384:
                rg__select_paper_size.check(R.id.rb_58mm);
                break;
            case 576:
                rg__select_paper_size.check(R.id.rb_80mm);
                break;
            case 724:
                rg__select_paper_size.check(R.id.rb_100mm);
                break;
            default:
                rg__select_paper_size.check(R.id.rb_80mm);
                break;
        }

         */

        //两个日历权限和一个数据读写权限
        String[] permissions = new String[]{ android.Manifest.permission.PACKAGE_USAGE_STATS};
        // PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
        //这里的this不是上下文，是Activity对象！
        PermissionsUtils.getInstance().chekPermissions(this, permissions, permissionsResult);

        AppOpsManager appOps = (AppOpsManager) this .getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow("android:get_usage_stats",
                android.os.Process.myUid(), this.getPackageName());
        boolean granted = mode == AppOpsManager.MODE_ALLOWED;
        if (!granted){
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivityForResult(intent,100);
        }

        getAppProcessName(this);



    }

    public  void getAppProcessName(Context context) {
        //当前应用pid
        final PackageManager packageManager = getPackageManager();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        // get all apps
        final List<ResolveInfo> apps = packageManager.queryIntentActivities(mainIntent, 0);
        for (int i = 0; i <apps.size() ; i++) {
            String name = apps.get(i).activityInfo.packageName;
            if(name.contains("cn.com.coolsoft")&&!name.contains("android")){
                Log.i("TAG", "====getAppProcessName: "+apps.get(i).activityInfo.packageName);
            }
        }
    }



    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            //Toast.makeText(BaseActivity_test.this, "权限通过，可以做其他事情!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void forbitPermissons() {
            //finish();
            Toast.makeText(TzhprintActivity.this, getString(R.string.no_permission), Toast.LENGTH_SHORT).show();
        }
    };

    // 用于接受连接状态消息的 Handler
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PrinterConstants.Connect.SUCCESS: {
                    isConnected = true;
                    break;
                }
                case PrinterConstants.Connect.FAILED: {
                    isConnected = false;
                    break;
                }
                default:
                        break;
            }


            updateButtonState(isConnected);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            super.handleMessage(msg);
        }

    };


    private void updateButtonState(boolean isConnected) {
        if (isConnected) {
            connect_status.setText("已连接");
        } else {
            connect_status.setText("未连接");
        }

    }

    private void doDiscovery(UsbManager manager) {
        HashMap<String, UsbDevice> devices = manager.getDeviceList();
        deviceList = new ArrayList<UsbDevice>();
        for (UsbDevice device : devices.values()) {
            if (USBPort.isUsbPrinter(device)) {
                deviceList.add(device);
            }
        }

    }

    //Usb 连接
    public void usbAutoConn(UsbManager manager) {
        doDiscovery(manager);
        if (deviceList.isEmpty()) {
            Toast.makeText(mContext, "未连接", Toast.LENGTH_SHORT).show();
            return;
        }
        mUSBDevice = deviceList.get(0);
        if (mUSBDevice == null) {
            mHandler.obtainMessage(PrinterConstants.Connect.FAILED).sendToTarget();
            return;
        }
        myPrinter = PrinterInstance.getPrinterInstance(mContext, mUSBDevice, mHandler);
        devicesName = mUSBDevice.getDeviceName();
        devicesAddress = "vid: " + mUSBDevice.getVendorId() + "  pid: " + mUSBDevice.getProductId();
        UsbManager mUsbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
        if (mUsbManager.hasPermission(mUSBDevice)) {
            myPrinter.openConnection();
        } else {
            // 没有权限询问用户是否授予权限
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
            filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
            mContext.registerReceiver(mUsbReceiver, filter);
            mUsbManager.requestPermission(mUSBDevice, pendingIntent); // 该代码执行后，系统弹出一个对话框
        }

    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @SuppressLint("NewApi")
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.w("JCprintActivity", "receiver action: " + action);

            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    mContext.unregisterReceiver(mUsbReceiver);
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)
                            && mUSBDevice.equals(device)) {
                        myPrinter.openConnection();
                    } else {
                        mHandler.obtainMessage(PrinterConstants.Connect.FAILED).sendToTarget();
                        Log.e("JCprintActivity", "permission denied for device " + device);
                    }
                }
            }
        }
    };

    private String PrintStatus = "未知状态";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_connect:
                UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
                usbAutoConn(manager);
                break;
            case R.id.bt_test_print:

                if (isConnected) {
                    new Thread(new Runnable() {
                        public void run() {
                           printTest(getResources(), myPrinter);
                        }

                    }).start();
                } else {

                    Toast.makeText(mContext,"设备未连接", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_query_status:
                if (isConnected) {
                    new Thread(new Runnable() {
                        public void run() {
                            int i = myPrinter.getCurrentStatus();
                            if (i == 0) {
                                PrintStatus = "打印机状态正常";
                            } else if (i == -1) {
                                PrintStatus = "接收数据失败";
                            } else if (i == -2) {
                                PrintStatus = "打印机缺纸";
                            } else if (i == -3) {
                                PrintStatus = "打印机纸将尽";
                            } else if (i == -4) {
                                PrintStatus = "打印机开盖";
                            } else if (i == -5) {
                                PrintStatus = "发送数据失败";
                            }
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    print_status.setText(PrintStatus);
                                    Toast.makeText(mContext, PrintStatus, Toast.LENGTH_LONG).show();

                                }
                            });

                        }

                    }).start();
                } else {
                    Toast.makeText(mContext,"设备未连接", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }


    public  void printTest(Resources resources, PrinterInstance mPrinter) {


        mPrinter.initPrinter();

        mPrinter.printText("打印文本效果展示：");
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 2);

        mPrinter.setFont(0, 0, 0, 0, 0);
        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, 0);
        mPrinter.printText("文字居左效果演示abc123：");
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 2);// ��2��

        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, 1);
        mPrinter.printText("文字居中效果演示abc123：");
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 2);// ��2��

        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, 2);
        mPrinter.printText("文字居右效果演示abc123：");
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 3); // ��3��

        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, 0);
        mPrinter.setFont(0, 0, 0, 1, 0);
        mPrinter.printText("文字加粗效果演示abc123：");
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 2); // ��2��

        mPrinter.setFont(0, 0, 0, 0, 1);
        mPrinter.sendBytesData(new byte[] { (byte) 0x1C, (byte) 0x21, (byte) 0x80 });
        mPrinter.printText("文字下划线效果演示abc123：");
        mPrinter.sendBytesData(new byte[] { (byte) 0x1C, (byte) 0x21, (byte) 0x00 });
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 2); // ��2��

        mPrinter.setFont(0, 0, 0, 0, 0);
        mPrinter.printText("同行不同高效果演示：：");
        for (int i = 0; i < 4; i++) {
            mPrinter.setFont(0, i, i, 0, 0);
            mPrinter.printText((i + 1) + "倍");

        }
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 1);
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 3);

        for (int i = 0; i < 4; i++) {

            mPrinter.setFont(0, i, i, 0, 0);
            mPrinter.printText("放大"+ (i + 1) + "倍效果演示abc123");
            mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 3);

        }

        mPrinter.setFont(0, 0, 0, 0, 0);
        mPrinter.setPrinter(PrinterConstants.Command.ALIGN, 0);
        mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 3);


        allCut();
    }

   //半切
    public void allCut() {
        if (isConnected) {
           // new Thread(new Runnable() {
             //   public void run() {
                    XTUtils.printNote(getResources(), myPrinter);
                    myPrinter.cutPaper(65, 50);
            //    }
          //  }).start();

        } else {
            Toast.makeText(mContext, "设备未连接", Toast.LENGTH_SHORT).show();
        }
    }
  //全切
    public void halfCut() {
        if (isConnected) {
            new Thread(new Runnable() {
                public void run() {
                    XTUtils.printNote(getResources(), myPrinter);
                    myPrinter.cutPaper(66, 50);
                }
            }).start();

        } else {
            Toast.makeText(mContext, "设备未连接", Toast.LENGTH_SHORT).show();
        }
    }
}
