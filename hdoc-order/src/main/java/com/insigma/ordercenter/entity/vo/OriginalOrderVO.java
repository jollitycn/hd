package com.insigma.ordercenter.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jason
 * @program hdoc-parent
 * @description：原始订单列表
 * @date Create in 2020/7/27
 */
@Data
@ApiModel(value = "原始订单列表")
@EqualsAndHashCode(callSuper = true)
public class OriginalOrderVO extends BaseVO{

    @ApiModelProperty(value = "原始订单ID ")
    private Long originalOrderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "下单时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "收件人姓名")
    private String consumerName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "应收合计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "收货人地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "订单ID ")
    private Long orderId;

}
