package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ShippingOrderDetailVO extends ShippingOrderVO {

    @ApiModelProperty(value = "订单明细列表")
    private List<OrderDetailVO> orderDetailVOS;

}
