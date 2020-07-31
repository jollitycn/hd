package com.insigma.ordercenter.entity;

import java.math.BigDecimal;

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
 * 退换货明细
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_refund_detail")
@ApiModel(value = "RefundDetail对象", description = "退换货明细")
public class RefundDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "退换货明细ID")
    @TableId(value = "refund_detail_id", type = IdType.ID_WORKER)
    private Long refundDetailId;

    @ApiModelProperty(value = "退换货ID")
    private Long refundId;

    @ApiModelProperty(value = "收货ID")
    private Long deliverRecordId;

    @ApiModelProperty(value = "发货单ID")
    private Long shippingOrderId;

    @ApiModelProperty(value = "状态（0：未完成，1：已完成）")
    private Integer status;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundMoney;


    public static final String REFUND_DETAIL_ID = "refund_detail_id";

    public static final String REFUND_ID = "refund_id";

    public static final String DELIVER_RECORD_ID = "deliver_record_id";

    public static final String SHIPPING_ORDER_ID = "shipping_order_id";

    public static final String STATUS = "status";

    public static final String REFUND_MONEY = "refund_money";

}
