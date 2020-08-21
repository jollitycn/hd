package com.insigma.ordercenter.entity.vo;

import com.insigma.ordercenter.entity.OrderPay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "订单支付信息")
@EqualsAndHashCode(callSuper = true)
public class OrderPayAllVO extends BaseVO{

    @ApiModelProperty(value = "原价")
    private BigDecimal originalCost;

    @ApiModelProperty(value = "实付款")
    private BigDecimal Disbursements;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "支付方式列表信息")
    private List<OrderpayVO> orderPays;

}
