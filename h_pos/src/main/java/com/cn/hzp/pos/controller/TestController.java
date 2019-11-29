package com.cn.hzp.pos.controller;

import com.cn.hzp.pos.bean.User;
import com.cn.hzp.pos.dao.impl.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/addUser")
    public int addUser(){

        User user = new User();
        user.setPhone("15979978833");
        user.setImg_url("");
        user.setPassword("123456");
        user.setSex("0");
        user.setLast_login_date("2019-11-27 14:58");
        user.setCreate_time("2019-11-27 14:22");
        user.setMail("1597997848545@163.com");
        user.setNickname("胡显兮");

        User result = userDao.queryUserByPhone(user.getPhone());
        int index=0;
        if (result == null) {
          //  index = userDao.insertUser(user);
        }
        return index;
    }


























































}
