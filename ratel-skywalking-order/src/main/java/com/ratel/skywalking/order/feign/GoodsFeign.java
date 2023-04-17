package com.ratel.skywalking.order.feign;

import com.ratel.common.skywalking.entity.GoodsEntity;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(value = "ratel-goods")
public interface GoodsFeign {

    @GetMapping("/goods/getGood")
    GoodsEntity getGoods();
}
