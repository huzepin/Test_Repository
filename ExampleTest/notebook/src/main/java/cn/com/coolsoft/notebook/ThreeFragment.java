package cn.com.coolsoft.notebook;

import android.content.Context;
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
import android.widget.TextView;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
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


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_model_type){
            PlatformUtil.shareQQ(getContext(),"23156156");
        }else if (v.getId() == R.id.tv_time_type){
            PlatformUtil.shareWechatFriend(getContext(),"564848");
        }else if (v.getId() == R.id.tv_buyer_type){

        }
    }


    class InnerThread extends Thread{
        @Override
        public void run() {
            List<GoodOrder> goodmodes = GoodOrder.findWithQuery(GoodOrder.class,"SELECT * FROM GOOD_ORDER order by time desc limit 10",null);
           // List<GoodOrder> goodmodes = GoodOrder.listAll(GoodOrder.class);
            listModel.list = (ArrayList) goodmodes;
            Message message = Message.obtain();
            message.what = 0;
            handler.sendMessage(message);
        }
    }



}
