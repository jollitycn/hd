package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("t_order_pay_cardno")
@ApiModel(value = "OrderPayRelation对象", description = "")
public class OrderPayCardNo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "提货卡号")
    private String cardNo;

    @ApiModelProperty(value = "订单支付Id")
    private Long orderPayId;

    public static final String ORDER_ID = "order_id";

    public static final String ORDER_PAY_ID = "order_pay_id";

}
