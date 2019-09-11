package cn.com.coolsoft.notebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.coolsoft.notebook.bean.BaseAdapter;
import cn.com.coolsoft.notebook.bean.GoodModel_ItemHolder;
import cn.com.coolsoft.notebook.bean.GoodPosition;
import cn.com.coolsoft.notebook.bean.Goodmode;
import cn.com.coolsoft.notebook.bean.ListModel;

import static cn.com.coolsoft.notebook.utils.PublicDatas.TAG;

/**
 * @author admin
 * @time 2019/9/9 09
 */
public class FirstFragment extends Fragment implements View.OnClickListener {

    private TextView mTextView;
    //要显示的页面
    private int FragmentPage;
    private RecyclerView ListView;
    private LinearLayout ll_add_model;

    private Activity mcontext;
    private BaseAdapter adapter=null;
    private ListModel listModel;

    public static FirstFragment newInstance(String context,int iFragmentPage){

        FirstFragment myFragment = new FirstFragment();
        myFragment.FragmentPage = iFragmentPage;
        return  myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(FragmentPage,container,false);
        ListView = view.findViewById(R.id.ry_list);
        ll_add_model = view.findViewById(R.id.ll_add_model);
        mcontext = getActivity();
        ll_add_model.setOnClickListener(this);

        List<Goodmode> goodmodes = Goodmode.listAll(Goodmode.class);
        listModel = new ListModel();
        listModel.list = (ArrayList) goodmodes;
        adapter = new BaseAdapter(listModel,GoodModel_ItemHolder.class,R.layout.model_item,mcontext);

        ListView.setLayoutManager(new LinearLayoutManager(mcontext));
        ListView.setHasFixedSize(true);
        ListView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        Goodmode goodmode = new Goodmode() ;

        Log.e(TAG,"==========onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"==========onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"==========onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"==========onDestroy");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 10 && resultCode == 10){  //添加模版成功
                List<Goodmode> goodmodes = Goodmode.listAll(Goodmode.class);
                if (goodmodes != null)
                listModel.addItem(goodmodes.get(goodmodes.size()-1));
              //  List<GoodPosition> goodPositionList = GoodPosition.listAll(GoodPosition.class);
               // List<GoodPosition> user = GoodPosition.find(GoodPosition.class, "modeid = ?", goodmodes.get(0).getId()+"");

            }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mcontext,EditModelDialogActivity.class);
        intent.putExtra("type","model");
        startActivityForResult(intent,10);

    }
}
