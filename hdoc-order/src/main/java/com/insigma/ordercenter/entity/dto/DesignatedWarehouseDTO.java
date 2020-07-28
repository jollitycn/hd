package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 商品指定仓库请求封装类
 * @author: XuChao
 * @create: 2020-07-28 15:36
 **/
@Data
public class DesignatedWarehouseDTO {

    @ApiModelProperty(value = "仓库id")
    private List<Long> warehouseIds;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "库存数量")
    private Integer quantity;

    @ApiModelProperty(value = "优先级")
    private Integer priority;
}
