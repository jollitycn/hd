package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jason
 * @description：商品添加
 * @date Create in 2020/7/10
 */
@Data
@ApiModel(value = "补货单商品添加DTO")
public class ShippingProductAddDTO {

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "数量")
    private Integer amount;
}
