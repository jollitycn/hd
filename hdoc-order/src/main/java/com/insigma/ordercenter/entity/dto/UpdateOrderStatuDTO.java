package com.insigma.ordercenter.entity.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: hdoc-parent
 * @description:
 * @author: AH
 * @create: 2020-07-23 10:45
 **/
@Data
public class UpdateOrderStatuDTO implements Serializable {

    @ApiModelProperty(value = "订单号Id")
    private Long orderId;

    @ApiModelProperty(value = "订单状态")
    private Long orderStatus;

}
