package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 订单审单分派发仓库生成发货单时参数封装类
 * @author: AH
 * @create: 2020-07-21 17:10
 **/
@Data
@ApiModel(value = "订单审单分派发仓库生成发货单")
public class AddShippingOrderResultDTO {

    @ApiModelProperty(value = "审单分派发货单列表")
    private List<AddShippingOrderDTO> addShippingOrderDTOS;

}
