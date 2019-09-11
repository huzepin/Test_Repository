package cn.com.coolsoft.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import cn.com.coolsoft.notebook.bean.GoodPosition;
import cn.com.coolsoft.notebook.bean.Goodmode;
import cn.com.coolsoft.notebook.bean.ListModel;

/**
 * @author admin
 * @time 2019/9/9 17
 */
public class AddGoodsActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatSpinner mSpinner;
    private ArrayList mData;
    private ArrayAdapter adapter;
    private TextView Model_name,tv_name,tv_goodprice,tv_unit;
    private ImageView add_position;

    private String GoodModel_id = null;
    private String GoodModel_name = null;
    private String Good_name = null;

    private Intent intent = null;
    List<GoodPosition> goods = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgoods_activity);
        intent = getIntent();
        GoodModel_id = intent.getStringExtra("id");
        Good_name = intent.getStringExtra("goodName");
        GoodModel_name = intent.getStringExtra("modelName");

        Model_name = findViewById(R.id.tv_Model_name);
        add_position = findViewById(R.id.iv_add_goodposition);
        mSpinner = findViewById(R.id.position);
        tv_name = findViewById(R.id.tv_name);
        tv_goodprice = findViewById(R.id.tv_price);
        tv_unit = findViewById(R.id.tv_unit);
        Model_name.setText(GoodModel_name);
        tv_name.setText(Good_name);
        add_position.setOnClickListener(this);


         goods = GoodPosition.find(GoodPosition.class, "modeid = ?", GoodModel_id);

        if (goods.size() == 1 && goods.get(0).getName().equals("")){
            mSpinner.setVisibility(View.INVISIBLE);
        }
        tv_goodprice.setText("价格："+goods.get(0).getPrice()+"/"+goods.get(0).getWeight());
        tv_unit.setText("单位："+goods.get(0).getWeight());
        mData = new ArrayList();
        for (GoodPosition  str: goods) {
            mData.add(str.getName());
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mData);
       // adapter.setDropDownViewResource(R.drawable.dropdown);

        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //String seleted = (String) mData.get(position);
                show_price_unit(position);
                parent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
               // tv.setText("您没有选择");
            }

        });
    }

    private void show_price_unit(int i){
        tv_goodprice.setText("价格："+goods.get(i).getPrice()+"/"+goods.get(i).getWeight());
        tv_unit.setText("单位："+goods.get(i).getWeight());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 10 && resultCode == 10){
            goods = GoodPosition.find(GoodPosition.class, "modeid = ?", GoodModel_id);
            if (goods != null){
                mData.clear();
                for (GoodPosition  str: goods) {
                    mData.add(str.getName());
                }
                adapter.notifyDataSetChanged();
            }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,EditModelDialogActivity.class);
        intent.putExtra("type","goodposition");
        intent.putExtra("model_id",GoodModel_id);
        intent.putExtra("good_name",Good_name);
        startActivityForResult(intent,10);
    }
}
