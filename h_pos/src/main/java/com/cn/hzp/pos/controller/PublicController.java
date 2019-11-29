package com.cn.hzp.pos.controller;

import com.cn.hzp.pos.bean.User;
import com.cn.hzp.pos.dao.impl.UserDao;
import com.cn.hzp.pos.utils.Constant;
import com.cn.hzp.pos.utils.Util;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;


public class PublicController {
    @Autowired
    public UserDao userDao;

    static class PublicResult{
      public  Boolean isSuccess;
      public  String msg;
      public  User user;
  //    public Map<String,Object> stringMap;
    }

    /**
     * 每条接口 都要token验证一下
     * @param token
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public PublicResult isToken(String token) throws IOException, JSONException {
        JSONObject obj = new JSONObject();
        PublicResult result = new PublicResult();
        if (!token.equals("") && token != null) {
            String tokens = Util.decoderToken(token);
            if (tokens.contains("-") == false) {
                obj.put("code", Constant.CODE_PARAM_ERROR);
                obj.put("msg", Constant.STRING_PARAM_ERROR);
                result.msg = obj.toString();
                result.isSuccess = false;
                return result;
            }
            String time = tokens.split("-")[0];
            long overtime = System.currentTimeMillis() - Long.parseLong(time);
            if (overtime / 1000 / 3600 / 24 > 30) {
                obj.put("code", Constant.CODE_PARAM_TOKEN);
                obj.put("msg", Constant.STRING_TOKEN_ERROR);
                result.msg = obj.toString();
                result.isSuccess = false;
                return result;
            }
            User user = userDao.token(token);
            if (user == null){
                obj.put("code", Constant.CODE_PARAM_TOKEN);
                obj.put("msg", Constant.STRING_TOKEN_ERROR);
                result.msg = obj.toString();
                result.isSuccess = false;
                return result;
            }
            result.msg = obj.toString();
            result.user = user;
            result.isSuccess = true;
            return result;
        }else {
            obj.put("code", Constant.CODE_PARAM_ERROR);
            obj.put("msg", Constant.STRING_PARAM_ERROR);
            result.msg = obj.toString();
            result.isSuccess = false;
            return result;
        }
    }

    /**
     * 判断是否为空
     * @param data
     * @return
     * @throws JSONException
     */
    public PublicResult isNull(String data) {
        JSONObject obj = new JSONObject();
        PublicResult result = new PublicResult();
        if (data.length() == 0){
            try {
                obj.put("code", Constant.CODE_PARAM_ERROR);
                obj.put("msg", Constant.STRING_PARAM_ERROR);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            result.msg = obj.toString();
            result.isSuccess = false;
            return result;
        }else {
            result.isSuccess = true;
            return result;
        }
    }
    /**
     * 判断是否为空
     * @param data
     * @return
     * @throws JSONException
     */
    public PublicResult isNull(Map data) {
        JSONObject obj = new JSONObject();
        PublicResult result = new PublicResult();
        if (data.size() == 0){
            try {
                obj.put("code", Constant.CODE_PARAM_ERROR);
                obj.put("msg", Constant.STRING_PARAM_ERROR);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            result.msg = obj.toString();
            result.isSuccess = false;
            return result;
        }else {
            result.isSuccess = true;
            return result;
        }
    }


}
