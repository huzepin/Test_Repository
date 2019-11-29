package com.cn.hzp.pos.dao.impl;

import com.cn.hzp.pos.bean.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodDao {
    /**
     * 根据 商品条码查询商品信息
     * @param no
     * @return
     */
    public Good queryGoodByNo(String no);

    /**
     * 获取所有的商品信息
     * @return
     */
    public List<Good> queryGoodAll();

    /**
     * 新增商品
     *
     * @param good
     */
    public int insertGood(Good good);

    /**
     * 更新用户信息
     *
     * @param good
     */
    public void updateGood(Good good);

    /**
     * 根据no删除商品信息
     *
     * @param no
     */
    public void deleteGood(String no);

}
