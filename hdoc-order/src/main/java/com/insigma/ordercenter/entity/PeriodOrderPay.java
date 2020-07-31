package com.insigma.ordercenter.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author AH
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_period_order_pay")
@ApiModel(value = "PeriodOrderPay对象", description = "")
public class PeriodOrderPay implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单支付id")
    @TableId(value = "period_order_pay_id", type = IdType.ID_WORKER)
    private Long periodOrderPayId;

    @ApiModelProperty(value = "支付类型")
    private String periodPayType;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal periodPayMoney;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime periodPayDatetime;

    @ApiModelProperty(value = "支付卡号")
    private String periodPayCardNo;

    @ApiModelProperty(value = "备用（微信支付昵称）")
    private String periodPayBack;


    public static final String PERIOD_ORDER_PAY_ID = "period_order_pay_id";

    public static final String PERIOD_PAY_TYPE = "period_pay_type";

    public static final String PERIOD_PAY_MONEY = "period_pay_money";

    public static final String PERIOD_PAY_DATETIME = "period_pay_datetime";

    public static final String PERIOD_PAY_CARD_NO = "period_pay_card_no";

    public static final String PERIOD_PAY_BACK = "period_pay_back";

}
