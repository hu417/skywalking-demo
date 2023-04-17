package com.ratel.skywalking.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ratel-notice")
public interface NoticeFeign {

    @GetMapping("/email/sendOrderMsg")
    String sendOrderMsg();
}
