package com.sunmi.extprinterservic;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import android.os.IBinder;
import android.os.RemoteException;

import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sunmi.extprinterservice.ExtPrinterService;


public class K1printActivity extends Activity implements View.OnClickListener {
    /**
     * 商米 自助机K1
     */
    private Button k1print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_k1print);


        k1print= findViewById(R.id.k1print);

        Intent intent = new Intent();
        intent.setPackage("com.sunmi.extprinterservice");
        intent.setAction("com.sunmi.extprinterservice.PrinterService");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        k1print.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
       try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("小票：270500027719 收银员：010121212122121\n");
            stringBuilder.append(" ------------------------------------------\n");
            stringBuilder.append("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
            stringBuilder.append(" 商品编码        单价  数量 \n");
            stringBuilder.append("01.9940228004700    3.98   1.181 \n");
            stringBuilder.append("番石榴     小计：4.70   小计： 4.70小计 \n");
            stringBuilder.append("02.996100800220     6.00   0.376 \n");
            stringBuilder.append(" 〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓 \n");
            stringBuilder.append("------------------------------------------\n");
            stringBuilder.append("02.996100800220     6.00   0.376 \n");

            if (inte == null){
                Toast.makeText(this,"初始化失败",Toast.LENGTH_SHORT).show();
            }else {
                int result = inte.printText(stringBuilder.toString());
                Toast.makeText(this,"="+result,Toast.LENGTH_SHORT).show();
                int status = inte.getPrinterStatus();
                if (status == 0 ){
                    inte.cutPaper(0,1);
                }
            }





        } catch (RemoteException e) {
            e.printStackTrace();
       }
    }
    ExtPrinterService inte = null;

   public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            inte = ExtPrinterService.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onDestroy() {

        super.onDestroy();
        unbindService(serviceConnection);
    }
}
