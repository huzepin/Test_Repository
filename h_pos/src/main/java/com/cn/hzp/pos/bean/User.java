package com.cn.hzp.pos.bean;

public class User {
    /*
     `phone` varchar(11) NOT NULL,
  `password` varchar(50) NOT NULL,
  `type` smallint(6) DEFAULT NULL,
  `create_time` date NOT NULL COMMENT '注册时间',
  `nickname` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `last_login_date` date DEFAULT NULL,
  `token` varchar(256) DEFAULT NULL,
  `sex` smallint(1) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `weixin` varchar(50) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
     */
    private String phone;
    private String password;
    private String types;
    private String create_time;
    private String nickname;
    private String img_url;
    private String last_login_date;
    private String token;
    private String sex;
    private String qq;
    private String weixin;
    private String mail;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(String last_login_date) {
        this.last_login_date = last_login_date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", type='" + types + '\'' +
                ", create_time='" + create_time + '\'' +
                ", nickname='" + nickname + '\'' +
                ", img_url='" + img_url + '\'' +
                ", last_login_date='" + last_login_date + '\'' +
                ", token='" + token + '\'' +
                ", sex='" + sex + '\'' +
                ", qq='" + qq + '\'' +
                ", weixin='" + weixin + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
