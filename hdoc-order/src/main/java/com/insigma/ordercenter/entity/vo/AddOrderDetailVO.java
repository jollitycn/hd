package com.insigma.ordercenter.entity.vo;

import com.insigma.ordercenter.entity.Order;
import com.insigma.ordercenter.entity.OrderDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "订单明细列表")
@EqualsAndHashCode(callSuper = true)
public class AddOrderDetailVO extends BaseVO{

    @ApiModelProperty(value = "订单明细列表")
    private List<OrderDetail> orderDetails;

}
