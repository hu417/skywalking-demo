package com.ratel.skywalking.goods.controller;


import cn.hutool.http.HttpUtil;
import com.ratel.common.skywalking.entity.GoodsEntity;
import com.ratel.skywalking.goods.api.GoodsFeginApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {



    @GetMapping("/getGoods")
    public GoodsEntity getGoods(){
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodsId(1);
        goodsEntity.setGoodsName("iphone 14 pro max");
        goodsEntity.setGoodsPrice(9999.99D);
        //增加线程休眠，否则skywalking无法采集到数据，相关功能移到testProfile里，所以注释了
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            log.info(e.getMessage(),e);
//        }
        return goodsEntity;
    }

    @GetMapping("/getGoodsByGoodsId")
    @Trace
    @Tag(key = "goodsId", value = "arg[0]")
    @Tag(key = "result.goodsName", value = "returnedObj.goodsName")
    @Tag(key = "result.goods.goodsPrice", value = "returnedObj.goodsPrice")
    public GoodsEntity getGoodsByGoodsId(long goodsId) {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodsId(goodsId);
        goodsEntity.setGoodsName("iphone 14 pro max");
        goodsEntity.setGoodsPrice(9999.99D);
        return goodsEntity;
    }


    @GetMapping("/testProfile")
    public GoodsEntity testProfile(){
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodsId(1);
        goodsEntity.setGoodsName("iphone 14 pro max 1TB");
        goodsEntity.setGoodsPrice(9999.99D);
        ////增加线程休眠，否则skywalking无法采集到数据
        try {
            HttpUtil.get("https://www.youtube.com/watch?v=mfKaToAKl7k",500);

        } catch (Exception e) {
            log.info(e.getMessage(),e);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return goodsEntity;
    }


}
