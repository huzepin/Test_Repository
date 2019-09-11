package cn.com.coolsoft.notebook.bean;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.ConcurrentModificationException;
import java.util.List;

import cn.com.coolsoft.notebook.AddGoodsActivity;
import cn.com.coolsoft.notebook.R;

/**
 * @author admin
 * @time 2019/9/9 17
 */
public class GoodModel_ItemHolder extends ItemHolder implements View.OnClickListener {
    private TextView titles;
    private String goodmodeid,ModelName,GoodName;
    private Context mcontext;

    public GoodModel_ItemHolder(View itemView,Context context) {
        super(itemView);
        titles = itemView.findViewById(R.id.tv_title);
        titles.setOnClickListener(this);
        mcontext = context;
    }


    @Override
    public void onBind(Object item) {
        super.onBind(item);
        Goodmode goodmode = (Goodmode)item;
        goodmodeid = String.valueOf(goodmode.getId());
        ModelName = goodmode.getName();
        GoodName = goodmode.getGoodname();
        titles.setText(goodmode.getName());
    }

    @Override
    public void onPropertyChanged(Model model, String propertyName) {
        super.onPropertyChanged(model, propertyName);
    }


    @Override
    public void onUnBind() {
        super.onUnBind();
    }

    @Override
    public void onClick(View v) {
        if (mcontext != null) {
            Intent intent = new Intent(mcontext, AddGoodsActivity.class);
            intent.putExtra("id",goodmodeid);
            intent.putExtra("goodName",GoodName);
            intent.putExtra("modelName",ModelName);
            mcontext.startActivity(intent);
        }
    }
}
