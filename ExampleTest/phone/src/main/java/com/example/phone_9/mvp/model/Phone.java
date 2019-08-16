package com.example.phone_9.mvp.model;

/**
 * @author admin
 * @time 2019/8/13 11
 */
public class Phone {
    private String telString;
    private String province;
    private String catName;
    private String carrier;

    public String getTelString() {
        return telString;
    }

    public void setTelString(String telString) {
        this.telString = telString;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

}
