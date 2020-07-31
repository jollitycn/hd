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
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_period_order_pay_relation")
@ApiModel(value = "PeriodOrderPayRelation对象", description = "")
public class PeriodOrderPayRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单支付关联Id")
    @TableId(value = "period_order_pay_relation_id", type = IdType.ID_WORKER)
    private Long periodOrderPayRelationId;

    @ApiModelProperty(value = "订单Id")
    private Long periodOrderId;

    @ApiModelProperty(value = "订单支付Id")
    private Long periodOrderPayId;


    public static final String PERIOD_ORDER_PAY_RELATION_ID = "period_order_pay_relation_id";

    public static final String PERIOD_ORDER_ID = "period_order_id";

    public static final String PERIOD_ORDER_PAY_ID = "period_order_pay_id";

}
