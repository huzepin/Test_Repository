package cn.com.coolsoft.notebook;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.com.coolsoft.notebook.bean.BaseAdapter;
import cn.com.coolsoft.notebook.bean.GoodModel_ItemHolder;
import cn.com.coolsoft.notebook.bean.GoodOrder;
import cn.com.coolsoft.notebook.bean.Goodmode;
import cn.com.coolsoft.notebook.bean.ListModel;

/**
 * @author admin
 * @time 2019/9/19 09
 */
public class GoodModelActivity extends AppCompatActivity {

    private RecyclerView listview;
    private BaseAdapter adapter;
    private ListModel listModel;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                long current = Thread.currentThread().getId();
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsmodel_activity);
        listview = findViewById(R.id.list_view);
        listModel = new ListModel();
        adapter = new BaseAdapter(listModel, GoodModel_ItemHolder.class,R.layout.model_item,this);

        listview.setLayoutManager(new LinearLayoutManager(this));
        listview.setHasFixedSize(true);
        listview.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new InnerThread().start();
    }

    class InnerThread extends Thread{
        @Override
        public void run() {
            List<Goodmode> goodmodes = Goodmode.listAll(Goodmode.class);
            listModel.list = (ArrayList) goodmodes;
            Message message = Message.obtain();
            message.what = 0;
            handler.sendMessage(message);
        }
    }
}
