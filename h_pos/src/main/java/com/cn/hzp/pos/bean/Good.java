package com.cn.hzp.pos.bean;

import java.math.BigDecimal;

public class Good {
    public String flowId;
    public String no;             //商品条码
    public String name;           //名称
    public String unit;           //单位
    public Double originalPrice;//原价
    public Double salePrice;  //销售价格 （会员价：当原价与现价不一）
    public Double quantity;   //单品数量
    public Double amount;    //非单品总金额

    public String isFresh;       //是否生鲜
//    public String specFlag;       //规格标志
//    public String specSheetNo;    //规格表编号
//    public String specSheetNoPR;  //规格 价格
//    public String prefAmountPR;// 总金额
//    public String remark; //标记


    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIsFresh() {
        return isFresh;
    }

    public void setIsFresh(String isFresh) {
        this.isFresh = isFresh;
    }

    @Override
    public String toString() {
        return "Good{" +
                "flowId='" + flowId + '\'' +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", originalPrice=" + originalPrice +
                ", salePrice=" + salePrice +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", isFresh='" + isFresh + '\'' +
                '}';
    }
}
