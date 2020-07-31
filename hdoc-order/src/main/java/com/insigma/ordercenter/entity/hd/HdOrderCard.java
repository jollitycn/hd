package com.insigma.ordercenter.entity.hd;

import lombok.Data;

import java.io.Serializable;

/**
 * @author youwk
 * @ClassName HdOrderCard
 * @description TODO
 * @date 2020/7/27 15:18
 * @Version 1.0
 */
@Data
public class HdOrderCard implements Serializable {

    /**
     * 订单编号
     */
    private String tid;

    /**
     * 提货卡号
     */
    private String card_no;
}
