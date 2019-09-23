package cn.com.coolsoft.notebook;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.coolsoft.notebook.bean.BaseAdapter;
import cn.com.coolsoft.notebook.bean.GoodModel_ItemHolder;
import cn.com.coolsoft.notebook.bean.GoodOrder;
import cn.com.coolsoft.notebook.bean.GoodOrder_ItemHolder;
import cn.com.coolsoft.notebook.bean.Goodmode;
import cn.com.coolsoft.notebook.bean.ListModel;

/**
 * @author admin
 * @time 2019/9/9 09
 */
public class SecondFragment extends Fragment {

    private String context="xxxxxxxxxxxxx";
    private TextView mTextView;
    private RecyclerView rl_list;
    //要显示的页面
    private int FragmentPage;
    private Context mcontext;
    private BaseAdapter adapter=null;
    private static ListModel listModel;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                long current = Thread.currentThread().getId();
                adapter.notifyDataSetChanged();
            }
        }
    };


    public  static SecondFragment newInstance(String context, int iFragmentPage){
        SecondFragment myFragment = new SecondFragment();
        myFragment.context = context;
        myFragment.FragmentPage = iFragmentPage;
        return  myFragment;
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(FragmentPage,container,false);
        rl_list = view.findViewById(R.id.ry_list);
        mcontext = getContext();
        listModel = new ListModel();
        adapter = new BaseAdapter(listModel, GoodOrder_ItemHolder.class,R.layout.order_item,mcontext);
        long current = Thread.currentThread().getId();
        rl_list.setLayoutManager(new LinearLayoutManager(mcontext));
        rl_list.setHasFixedSize(true);
        rl_list.setAdapter(adapter);

        rl_list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
                outRect.bottom = 2;
            }
        });
        Log.e("SecondFragment","===========onCreateView");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("SecondFragment","===========onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
       // new Thread(runnable).start();
        new InnerThread().start();
        Log.e("SecondFragment","===========onResume");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("SecondFragment","===========onAttach");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.e("SecondFragment","===========onAttachFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("SecondFragment","===========onPause");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.e("SecondFragment","===========onStop");
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            List<GoodOrder> goodmodes = GoodOrder.listAll(GoodOrder.class);
            listModel.list = (ArrayList) goodmodes;
            long current = Thread.currentThread().getId();
            Message message = new Message();
            message.what = 0;
            handler.sendMessage(message);
        }
    };

    class InnerThread extends Thread{
        @Override
        public void run() {
            List<GoodOrder> goodmodes = GoodOrder.listAll(GoodOrder.class);
            listModel.list = (ArrayList) goodmodes;
            Message message = Message.obtain();
            message.what = 0;
            handler.sendMessage(message);
        }
    }


}
