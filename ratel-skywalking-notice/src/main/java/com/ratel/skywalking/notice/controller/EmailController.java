package com.ratel.skywalking.notice.controller;

import com.ratel.common.skywalking.entity.OrderEntity;
import com.ratel.skywalking.notice.entity.SkyWalkingAlarmMessage;
import com.ratel.skywalking.notice.feign.OrderFegin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/email")
@Slf4j
@RestController
@RefreshScope
public class EmailController {

    @Value(value = "${skywalking.emailArraysJsonStr:[\"fuyn16@chinaunicom.cn\"]}")
    private String emailArraysJsonStr;

    @Value(value = "${skywalking.emailArrays:fuyn16@chinaunicom.cn}")
    private List<String> emailList;

    @Autowired
    private OrderFegin orderFegin;

    @PostMapping("/sendAlarmEmails")
    public String sendAlarmEmails(@RequestBody List<SkyWalkingAlarmMessage> skyWalkingAlarmMessageList) {
        log.info(emailArraysJsonStr);
        log.info(emailList.toString());
        skyWalkingAlarmMessageList.forEach(message -> {
            log.info(message.toString());
        });
        return "发送成功";
    }

    @GetMapping("/getOrder")
    public OrderEntity getOrder() {
        return orderFegin.getOrder();
    }

    @GetMapping("/sendOrderMsg")
    public String sendOrderMsg() {
        return "信息发送成功：您的订单，我们已经收到了";
    }

}
