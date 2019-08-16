package com.example.phone_9.gosn;

import com.google.gson.annotations.SerializedName;

/**
 * @author admin
 * @time 2019/7/24 15
 */
public class User {
    @SerializedName(value = "userName", alternate = {"user_name", "Name"})  // 当反序列化时 属性值不是name 可用多个备选属性名
    public String name;
    public int age;
    public String sex;

    public User(String name,int age,String sex){
        this.name= name;
        this.age = age;
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
