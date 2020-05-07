package com.example.test_jni;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    static {
        System.loadLibrary("native-lib");  //native-lib  osdApi
    }


    private EditText et_szcip,et_port,et_linenum,et_l,et_2,et_3,et_4,et_5,et_6,et_7,et_8;
    private EditText et_clear1,et_clear2,et_clear3;
    private TextView tv_tip;
    private Button bt_init,bt_one,bt_two,bt_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_szcip = findViewById(R.id.et_szcip);
        et_port = findViewById(R.id.et_port);
        et_linenum = findViewById(R.id.et_linenum);
        et_l = findViewById(R.id.et_l);
        et_2 = findViewById(R.id.et_2);
        et_3 = findViewById(R.id.et_3);
        et_4 = findViewById(R.id.et_4);
        et_5 = findViewById(R.id.et_5);
        et_6 = findViewById(R.id.et_6);
        et_7 = findViewById(R.id.et_7);
        et_8 = findViewById(R.id.et_8);


        tv_tip = findViewById(R.id.tv_tip);


        bt_init = findViewById(R.id.bt_init);
        bt_one = findViewById(R.id.bt_one);
        bt_two = findViewById(R.id.bt_two);
        bt_clear = findViewById(R.id.bt_clear);


        et_clear1 = findViewById(R.id.et_clear1);
        et_clear2 = findViewById(R.id.et_clear2);
        et_clear3 = findViewById(R.id.et_clear3);


        bt_two.setOnClickListener(this);
        bt_one.setOnClickListener(this);
        bt_init.setOnClickListener(this);
        bt_clear.setOnClickListener(this);


    }

    /**
     * int exitWfSDK();
     * int initWfSDK(char *szcIP, unsigned int uiPort);
     * int WFOSD_setOsdDisplayLineNum(unsigned int uiLineNum);
     * int WFOSD_set(char *szcIP, unsigned int uiPort, char *szcOsd, int iOsdLen, int iX, int iY, unsigned short iColor, char *szcCode);
     * int WFOSD_clear(char *szcIP, unsigned int uiPort, char *szcCode);
     */

    public static  native int initSDK(String sicip,int port);
    public static  native int exitSDK();
    public static  native int WFOSD_setOsdDisplayLineNums(int lineNum);
                                       // 10.0.0.88 ，   6605 ，       12 ，    2 ，        0 ，    0 ，  0xFF0000FF(转成了short类型)， 1
    public static  native int WFOSD_sets(String szcip,int port,String szcosd,int iosdlen,int ix,int iy, short iColor,String szcCode);
    public static  native int WFOSD_clears(String szcip,int port,String szcCode);

    @Override
    public void onClick(View v) {
        if (v == bt_init){
            String szcip = et_szcip.getText().toString().trim();
            String port = et_port.getText().toString().trim();
            int status = initSDK(szcip,Integer.parseInt(port));
            tv_tip.setText(status+"");
        }else if (v == bt_one){
            String linenum = et_linenum.getText().toString().trim();
            int status = WFOSD_setOsdDisplayLineNums(Integer.parseInt(linenum));
            tv_tip.setText(status+"");
        }else if (v == bt_two){
         // (char *szcIP, unsigned int uiPort, char *szcOsd, int iOsdLen, int iX, int iY, unsigned short iColor, char *szcCode);

            String szcIP = et_l.getText().toString().trim();
            String port  = et_2.getText().toString().trim();
            String szcOsd = et_3.getText().toString().trim();
            String iOsdLen = et_4.getText().toString().trim();
            //String iX = et_5.getText().toString().trim();
            //String iY = et_6.getText().toString().trim();
            //String iColor = et_7.getText().toString().trim();
            String szcCode = et_8.getText().toString().trim();
            //Integer.parseInt(iX),Integer.parseInt(iY),Short.parseShort(iColor)

            // 10.0.0.88 ， 6605 ，12 ，2 ， 0 ， 0 ，  0xFF0000FF(转成了short类型)， 1
            int status = WFOSD_sets(szcIP,Integer.parseInt(port),szcOsd,Integer.parseInt(iOsdLen),0,0, (short) Color.BLUE,szcCode);
            tv_tip.setText(status+"");
        }else if (v == bt_clear){
            String zcip = et_clear1.getText().toString().trim();
            String port = et_clear2.getText().toString().trim();
            String code = et_clear3.getText().toString().trim();

            int status = WFOSD_clears(zcip, Integer.parseInt(port),code);
            tv_tip.setText(status+"");
        }

    }
}
