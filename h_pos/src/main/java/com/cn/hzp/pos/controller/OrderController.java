package com.cn.hzp.pos.controller;

import com.alibaba.fastjson.JSON;
import com.cn.hzp.pos.bean.Good;
import com.cn.hzp.pos.bean.Order;
import com.cn.hzp.pos.dao.impl.GoodDao;
import com.cn.hzp.pos.dao.impl.OrderDao;
import com.cn.hzp.pos.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderController extends PublicController {
    @Autowired
    public OrderDao orderDao;



    public String queryOrder(@RequestParam("token")String token, @RequestParam("no")String no) throws IOException, JSONException {
        PublicController.PublicResult publicResult = isToken(token);
        PublicController.PublicResult isNull  =isNull(no);
        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        logger.info("token="+token+"no="+no);
        if (publicResult.isSuccess && isNull.isSuccess) {
            Order order = orderDao.queryOrderByNo(no);
            JSONObject rt = new JSONObject();
            if (order != null){
                JSON json = (JSON) JSON.toJSON(order);
                return json.toJSONString();
            }else {
                rt.put("code", Constant.CODE_NO_GOOD);
                rt.put("msg",Constant.STRING_GOOD_NO);
                return rt.toString();
            }
        }else {
            if (isNull.isSuccess == false){
                return  isNull.msg;
            }
            return publicResult.msg;
        }
    }


}
