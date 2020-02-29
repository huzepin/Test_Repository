package com.cn.hzp.pos.dao.impl;


import com.cn.hzp.pos.bean.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderDao {
    /**
     * 根据 商品条码查询商品信息
     * @param no
     * @return
     */
    public Order queryOrderByNo(String no);

    /**
     * 获取所有的商品信息
     * @return
     */
    public List<Order> queryOrderAll();

    /**
     * 新增商品
     *
     * @param good
     */
    public int insertOrder(Order good);

    /**
     * 新增多个商品
     *
     * @param good
     */
    public int insertOrders(ArrayList<Order> good);
    /**
     * 更新用户信息
     *
     * @param good
     */
    public void updateOrder(Order good);

    /**
     * 根据no删除商品信息
     *
     * @param no
     */
    public void deleteOrder(String no);

}
