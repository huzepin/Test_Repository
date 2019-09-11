package cn.com.coolsoft.notebook.bean;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * @author admin
 * @time 2019/9/9 10
 */
public class BaseAdapter extends RecyclerView.Adapter implements ListChangedListener {
    private final ListModel listModel;
    private final int itemResourceId;
    private final Constructor holderCtor;
    private final Context mcontext;

    public BaseAdapter(ListModel listModel,Class holderClass, int itemResourceId,Context context) {
        this.listModel = listModel;
        this.mcontext = context;
        try {
            holderCtor = holderClass.getDeclaredConstructor(View.class,Context.class); // 构造函数的参数类型
        } catch (Exception ex) {
            throw new RuntimeException();
        }
        //this.layoutInflater = layoutInflater;// LayoutInflater.from(activity);
        this.itemResourceId = itemResourceId;

    }

    public void setActivity(){}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemResourceId, viewGroup, false);
        try {
            ItemHolder holder = (ItemHolder) holderCtor.newInstance(view,mcontext);//通过有两个参数的构造函数来获取对象
            holder.listModel = listModel;
            return holder;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemHolder) viewHolder).onBind(listModel.item(i));
    }

    @Override
    public int getItemCount() {
        return listModel.itemCount();
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        ((ItemHolder) holder).onUnBind();
    }

    @Override
    public void onListChanged(ListModel listModel,ListChangedKind kind, int index) {
        switch (kind) {
            case insert:
                notifyItemInserted(index);
                break;
            case remove:
                notifyItemRemoved(index);
                break;
            case clear:
                notifyDataSetChanged();
                break;
        }
    }
}
