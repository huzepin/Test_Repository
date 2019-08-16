package com.sunmi.extprinterservic;

import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

import static android.widget.NumberPicker.OnScrollListener.SCROLL_STATE_IDLE;


public class AnimatorSampleActivity extends Activity {


    private List<String> mlist = new ArrayList<>();
   private MainAdapter adapter ;
   private RecyclerView mRecyclerview;
   private TextView tvAdd , tvDel;
   private Spinner mspinner ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_sample);
        initData();

        adapter = new MainAdapter(this, mlist);
        mRecyclerview = findViewById(R.id.list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerview.setLayoutManager(layoutManager);

        mRecyclerview.setItemAnimator(new SlideInLeftAnimator());

        mRecyclerview.setAdapter(adapter);

        tvAdd = findViewById(R.id.add);
        tvDel = findViewById(R.id.del);


        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerview.scrollToPosition(mlist.size());
                adapter.add("newly added item", 1);
                mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        if (newState == SCROLL_STATE_IDLE){ //滚动停止

                        }

                        super.onScrollStateChanged(recyclerView, newState);
                    }
                });

            }
        });

        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerview.smoothScrollToPosition(mlist.size()-1);
                adapter.remove(1);

            }
        });
    }



   private void initData(){
        for (int i =0; i <6 ; i ++){
            mlist.add(i,"第"+i+"个");
        }
    }
}
