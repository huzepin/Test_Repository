package com.cn.hzp.pos.controller;

import com.cn.hzp.pos.bean.User;
import com.cn.hzp.pos.dao.impl.UserDao;
import com.cn.hzp.pos.utils.Constant;
import com.cn.hzp.pos.utils.MD5Util;
import com.cn.hzp.pos.utils.Util;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.cs.US_ASCII;
import sun.text.resources.FormatData;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* code
 *  99 参数错误
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends PublicController{
   // @Autowired
  //  private UserDao userDao;
// http://localhost:8080/user/login?password=123456&phone=15979978803
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("phone") String phone,@RequestParam("password") String pw) throws JSONException {
        JSONObject obj = new JSONObject();
        if (!phone.equals("") && !pw.equals("")){
            User result = userDao.phoneAndPw(phone, MD5Util.MD5EncodeUtf8(pw));
            if (result != null){
                  User user = new User();
                  user.setPhone(phone);
                  String token = Util.createToken(phone);
                  user.setToken(token);
                  userDao.updateUser(user);
                obj.put("code",Constant.CODE_SUCCESS);
                obj.put("msg",Constant.STRING_SUCCESS);
                obj.put("token",token);
                obj.put("phone",result.getPhone());
                obj.put("nickname",result.getNickname());
                obj.put("sex",result.getSex());
                obj.put("types",result.getTypes());
                obj.put("mail",result.getMail());
                obj.put("img_url",result.getImg_url());
                return obj.toString();
            }else {
                obj.put("code", Constant.CODE_PARAM_ERROR);
                obj.put("msg",Constant.STRING_PARAM_ERROR);
                return obj.toString();
            }

        }else {
            obj.put("code", Constant.CODE_PARAM_NULL);
            obj.put("msg",Constant.STRING_PARAM_NULL);
            return obj.toString();
        }
    }

    @RequestMapping(value = "/tokenlogin", method = RequestMethod.POST)
    public String tokenlogin(@RequestParam("token") String token) throws JSONException, IOException {
        JSONObject obj = new JSONObject();
 //       if (!token.equals("")){
//            String tokens = Util.decoderToken(token);
//            if (tokens.contains("-") == false){
//                obj.put("code", Constant.CODE_PARAM_ERROR);
//                obj.put("msg",Constant.STRING_PARAM_ERROR);
//                return obj.toString();
//            }
//            String time = tokens.split("-")[0];
//            long overtime = System.currentTimeMillis() - Long.parseLong(time);
//            if (overtime/1000/3600/24 > 30){
//                obj.put("code", Constant.CODE_PARAM_TOKEN);
//                obj.put("msg",Constant.STRING_TOKEN_ERROR);
//                return obj.toString();
//            }
//            User result = userDao.token(token);
            PublicResult publicResult =isToken(token);
           if (publicResult.isSuccess){
              User result = publicResult.user;
            if (result != null){
                obj.put("code",Constant.CODE_SUCCESS);
                obj.put("msg",Constant.STRING_SUCCESS);
                obj.put("token",result.getToken());
                obj.put("phone",result.getPhone());
                obj.put("nickname",result.getNickname());
                obj.put("sex",result.getSex());
                obj.put("types",result.getTypes());
                obj.put("mail",result.getMail());
                obj.put("img_url",result.getImg_url());
                return obj.toString();
            }else {
                obj.put("code", Constant.CODE_PARAM_ERROR);
                obj.put("msg",Constant.STRING_PARAM_ERROR);
                return obj.toString();
            }

        }else {
            return publicResult.msg;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("phone") String phone,@RequestParam("password") String pw,@RequestParam("sex") String sex) throws JSONException {
        JSONObject obj = new JSONObject();
        if (!phone.equals("") && !pw.equals("")) {
            User result = userDao.queryUserByPhone(phone);
            if (result == null){
                String token = Util.createToken(phone);
                User user = new User();
                user.setPhone(phone);
                user.setPassword(MD5Util.MD5EncodeUtf8(pw));
                user.setSex(sex);
                user.setNickname("小宝");
                user.setCreate_time(Constant.f.format(new Date()));
                user.setLast_login_date(Constant.f.format(new Date()));
                user.setImg_url("");
                user.setTypes("0");
                user.setMail("");
                user.setToken(token);
                int insert = userDao.insertUser(user);
                if (insert > 0){
                    obj.put("code",Constant.CODE_SUCCESS);
                    obj.put("msg",Constant.STRING_SUCCESS);
//                    obj.put("token",token);
//                    obj.put("phone",user.getPhone());
//                    obj.put("nickname",user.getNickname());
//                    obj.put("sex",user.getSex());
//                    obj.put("types",user.getTypes());
//                    obj.put("mail",user.getMail());
//                    obj.put("img_url",user.getImg_url());
                    return obj.toString();
                }else {
                    obj.put("code", Constant.CODE_OTHER);
                    obj.put("msg",Constant.STRING_OTHER);
                    return obj.toString();
                }

            }else {
                obj.put("code", Constant.CODE_PARAM_ERROR);
                obj.put("msg",Constant.STRING_USER_SAVE);
            }
             return obj.toString();
        }else {
            obj.put("code", Constant.CODE_PARAM_NULL);
            obj.put("msg",Constant.STRING_PARAM_NULL);
            return obj.toString();
        }

    }






}
