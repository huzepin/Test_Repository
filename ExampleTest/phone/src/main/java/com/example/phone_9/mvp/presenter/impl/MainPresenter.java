package com.example.phone_9.mvp.presenter.impl;

import com.example.phone_9.mvp.HttpUntil;
import com.example.phone_9.mvp.model.Phone;
import com.example.phone_9.mvp.presenter.MvpMainView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @time 2019/8/13 13
 */
public class MainPresenter extends BasePresenter {
    private MvpMainView mvpMainView;
    private String mUrl = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
    private Phone mPhone;

    public Phone getPhoneInfo() {
        return mPhone;
    }

    public MainPresenter(MvpMainView mainView) {
        mvpMainView = mainView;
    }

    public void searchPhoneInfo(String phone) {
        if (phone.length() != 11) {
            mvpMainView.showToash("请输入正确的手机号");
            return;
        }
        mvpMainView.showLoading(); //http请求的处理逻辑
        sendHttp(phone);
    }

    private void sendHttp(String phone) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("tel", phone);
        HttpUntil httpUntil = new HttpUntil(new HttpUntil.HttpResonse() {
            @Override
            public void onSuccess(Object object) {
                String json = object.toString();
                int index = json.indexOf("{");
                json = json.substring(index, json.length());                 //JsonObject//
               // mPhone = parseModelWithOrgJson(json);                //Gson
                     mPhone = parseModelWithGson(json);                //FastJson//
                      //  mPhone = parseModelWithFastJson(json);
                        mvpMainView.hideLoading();
                        mvpMainView.updateView();
            }
            @Override
            public void onFail(String error) {
                mvpMainView.showToash(error);
                mvpMainView.hideLoading();
            }
        });
        httpUntil.sendGetHttp(mUrl, map);

    }

    public Phone parseModelWithGson(String json) {
        Gson gson = new Gson();
        Phone phone = gson.fromJson(json, Phone.class);
        return phone;
    }

    public Phone parseModelWithFastJson(String json) {
        Gson gson = new Gson();
        Phone phone = gson.fromJson(json, Phone.class);
       // Phone phone = ()   //JSONObject.parseObject(json, Phone.class);
        return phone;
    }

    public Phone parseModelWithOrgJson(String json) {
        Phone phone = new Phone();
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(json);
            String value = jsonObject.getString("telString");
            phone.setTelString(value);
            value = jsonObject.getString("province");
            phone.setProvince(value);
            value = jsonObject.getString("catName");
            phone.setCatName(value);
            value = jsonObject.getString("carrier");
            phone.setCarrier(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return phone;
    }


}
