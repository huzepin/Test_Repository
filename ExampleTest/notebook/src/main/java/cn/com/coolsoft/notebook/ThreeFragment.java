package cn.com.coolsoft.notebook;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.com.coolsoft.notebook.bean.BaseAdapter;
import cn.com.coolsoft.notebook.bean.GoodOrder;
import cn.com.coolsoft.notebook.bean.GoodOrder_ItemHolder;
import cn.com.coolsoft.notebook.bean.GoodPosition;
import cn.com.coolsoft.notebook.bean.ListModel;
import cn.com.coolsoft.notebook.utils.PlatformUtil;

/**
 * @author admin
 * @time 2019/9/9 09
 */
public class ThreeFragment extends Fragment implements View.OnClickListener {

    private String context="xxxxxxxxxxxxx";
    private TextView tv_model_type,tv_time_type,tv_buyer_type;
    private RecyclerView list;



    private Context mcontext;
    private BaseAdapter adapter=null;
    private static ListModel listModel;
    //要显示的页面
    private int FragmentPage;

    public  static ThreeFragment newInstance(String context, int iFragmentPage){

        ThreeFragment myFragment = new ThreeFragment();
        myFragment.context = context;
        myFragment.FragmentPage = iFragmentPage;
        return  myFragment;
    }


    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(FragmentPage,container,false);
        tv_model_type = view.findViewById(R.id.tv_model_type);
        tv_time_type = view.findViewById(R.id.tv_time_type);
        tv_buyer_type = view.findViewById(R.id.tv_buyer_type);
        list = view.findViewById(R.id.ry_list);
        tv_model_type.setOnClickListener(this);
        tv_time_type.setOnClickListener(this);
        tv_buyer_type.setOnClickListener(this);
        mcontext = getContext();
        listModel = new ListModel();
        adapter = new BaseAdapter(listModel, GoodOrder_ItemHolder.class,R.layout.order_item,mcontext);

        list.setLayoutManager(new LinearLayoutManager(mcontext));
        list.setHasFixedSize(true);
        list.setAdapter(adapter);


        list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
                outRect.bottom = 2;
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        new InnerThread().start();
    }


    long order_time ;
    private String buyer;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_model_type){
            startActivity(new Intent(mcontext,GoodModelActivity.class));
            //PlatformUtil.shareQQ(getContext(),"23156156");
//            new TimePickerDialog(mcontext, new TimePickerDialog.OnTimeSetListener() {
//                //实现监听方法
//                @Override
//                public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                    //设置文本显示内容
//                   // Toast.makeText(MainActivity.this,"当前时间："+mYear+"年"+mMonth+"月"+mDay+"日   "+i+":"+i1,Toast.LENGTH_SHORT).show();
//                }
//            },hour,minute,true).show();//记得使用show才能显示


        }else if (v.getId() == R.id.tv_time_type){
           // PlatformUtil.shareWechatFriend(getContext(),"564848");
            Calendar ca = Calendar.getInstance();
            final int mYear = ca.get(Calendar.YEAR);
            final int mMonth = ca.get(Calendar.MONTH);
            final int mDay = ca.get(Calendar.DAY_OF_MONTH);
            int hour = ca.get(Calendar.HOUR);//获取小时
            int minute = ca.get(Calendar.MINUTE);//获取分钟
            int seconds = ca.get(Calendar.SECOND);//获取秒钟

            DatePickerDialog datePickerDialog = new DatePickerDialog(mcontext,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String str = year+"-"+(month+1)+"-"+(dayOfMonth-1)+" 16:00:00";  // 00:00:00
                            Date date = null;
                            try {
                                date = format.parse(str);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            order_time = date.getTime();
                            new Thread(timerun).start();
                        }
                    },mYear, mMonth, mDay);
            datePickerDialog.show();
        }else if (v.getId() == R.id.tv_buyer_type){
            final EditText et = new EditText(mcontext);
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("请输入购买人的姓名");
            dialog.setTitle("按购买人搜索");
            dialog.setView(et);
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
                 String name = et.getText().toString().trim();
                    buyer = name;
                   new Thread(namerun).start();
                }
            });

            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.create().show();
        }
    }


    Runnable timerun = new Runnable() {
        @Override
        public void run() {
            List<GoodOrder> goodmodes = GoodOrder.findWithQuery(GoodOrder.class,"SELECT * FROM GOOD_ORDER where time > ?",order_time+"");
            listModel.list = (ArrayList) goodmodes;
            Message message = Message.obtain();
            message.what = 0;
            handler.sendMessage(message);
        }
    };

    Runnable namerun = new Runnable() {
        @Override
        public void run() {
            List<GoodOrder> goodmodes = GoodOrder.findWithQuery(GoodOrder.class,"SELECT * FROM GOOD_ORDER where buyer = ?",buyer);
            listModel.list = (ArrayList) goodmodes;
            Message message = Message.obtain();
            message.what = 0;
            handler.sendMessage(message);
        }
    };

    class InnerThread extends Thread{
        @Override
        public void run() {
          //  List<GoodOrder> goodmodes = GoodOrder.findWithQuery(GoodOrder.class,"SELECT * FROM GOOD_ORDER order by time desc limit 10",null);
            long time = 1568771440563l;
            Date date = new Date(time);
            List<GoodOrder> goodmodes = GoodOrder.findWithQuery(GoodOrder.class,"SELECT * FROM GOOD_ORDER where time > ?","1568771440563");
           // List<GoodOrder> goodmodes = GoodOrder.listAll(GoodOrder.class);
            listModel.list = (ArrayList) goodmodes;
            Message message = Message.obtain();
            message.what = 0;
            handler.sendMessage(message);
        }
    }



}
