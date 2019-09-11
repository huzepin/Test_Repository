package cn.com.coolsoft.notebook.bean;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * @author admin
 * @time 2019/9/9 14
 */
public class GoodPosition extends SugarRecord {

    private long modeid; //模板id
    String name ;  //名字
    String price;  //价格
    String unit;   //单位      RMB
    String weight;  //重量     克，千克，件

    public GoodPosition() {

    }

    public GoodPosition(long modeId, String name, String price,String weight) {
        this.modeid = modeId;
        this.name = name;
        this.price = price;
        this.unit = "RMB";
        this.weight = weight;
    }

    public long getModeId() {
        return modeid;
    }

    public void setModeId(long modeId) {
        this.modeid = modeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
