package com.cn.hzp.pos.utils;

import java.text.SimpleDateFormat;

public class Constant {
    public static int CODE_PARAM_ERROR = 99; //参数错误
    public static int CODE_PARAM_NULL  = 90; //参数不能为空
    public static int CODE_PARAM_LENGTH = 91;
    public static int CODE_SUCCESS = 200;
    public static int CODE_ERROR = 0;
    public static int CODE_OTHER = 2;
    public static int CODE_NO_GOOD = 5;
    public static int CODE_PARAM_TOKEN = 3;
    public static int CODE_GOOD_SAVE = 4;

    public static String STRING_PARAM_ERROR="参数错误";
    public static String STRING_PARAM_FORMAT_ERROR="参数格式错误";
    public static String STRING_PARAM_NULL="参数不能为空";
    public static String STRING_PARAM_LENGTH="参数长度错误";
    public static String STRING_PARAM_E="参数错误";
    public static String STRING_SUCCESS="请求成功";
    public static String STRING_ERROR="请求失败";
    public static String STRING_USER_SAVE="用户已存在";
    public static String STRING_GOOD_SAVE="商品已存在";
    public static String STRING_GOOD_NO="商品不存在";
    public static String STRING_OTHER="未知错误";
    public static String STRING_TOKEN_ERROR="token过期";

   public static   SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
