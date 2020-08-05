package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: hdoc-parent
 * @description:
 * @author: AH
 * @create: 2020-08-5 10:45
 **/
@Data
public class UpdateShippingOrderStatuDTO implements Serializable {

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "发货单ID")
    private Long shippingOrderId;

    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    @ApiModelProperty(value = "物流公司ID")
    private Long expressCompanyId;

    @ApiModelProperty(value = "物流单号")
    private Long expressNo;

    @ApiModelProperty(value = "发货单编号")
    private Long shippingOrderNo;

    @ApiModelProperty(value = "发货单状态")
    private Integer status;

}
