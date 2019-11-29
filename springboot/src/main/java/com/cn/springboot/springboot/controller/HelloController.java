package com.cn.springboot.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.cn.springboot.springboot.bean.NeoProperties;
import com.cn.springboot.springboot.bean.User;
import com.cn.springboot.springboot.dao.UserDao;
import com.cn.springboot.springboot.dao.impl.UserDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.Date;

@RestController
public class HelloController {
    @Autowired
    private UserDao userDao;
    @RequestMapping("/getUser")
    public User getUser() {
        NeoProperties properties = new NeoProperties();
        User user=new User();
        user.setUsername("小明");
        user.setAge("22");
        return user; //{"username":"小明","password":"xxxx"}
    }

    @RequestMapping(value = "/testthymeleaf")
    public ModelAndView testthymeleaf(ModelAndView mv)
    {
        mv.setViewName("thymeleaf"); //页面文件名
        mv.addObject("name","欢迎使用Thymeleaf!");
        return mv;
    }


    /** * 通过JdbcTemplate方式插入用户信息* @return*
     */
    @RequestMapping("/insertUserByJdbcTemplate")
    public String insertUserByJdbcTemplate()
    {

        User user = new User();
        user.setUserid("3");
        user.setUsername("胡先生");
        user.setAge("23");
        user.setSex("男");
        int ret = userDao.insertUser(user);
        return String.valueOf(ret);
    }

    @RequestMapping("/selectUserByJdbcTemplate")
    public String selectUserByJdbcTemplate(){
        return userDao.queryUserAll().toString();
    }

    @RequestMapping(value="/queryUserByUserid", method= RequestMethod.GET)
    public String queryUserByUserid(@RequestParam("userid") String userid) {
        User user = userDao.queryUserById(userid);
        return user.getUsername()+"="+user.getAge();
    }

    //接收json 字符数据 输出json 数据
    @RequestMapping(value = "/active", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String active(@RequestBody JSONObject jsonParam)
    {
        System.out.println(jsonParam.toJSONString());
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data", jsonParam);

        return result.toJSONString();
    }



    @RequestMapping(value="/queryrid", method= RequestMethod.POST)
    public String queryid(@RequestParam("userid") String userid) {
        return userDao.queryUserById(userid).toString();
    }
}
