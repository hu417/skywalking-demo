package com.ratel.skywalking.order.controller;


import com.ratel.common.skywalking.entity.OrderEntity;
import com.ratel.skywalking.goods.api.GoodsFeginApi;
import com.ratel.skywalking.order.feign.NoticeFeign;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    //@Autowired
    //GoodsFeign goodsFeign;

    @Autowired
    GoodsFeginApi goodServiceFegin;


    @Autowired
    NoticeFeign noticeFeign;

    @GetMapping("/getOrder")
    public OrderEntity getOrder(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(20230001);
        orderEntity.setUserId(100001);
        orderEntity.setGoods(goodServiceFegin.getGoods());
        return orderEntity;
    }

    @GetMapping("/sendOrderMsg")
    public String sendOrderMsg(){
        return noticeFeign.sendOrderMsg();
    }


    @GetMapping("/getOrderByOrderIdWithTagAnnotated")
    @Trace
    @Tag(key = "orderId", value = "arg[0]")
    @Tag(key = "userId", value = "arg[1]")
    @Tag(key = "result.orderId", value = "returnedObj.orderId")
    @Tag(key = "result.goods.goodsName", value = "returnedObj.goods.goodsName")
    public OrderEntity getOrderWithTagAnnotated(long orderId,long userId){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setUserId(userId);
        orderEntity.setGoods(goodServiceFegin.getGoodsByGoodsId(6666L));
        return orderEntity;
    }

    @GetMapping("/getOrderWithTagInContext")
    public OrderEntity getOrderWithTagInContext(){

        ActiveSpan.tag("my_tag", "my_value");
        ActiveSpan.error();
        ActiveSpan.error("Test-Error-Reason");

        ActiveSpan.error(new RuntimeException("Test-Error-Throwable"));
        ActiveSpan.info("Test-Info-Msg");
        ActiveSpan.debug("Test-debug-Msg");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(20230001);
        orderEntity.setUserId(100001);
        orderEntity.setGoods(goodServiceFegin.getGoods());
        return orderEntity;
    }

    @GetMapping("/getOrderWithOpentracing")
    public OrderEntity getOrderWithOpentracing(){
        Tracer tracer = new SkywalkingTracer();
        Tracer.SpanBuilder spanBuilder = tracer.buildSpan("/order-service/method-getOrderWithOpentracing");
        Span span = spanBuilder.withTag("tag1", false).withTag("tag2", "这是tag2").startManual();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(20230001);
        orderEntity.setUserId(100001);
        orderEntity.setGoods(goodServiceFegin.getGoods());
        span.finish();
        return orderEntity;
    }

}
