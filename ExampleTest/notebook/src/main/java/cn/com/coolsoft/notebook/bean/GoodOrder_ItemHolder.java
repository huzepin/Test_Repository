package cn.com.coolsoft.notebook.bean;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.com.coolsoft.notebook.AddGoodsActivity;
import cn.com.coolsoft.notebook.R;


/**
 * @author admin
 * @time 2019/9/9 17
 */
public class GoodOrder_ItemHolder extends ItemHolder implements View.OnClickListener {
    private TextView buyer,name,price,weight,total,delete,tv_ordertime;
    private String goodmodeid,ModelName,GoodName;
    private Button is_buy;
    private Context mcontext;
    private OnClickSlideDeleteListener listener;

   public interface OnClickSlideDeleteListener{
       void ondelet(Object o);
    }

    public GoodOrder_ItemHolder(View itemView, Context context) {
        super(itemView);
        buyer = itemView.findViewById(R.id.tv_buyer);
        name = itemView.findViewById(R.id.tv_name);
        price = itemView.findViewById(R.id.tv_price);
        weight = itemView.findViewById(R.id.tv_weight);
        total  = itemView.findViewById(R.id.tv_total);
        is_buy = itemView.findViewById(R.id.is_buy);
        delete = itemView.findViewById(R.id.delete);
        tv_ordertime = itemView.findViewById(R.id.tv_ordertime);
        is_buy.setOnClickListener(this);
        delete.setOnClickListener(this);
        listener = (OnClickSlideDeleteListener) context;
        mcontext = context;
    }

    GoodOrder order = null;

    @Override
    public void onBind(Object item) {
        super.onBind(item); //1568771440563
         order = (GoodOrder)item;
        //goodmodeid = String.valueOf(order.getId());
        buyer.setText(order.getBuyer());
        name.setText(order.getPositionname());
        price.setText(order.getPrice());
        weight.setText(order.getWeight());
        SimpleDateFormat _YmdHmFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        String time = _YmdHmFormat.format(order.getTime());
        Date date = order.getTime();
        long tim = date.getTime();

        tv_ordertime.setText("下单时间："+time);
        Double totals = Double.parseDouble(order.getWeight()) * Double.parseDouble(order.getPrice());
        total.setText(totals.toString());

        if (order.getIsbuy().equals("1")){
            is_buy.setText("已付款");
            is_buy.setBackgroundResource(R.color.white_f);
        }else {
            is_buy.setText("未付款");
            is_buy.setBackgroundResource(R.color.bt_order_bg);
        }

       // titles.setText(goodmode.getName());
    }

    @Override
    public void onPropertyChanged(Model model, String propertyName) {
        switch (propertyName) {
            case "updata":
             //   is_buy.setText("已付款");
              //  is_buy.setBackgroundResource(R.color.white_f);
                break;
            case "sum":
               // setSumCtrl();
                break;
        }
    }


    @Override
    public void onUnBind() {
        super.onUnBind();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.delete){
            long id = order.getId();
            GoodOrder order1 = GoodOrder.findById(GoodOrder.class,id);
            boolean del = order1.delete();
            if (del)
            listener.ondelet(order);
        }else if (v.getId() == R.id.is_buy){
            if (order.getIsbuy().equals("1")){
                return;
            }

            AlertDialog.Builder dialog = new AlertDialog.Builder(mcontext);
            dialog.setMessage("确认是否付款？");
            dialog.setTitle("提示");
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    long id = order.getId();
                    GoodOrder order1 = GoodOrder.findById(GoodOrder.class,id);
                    order1.setIsbuy("1");
                    long update = order1.save();   // updata 不能用该方法
                    if (update > 0){
                        is_buy.setText("已付款");
                        is_buy.setBackgroundResource(R.color.white_f);
                    }

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



    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
