package cn.com.coolsoft.notebook.bean;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * @author admin
 * @time 2019/9/9 14
 */
public class Goodmode extends SugarRecord {

    @Unique  // name 是唯一值，如新增有重复的就是重写该条数据
   private String name;     //模板名字
   private String goodname;  //商品名字
   // Long positionId;  //部位 id
   private String time;     //创建时间

    public Goodmode(){}

    public Goodmode(String name ,String goodname,String time){
        this.name = name;
        this.goodname = goodname;
       // this.positionId = position;
        this.time = time;
    }
    @Override
    public String toString() {
        return "Goodmode{" +
                "name='" + name + '\'' +
                ", goodname='" + goodname + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
