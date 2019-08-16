package com.example.phone_9.mvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phone_9.R;
import com.example.phone_9.mvp.model.Phone;
import com.example.phone_9.mvp.presenter.MvpMainView;
import com.example.phone_9.mvp.presenter.impl.MainPresenter;

/**
 * @author admin
 * @time 2019/8/13 13
 */
public class MainActivity extends AppCompatActivity implements MvpMainView, View.OnClickListener {

     MainPresenter mainPresenter;
     private LinearLayout mShowInfoLayout;
     private EditText et_phone;
     private TextView result_phone,result_province,result_type,result_carrier;
     private ProgressDialog   progressDialog;
     private Button search ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_activity);
        initview();
    }

    private void initview() {
        mShowInfoLayout = findViewById(R.id.ll_show);
        result_phone = findViewById(R.id.tv_phone);
        result_carrier = findViewById(R.id.tv_carrier);
        result_province = findViewById(R.id.tv_province);
        result_type = findViewById(R.id.tv_type);
        et_phone = findViewById(R.id.et_phone);
        search = findViewById(R.id.bt_query);
        search.setOnClickListener(this);
    }

    @Override
    public void showToash(String msg) {

    }

    @Override
    public void updateView() {
        Phone phone = mainPresenter.getPhoneInfo();
        if (phone != null) {
            mShowInfoLayout.setVisibility(View.VISIBLE);
            result_phone.setText("手机号：" + phone.getTelString());
            result_province.setText("省份：" + phone.getProvince());
            result_type.setText("运营商：" + phone.getCatName());
            result_carrier.setText("归属运营商：" + phone.getCarrier());
        } else {
            mShowInfoLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, "", "正在加载", true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("正在加载...");
        }
        progressDialog.show();

    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()){

            progressDialog.dismiss();

        }
    }

    @Override
    public void onClick(View v) {
        mainPresenter.searchPhoneInfo(et_phone.getText().toString());
    }
}
