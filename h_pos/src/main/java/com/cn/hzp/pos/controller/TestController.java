package com.cn.hzp.pos.controller;

import com.cn.hzp.pos.bean.User;
import com.cn.hzp.pos.dao.impl.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

//@RestController 不想返回视图建议使用这个
@Controller
public class TestController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/test")
    public String test(){
        return "thymeleaf";
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

    @RequestMapping("/index")
    public String index(Model model){  // 想显示static文件下的页面 返回 "redirect:/index.html"的路径必须带后缀名  templates文件下的页面无需带后缀名  前提类是 (@Controller)
        model.addAttribute("aaa","我是一个兵");
        model.addAttribute("bbb","来自老百姓！");
        ArrayList<String> data = new ArrayList<>();
        for (int i =0; i < 50; i ++){
            data.add("数据"+i);
        }
        model.addAttribute("list",data);
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){  // 想显示static文件下的页面 返回 "redirect:/index.html"的路径必须带后缀名  templates文件下的页面无需带后缀名  前提类是 (@Controller)
      //  model.addAttribute("aaa","我是一个兵");
      //  model.addAttribute("bbb","来自老百姓！");
        ArrayList<String> data = new ArrayList<>();
        for (int i =0; i < 50; i ++){
            data.add("数据"+i);
        }
      //  model.addAttribute("list",data);
        return "login";
    }

    @RequestMapping("/forms")
    public String forms(Model model){
        return "forms";
    }

    @RequestMapping("/charts")
    public String charts(Model model){
        return "charts";
    }
    @RequestMapping("/register")
    public String register(Model model){
        return "register";
    }

    @RequestMapping("/tables")
    public String tables(Model model){
        return "tables";
    }

    @RequestMapping("/left_nav")
    public String left_nav(Model model){
        return "left_nav";
    }
















































}
