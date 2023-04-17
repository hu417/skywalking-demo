package com.ratel.skywalking.goods.api;

import com.ratel.common.skywalking.entity.GoodsEntity;
import org.springframework.web.bind.annotation.RestController;

//@RestController // 提供 RESTful API 接口，给 Feign 调用
//public class GoodsApiImpl implements GoodsApi {
//
//
//    @Override
//    public GoodsEntity getGoodsByGoodsId(long goodsId) {
//        GoodsEntity goodsEntity = new GoodsEntity();
//        goodsEntity.setGoodsId(goodsId);
//        goodsEntity.setGoodsName("iphone 14 pro max");
//        goodsEntity.setGoodsPrice(9999.99D);
//        return goodsEntity;
//    }
//}