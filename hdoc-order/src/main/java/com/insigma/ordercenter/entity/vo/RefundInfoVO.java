package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jason
 * @description 退换货信息列表
 * @date Create in 2020/7/9
 */
@Data
@ApiModel(value = "商品库存信息返回对象")
@EqualsAndHashCode(callSuper = true)
public class RefundInfoVO extends BaseVO{

    @ApiModelProperty(value = "退换货记录ID")
    private Long refundId;

    @ApiModelProperty(value = "订单ID ")
    private Long orderId;

    @ApiModelProperty(value = "退换货单号")
    private String refundNo;

    @ApiModelProperty(value = "类型（0：退货，1：换货）")
    private Integer refundType;

    @ApiModelProperty(value = "发起类型（0：客户发起，1：客服发起）")
    private Integer sourceType;

    @ApiModelProperty(value = "发起时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "审核状态（0：未审核，1：通过，2：不通过）")
    private Integer auditStatus;

    @ApiModelProperty(value = "审核人ID")
    private Long auditId;

    @ApiModelProperty(value = "退换理由")
    private String reason;

    @ApiModelProperty(value = "审核日期")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "退换货状态（0：未完成，1：已完成）")
    private Integer status;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundMoney;

}
