package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 修改订单商品参数封装类
 * @author: XuChao
 * @create: 2020-07-31 17:08
 **/
@Data
public class EditOrderProductDTO {

    @ApiModelProperty(value = "订单详情id")
    private Long orderDetailId;

    @ApiModelProperty(value = "数量")
    private Integer amount;

    @ApiModelProperty(value = "商品规格")
    private String productSpecs;

}
