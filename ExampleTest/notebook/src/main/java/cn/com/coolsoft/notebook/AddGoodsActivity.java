package cn.com.coolsoft.notebook;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TooManyListenersException;

import cn.com.coolsoft.notebook.bean.BaseAdapter;
import cn.com.coolsoft.notebook.bean.GoodModel_ItemHolder;
import cn.com.coolsoft.notebook.bean.GoodOrder;
import cn.com.coolsoft.notebook.bean.GoodOrder_ItemHolder;
import cn.com.coolsoft.notebook.bean.GoodPosition;
import cn.com.coolsoft.notebook.bean.Goodmode;
import cn.com.coolsoft.notebook.bean.ListModel;
import cn.com.coolsoft.notebook.utils.SlideRecyclerView;

/**
 * @author admin
 * @time 2019/9/9 17
 */
public class AddGoodsActivity extends AppCompatActivity implements View.OnClickListener, GoodOrder_ItemHolder.OnClickSlideDeleteListener {

    private AppCompatSpinner mSpinner;
    private ArrayList mData;
    private ArrayAdapter adapter;
    private TextView Model_name,tv_name,tv_goodprice,tv_unit;
    private ImageView add_position;
    private Button Save;
    private EditText et_buyer,et_weight;
    private SlideRecyclerView add_list;


    private String GoodModel_id = null;
    private String GoodModel_name = null;
    private String Good_name = null;

    private Intent intent = null;
    List<GoodPosition> goods = null;
    private String price = "";
    private String positionid = "";
    private String positionname = "";

    private BaseAdapter baseAdapter=null;
    private ListModel listModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgoods_activity);
        intent = getIntent();
        GoodModel_id = intent.getStringExtra("id");
        Good_name = intent.getStringExtra("goodName");
        GoodModel_name = intent.getStringExtra("modelName");
        initView();
        List<GoodOrder> goodorders = GoodOrder.find(GoodOrder.class,"modelid = ?",GoodModel_id);
        listModel = new ListModel();
        listModel.list = (ArrayList) goodorders;
        baseAdapter = new BaseAdapter(listModel, GoodOrder_ItemHolder.class,R.layout.order_item,this);

        add_list.setLayoutManager(new LinearLayoutManager(this));
        add_list.setHasFixedSize(true);
        add_list.setAdapter(baseAdapter);

        add_list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
                outRect.bottom = 2;
            }
        });



         goods = GoodPosition.find(GoodPosition.class, "modeid = ?", GoodModel_id);

        if (goods.size() == 1 && goods.get(0).getName().equals("")){
            mSpinner.setVisibility(View.INVISIBLE);
            add_position.setVisibility(View.GONE);
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
        if (mSpinner.getVisibility() == View.VISIBLE) {
            mSpinner.setAdapter(adapter);
        }
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                show_price_unit(position);
                parent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    private void initView() {

        Model_name = findViewById(R.id.tv_Model_name);
        add_position = findViewById(R.id.iv_add_goodposition);
        mSpinner = findViewById(R.id.position);
        tv_name = findViewById(R.id.tv_name);
        tv_goodprice = findViewById(R.id.tv_price);
        tv_unit = findViewById(R.id.tv_unit);
        Model_name.setText(GoodModel_name);
        tv_name.setText(Good_name);
        Save = findViewById(R.id.bt_addsave);
        et_buyer = findViewById(R.id.et_buyer);
        et_weight = findViewById(R.id.et_unit);
        add_list = findViewById(R.id.ry_add_list);
        Save.setOnClickListener(this);
        add_position.setOnClickListener(this);
    }

    private void show_price_unit(int i){
        price = goods.get(i).getPrice();
        positionid = String.valueOf(goods.get(i).getId());
        positionname = String.valueOf(goods.get(i).getName());
        tv_goodprice.setText("价格："+price+"/"+goods.get(i).getWeight());
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
        if (v.getId() == R.id.iv_add_goodposition) { //添加商品小部分
            Intent intent = new Intent(this, EditModelDialogActivity.class);
            intent.putExtra("type", "goodposition");
            intent.putExtra("model_id", GoodModel_id);
            intent.putExtra("good_name", Good_name);
            startActivityForResult(intent, 10);

        }else if (v.getId() == R.id.bt_addsave){  //保存订单
           String buyer = et_buyer.getText().toString().trim();
           String weight = et_weight.getText().toString().trim();
           if (weight.equals("") || weight.length() ==0){
               Toast.makeText(this,"重量或件数必填",Toast.LENGTH_SHORT).show();
               return;
           }
           if (buyer.equals("") || buyer.length() ==0){
               buyer = "陌生人";
           }

            SimpleDateFormat _YmdHmFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
            String time = _YmdHmFormat.format(new Date());

            GoodOrder goodOrder = new GoodOrder(GoodModel_id,positionid,positionname,buyer,price,weight,"0",time);
            long status = goodOrder.save();
            if (status > 0){
                listModel.addItem(goodOrder);
                add_list.scrollToPosition(listModel.itemCount()-1);
            }

        }
    }

    @Override
    public void ondelet(Object o) {
        GoodOrder goodOrder = (GoodOrder) o;
        listModel.removeItem(goodOrder);
    }
}
