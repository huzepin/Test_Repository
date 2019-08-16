package com.example.phone_9.mvp;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author admin
 * @time 2019/8/13 13
 */
public class HttpUntil {
    String mUrl;
    Map<String, String> mParam;
    HttpResonse mHttpResonse;
    Handler myHandler = new Handler(Looper.getMainLooper());
    private final OkHttpClient client = new OkHttpClient();

    public HttpUntil(HttpResonse httpResonse) {
        mHttpResonse = httpResonse;
    }

    public interface HttpResonse {
        void onSuccess(Object object);

        void onFail(String error);
    }

    public void sendPostHttp(String url, Map<String, String> param) {
        sendHttp(url, param, true);
    }

    public void sendGetHttp(String url, Map<String, String> param) {
        sendHttp(url, param, false);
    }

    public void sendHttp(String url, Map<String, String> param, boolean isPost) {
        mUrl = url;
        mParam = param;
        run(isPost);
    }

    private void run(boolean isPost) {
        Request request = createRequest(isPost);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mHttpResonse != null) {
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mHttpResonse.onFail("Fail");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (mHttpResonse == null) return;
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!response.isSuccessful()) {
                            mHttpResonse.onFail("fail,code:" + response);
                        } else {
                            try {
                                mHttpResonse.onSuccess(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                                mHttpResonse.onFail("转换失败");
                            }
                        }
                    }
                });
            }
        });
    }

    private Request createRequest(boolean isPost) {
        Request request;
        if (isPost) {
            MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder();
            requestBodyBuilder.setType(MultipartBody.FORM);            //遍历map请求参数
            Iterator<Map.Entry<String, String>> iterator = mParam.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                requestBodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
            }
            request = new okhttp3.Request.Builder().url(mUrl).post(requestBodyBuilder.build()).build();
        } else {
            String urlStr = mUrl;            //带参请求
            if (mParam != null) {
                urlStr = mUrl + "?" + MapParamToString(mParam);
            }
            request = new Request.Builder().url(urlStr).build();
        }
        return request;
    }

    private String MapParamToString(Map<String, String> param) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = param.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            stringBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        String str = stringBuilder.toString().substring(0, stringBuilder.length());
        return str;
    }

}
