package com.ratel.skywalking.notice.feign;


import com.ratel.common.skywalking.entity.OrderEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ratel-order")
public interface OrderFegin {

    @GetMapping("/order/getOrder")
    OrderEntity getOrder();
}
