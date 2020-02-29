package com.cn.hzp.pos.controller;

import com.alibaba.fastjson.JSON;
import com.cn.hzp.pos.bean.Good;
import com.cn.hzp.pos.bean.User;
import com.cn.hzp.pos.dao.impl.GoodDao;
import com.cn.hzp.pos.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController extends PublicController  {
   @Autowired
   public GoodDao goodDao;

    /**
     *
     * @param token
     * @param no
     * @return
     * {
     *     "no": "2356245123",
     *     "unit": "件",
     *     "quantity": 3,
     *     "originalPrice": 2.3,
     *     "salePrice": 2.3,
     *     "name": "可乐",
     *     "isFresh": "1"
     * }
     * @throws IOException
     * @throws JSONException
     */
   @RequestMapping(value = "/getGood",method = RequestMethod.POST)
   public String getGood(@RequestParam("token")String token,@RequestParam("no")String no) throws IOException, JSONException {
       PublicResult publicResult = isToken(token);
       PublicResult isNull  =isNull(no);
       Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
       logger.info("token="+token+"no="+no);
       if (publicResult.isSuccess && isNull.isSuccess) {
           Good good = goodDao.queryGoodByNo(no);
           JSONObject rt = new JSONObject();
           if (good != null){
            JSON json = (JSON) JSON.toJSON(good);
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


    @RequestMapping(value = "/goodList", method = RequestMethod.POST)
    public String getGoods(@RequestParam("token")String token) throws JSONException, IOException {
        JSONObject obj = new JSONObject();
         PublicResult publicResult = isToken(token);
         if (publicResult.isSuccess){
             User user = publicResult.user;
             //获取所有的商品信息
             String userType = user.getTypes();
             ArrayList<Good> goods = (ArrayList<Good>) goodDao.queryGoodAll();
             JSONArray jsonArray=new JSONArray();
             com.alibaba.fastjson.JSONArray array = (com.alibaba.fastjson.JSONArray) JSON.toJSON(goods);
             return array.toString();

         }else {
             return publicResult.msg;
         }
    }  //127.0.0.1：54298

    /**
     * 增加批量商品
     * @param list  [{"no":"12","name":"hu1","unit":"克","originalPrice":0.0,"salePrice":0.0,"quantity":10,"amount":1,"isFresh":"1"},
     *               {"no":"123","name":"hu2","unit":"克","originalPrice":0.0,"salePrice":0.0,"quantity":10,"amount":1,"isFresh":"1"}]
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/addGoodList",method = RequestMethod.POST)
    public String addGoodList(@RequestParam("list") String list) throws JSONException {
        JSONObject rt = new JSONObject();
        PublicResult isNull  =isNull(list);
        if (isNull.isSuccess) {
         //   com.alibaba.fastjson.JSONArray lists= JSON.parseArray(list.toString());
            ArrayList<Good> data = (ArrayList<Good>) JSON.parseArray(list,Good.class);
            if (data.size() > 0 == false){
                rt.put("code", Constant.CODE_PARAM_ERROR);
                rt.put("msg", Constant.STRING_PARAM_ERROR);
                return rt.toString();
            }

            int insert = goodDao.insertGoods(data);
            if (insert > 0){
                rt.put("code",Constant.CODE_SUCCESS);
                rt.put("msg",Constant.STRING_SUCCESS);
                return rt.toString();
            }else {
                rt.put("code", Constant.CODE_OTHER);
                rt.put("msg",Constant.STRING_OTHER);
                return rt.toString();
            }


        }else {
                return  isNull.msg;
        }

    }

    /**
     * 接收json格式 {"no":"12","name":"hu1","unit":"克","originalPrice":0.0,"salePrice":0.0,"quantity":10,"amount":1,"isFresh":"1"}
     * @param data
     * @return
     */
    @RequestMapping(value = "/addGood",method = RequestMethod.POST)
    public String addGood(@RequestBody Map<Object, Object> data) { //@RequestParam("data") String data
           PublicResult result = isNull(data);
           if (result.isSuccess == false)return result.msg;
        JSONObject rt = new JSONObject();
       // JSONObject obj = null;
        try {
          //  obj = new JSONObject(data);
           /*
            public String no;             //商品条码
            public String name;           //名称
            public String unit;           //单位
            public BigDecimal originalPrice;//原价
            public BigDecimal salePrice;  //销售价格 （会员价：当原价与现价不一）
            public BigDecimal quantity;   //单品数量
            public BigDecimal amount;    //非单品总金额
            public String isFresh;       //是否生鲜
            */
        String no = (String) data.get("no");
        String name = (String) data.get("name");
        String unit = (String) data.get("unit");
        Double originalPrice = Double.parseDouble((String) data.get("originalPrice"));
        Double salePrice= Double.parseDouble((String) data.get("salePrice"));
        Double quantity= Double.parseDouble((String) data.get("quantity"));
        //BigDecimal amount = obj.getString("amount");
        String isFresh = (String) data.get("isFresh");
        if (isNull(no).isSuccess == false || isNull(name).isSuccess == false || originalPrice > 0 == false ||
            salePrice > 0 == false ||  quantity > 0 == false|| isNull(isFresh).isSuccess ==false){

            rt.put("code", Constant.CODE_PARAM_NULL);
            rt.put("msg", Constant.STRING_PARAM_NULL);
            return rt.toString();
        }

         Good queryGoodByNo = goodDao.queryGoodByNo(no);
            if (queryGoodByNo != null){
                rt.put("code",Constant.CODE_GOOD_SAVE);
                rt.put("msg",Constant.STRING_GOOD_SAVE);
                return rt.toString();
            }
            Good good = new Good();
            good.setNo(no);
            good.setName(name);
            good.setUnit(unit);
            good.setOriginalPrice(originalPrice);
            good.setSalePrice(salePrice);
            good.setQuantity(quantity);
            good.setIsFresh(isFresh);

            int insert = goodDao.insertGood(good);

            if (insert > 0){
                rt.put("code",Constant.CODE_SUCCESS);
                rt.put("msg",Constant.STRING_SUCCESS);
                return rt.toString();
            }else {
                rt.put("code", Constant.CODE_OTHER);
                rt.put("msg",Constant.STRING_OTHER);
                return rt.toString();
            }

        }
        catch (JSONException e) {
            try {
               // JSONObject rt = new JSONObject();
                rt.put("code", Constant.CODE_PARAM_ERROR);
                rt.put("msg", Constant.STRING_PARAM_FORMAT_ERROR);
                return rt.toString();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
         return "";

    }



}
