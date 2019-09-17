package cn.com.coolsoft.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.com.coolsoft.notebook.bean.GoodPosition;
import cn.com.coolsoft.notebook.bean.Goodmode;

/**
 * @author admin
 * @time 2019/9/9 14
 */
public class EditModelDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_cancel,bt_save;
    private TextView tv_model;
    private EditText Modelname,GoodName,Price,Weight,Goodposition;
    private String type = null;
    private String model_id = null;
    private String goodName = null;
    private Intent intent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editmodel_dialog);
         intent = getIntent();
         type = intent.getStringExtra("type");
         model_id = intent.getStringExtra("model_id");
         goodName = intent.getStringExtra("good_name");
         initView();

    }

    private void initView() {
        Modelname = findViewById(R.id.et_modelname);
        GoodName = findViewById(R.id.et_goodname);
        Goodposition = findViewById(R.id.et_goodposition);
        Price = findViewById(R.id.et_price);
        Weight = findViewById(R.id.et_weight);
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_save = findViewById(R.id.bt_save);
        tv_model = findViewById(R.id.tv_model);
        if (type.equals("goodposition")){
            GoodName.setText(goodName);
            GoodName.setFocusable(false);
            Modelname.setVisibility(View.GONE);
            tv_model.setVisibility(View.GONE);

        }


        bt_save.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_cancel){
            finish();
        }else if (v.getId() == R.id.bt_save) {
            SimpleDateFormat _YmdHmFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
            String time = _YmdHmFormat.format(new Date());

            String modelName = Modelname.getText().toString().trim();
            String goodName = GoodName.getText().toString().trim();

            String goodpostion = Goodposition.getText().toString().trim();
            String price = Price.getText().toString().trim();
            String weight = Weight.getText().toString().trim();
            if (type.equals("model")) {
                if (modelName.equals("") || modelName == null) {
                    Toast.makeText(this, "模板名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

               List<Goodmode> goodmode =Goodmode.find(Goodmode.class,"name = ?",modelName);
                if (goodmode.size() > 0){
                    Toast.makeText(this, "模板名重复，请重新命名", Toast.LENGTH_SHORT).show();
                    return;
                }


            }

            if (goodName.equals("") || goodName == null) {
                Toast.makeText(this, "商品名不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else if (price.equals("") || price == null) {
                Toast.makeText(this, "价格不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else if (weight.equals("") || weight == null) {
                Toast.makeText(this, "重量单位不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
       if (type.equals("model")) {
                Goodmode goodmode = new Goodmode(modelName, goodName, time);
                long status = goodmode.save();
           if (status > 0){
               GoodPosition goodposition = new GoodPosition(goodmode.getId(),goodpostion,price,weight);
               long savaStatus =  goodposition.save();

               //  GoodPosition position = GoodPosition.findById(GoodPosition.class,1);
               //  String name = position.getName();
               if (savaStatus > 0){
                   Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
                   setResult(10);
                   finish();
               }else {
                   Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
               }
           }
           }else if (type.equals("goodposition")){
           GoodPosition goodposition = new GoodPosition(Long.parseLong(model_id),goodpostion,price,weight);

           long savaStatus =  goodposition.save();

           //  GoodPosition position = GoodPosition.findById(GoodPosition.class,1);
           //  String name = position.getName();
           if (savaStatus > 0){
               Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
               setResult(10);
               finish();
           }else {
               Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
           }
       }





        }
    }
}
