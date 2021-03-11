package cn.com.hzp.selfsale.selfsale.controller;

import cn.com.hzp.selfsale.selfsale.bean.User;
import cn.com.hzp.selfsale.selfsale.server.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;
import sun.rmi.runtime.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/self")
public class TestController {
    /**
     * 测试数据库连接
     */
    @Autowired
    private IUserService userSerivce;


    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot!";
    }


    @GetMapping("/addUser")
    public String addUser(){

        String pw = "hu123456";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] md5 = md.digest(pw.getBytes());
        BASE64Encoder be = new BASE64Encoder();
        String user_PW = be.encode(md5);
        User user = new User();
        user.setName("huzeping");
        user.setPhone("15979978803");
        user.setPassword(user_PW);
        user.setAvailable("1");
        int code = 0;
        try {
             code = userSerivce.create(user);
        }catch (Exception e){
            System.out.println("自定义的打印==========="+e.getCause().getMessage());
        }

        if (code > 0){
            return "创建成功"+user.getName();
        }
        return "创建失败";
    }
}
