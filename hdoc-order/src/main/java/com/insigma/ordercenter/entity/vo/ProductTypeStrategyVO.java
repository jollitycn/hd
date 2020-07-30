package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/29 10:15
 */
@Data
@ApiModel("商品分类策略信息")
@EqualsAndHashCode(callSuper = true)
public class ProductTypeStrategyVO extends BaseVO {
    @ApiModelProperty(value = "商品分类id（对应字典id）")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productTypeId;

    @ApiModelProperty(value = "商品分类名称")
    private String productTypeName;

    @ApiModelProperty(value = "策略和商品分类关联状态：1---已关联 0---未关联")
    private Integer relationStatus;
}
