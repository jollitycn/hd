package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liuhao
 * @description：商品库存信息返回对象
 * @date Create in 2020/7/9
 */
@Data
@ApiModel(value = "商品库存信息返回对象")
@EqualsAndHashCode(callSuper = true)
public class ProductStockInfoVO extends BaseVO {

    @ApiModelProperty(value = "仓库ID ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long warehouseId;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "商品ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "库存数量")
    private Integer quantity;
}
