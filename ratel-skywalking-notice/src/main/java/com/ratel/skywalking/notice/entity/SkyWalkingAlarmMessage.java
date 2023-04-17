package com.ratel.skywalking.notice.entity;


import lombok.Data;
import lombok.ToString;

/**
 * @Description
 * @Author fuyongnan
 * @Date 2023/03/07
 * @Version 1.0
 */
@Data
@ToString
public class SkyWalkingAlarmMessage {

    private Integer scopeId;

    private String scope;

    private String name;

    private String id0;

    private String id1;

    private String ruleName;

    private String alarmMessage;

    private Long startTime;
}