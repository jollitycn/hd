package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("判断库存，以及店铺发货比例数量")
public class CheckProductDTO {

    private Long warehouseId;

    private Long productId;

    private Long shopId;

}
