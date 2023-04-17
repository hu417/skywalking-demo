package com.ratel.skywalking.goods.api;


import com.ratel.common.skywalking.entity.GoodsEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ratel-goods")
public interface GoodsFeginApi {

    @GetMapping("/goods/getGoodsByGoodsId")
    GoodsEntity getGoodsByGoodsId(@RequestParam("goodsId") long goodsId);

    @GetMapping("/goods/getGoods")
    GoodsEntity getGoods();
}
