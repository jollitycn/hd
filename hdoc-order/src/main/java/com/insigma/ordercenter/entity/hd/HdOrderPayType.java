package com.insigma.ordercenter.entity.hd;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author youwk
 * @ClassName HdOrderPayType
 * @description TODO
 * @date 2020/7/27 15:16
 * @Version 1.0
 */
@Data
public class HdOrderPayType implements Serializable {
    /**
     * 网店代码
     */
    private String xtwldm;

    /**
     * 平台订单号码
     */
    private String tid;

    /**
     * 支付方式（99在线支付；AA提货卡支付）
     */
    private String pay_type;

    /**
     * 支付金额
     */
    private BigDecimal pay_fee;

}
