package cn.com.coolsoft.notebook.bean;

import com.orm.SugarRecord;

/**
 * @author admin
 * @time 2019/9/11 13
 */
public class GoodOrder extends SugarRecord {

    private String modelid; //模板id
    private String positionid; // 部位 id    可为空
    private String positionname; // 部位名称
    private String buyer;  // 购买人
    private String price;  // 价格
    private String weight;  //重量或件
    private String isbuy;  // 是否付款  1 已付款
    private String time;  //下单时间

    public GoodOrder(){}

    public GoodOrder(String modelid, String positionid,String positionname, String buyer, String price, String weight,String isbuy,String time) {
        this.modelid = modelid;
        this.positionid = positionid;
        this.positionname = positionname;
        this.buyer = buyer;
        this.price = price;
        this.weight = weight;
        this.isbuy = isbuy;
        this.time = time;
    }



    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getPositionid() {
        return positionid;
    }

    public void setPositionid(String positionid) {
        this.positionid = positionid;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIsbuy() {
        return isbuy;
    }

    public void setIsbuy(String isbuy) {
        this.isbuy = isbuy;
    }

    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
