package com.cn.springboot.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
    /*
    user.setUserId("xcbeyond");
        user.setUserName("xcbeyond");
        user.setSex("F");
        user.setAge(18);
     */

    private String userid;
    private String username;
    private String age;
    private String sex;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
