package com.example.phone_9.gosn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.phone_9.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

/**
 * @author admin
 * @time 2019/7/24 14
 */
public class Gosn_test extends AppCompatActivity {

    private Button bt_gosn_start,bt_gosn_array;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gosn_test);

        bt_gosn_array = findViewById(R.id.bt_gosn_array);
        bt_gosn_start = findViewById(R.id.bt_gosn_start);

        bt_gosn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonObject();
               //序列化
                User user = new User("huzeping",22,"男");
                Gson gson = new Gson();
                String json =  gson.toJson(user);
                Log.d("Tag",json);
               //反序列化
                String data =  "{'age':22,'userName':'huzeping','sex':'男'}";
                User object = gson.fromJson(data,User.class);
                Log.d("Tag",object.name);
            }
        });


        bt_gosn_array.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                josnArray();
                gosnToArray();
            }
        });
    }


    private void jsonObject(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("String", "leavesC");
        jsonObject.addProperty("Number", 23);
        jsonObject.addProperty("Number", 22.9);
        jsonObject.addProperty("Boolean", true);
        jsonObject.addProperty("Char", 'c');

        JsonObject jsonElement = new JsonObject();
        jsonElement.addProperty("Boolean", false);
        jsonElement.addProperty("Double", 25.9);
        jsonElement.addProperty("Char", 'c');
        jsonObject.add("JsonElement", jsonElement);

        Log.v("Tag","====1"+jsonObject);
    }

    private void josnArray(){
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("hu");
        jsonArray.add("hu12");
        jsonArray.add("hu13");
        jsonArray.add("hu14");
        jsonArray.add("hu15");

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("JsonElement",jsonArray);

        Log.v("Tag","====2"+jsonObject);
    }

    private void gosnToArray(){  //Json数组 转为 字符串数组
        Gson gson = new Gson();
        String jsonArray = "['https://github.com/leavesC','https://www.jianshu.com/u/9df45b87cfdf','Java','Kotlin','Git','GitHub']";
        String[] strings = gson.fromJson(jsonArray, String[].class);
        for (String string : strings) {
          //  Log.v("Tag","====json数组转字符串数组："+string);
        }
        //字符串数组 转为 Json数组
        jsonArray = gson.toJson(strings, String[].class);
        //Log.v("Tag","====字符串数据转json数组："+jsonArray);
        //json数组转list
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>(){
        }.getType());
        Log.v("Tag","====json数组转list："+stringList.size()+"="+stringList.get(0));
        //list转json数组
        jsonArray = gson.toJson(stringList, new TypeToken<List<String>>() {
        }.getType());
        Log.v("Tag","====list转json数组："+jsonArray);

    }
}
