package com.sunmi.extprinterservic;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private Context mcontext;
    private List mlist;
    private View inflater;

    public  MainAdapter(Context context, List list){
         this.mcontext = context;
         this.mlist = list;
    }


    public void remove(int position) {
        int index = mlist.size()-1;
        if (index >=0)
        mlist.remove(index);

        notifyItemRemoved(index);
    }

    public void add(String text, int position) {
        mlist.add(mlist.size(), text);
        notifyItemInserted(mlist.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mcontext).inflate(R.layout.layout_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //将数据和控件绑定
        holder.textView.setText(mlist.get(position).toString());
        holder.imgeView.setBackgroundResource(R.drawable.chip);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
       final TextView textView;
       final  ImageView imgeView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imgeView = itemView.findViewById(R.id.image);
        }
    }


}
