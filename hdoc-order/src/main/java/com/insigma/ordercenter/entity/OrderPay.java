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
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_pay")
@ApiModel(value = "OrderPay对象", description = "")
public class OrderPay implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单支付id")
    @TableId(value = "order_pay_id", type = IdType.ID_WORKER)
    private Long orderPayId;

    @ApiModelProperty(value = "支付类型")
    private String payType;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payMoney;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payDatetime;

    @ApiModelProperty(value = "支付卡号")
    private String payCardNo;

    @ApiModelProperty(value = "备用（微信支付昵称）")
    private String payBack;


    public static final String ORDER_PAY_ID = "order_pay_id";

    public static final String PAY_TYPE = "pay_type";

    public static final String PAY_MONEY = "pay_money";

    public static final String PAY_DATETIME = "pay_datetime";

    public static final String PAY_CARD_NO = "pay_card_no";

    public static final String PAY_BACK = "pay_back";

}
