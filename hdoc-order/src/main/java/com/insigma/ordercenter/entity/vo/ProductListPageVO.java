package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jason
 * @description：商品列表返回对象
 * @date Create in 2020/7/8
 */

@Data
@ApiModel(value = "商品列表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ProductListPageVO extends BaseVO {

    @ApiModelProperty(value = "商品ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @ApiModelProperty(value = "商品sku")
    private String productSku;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "折扣价格")
    private BigDecimal discountPrice;

    @ApiModelProperty(value = "商品规格")
    private String productSpecs;

    @ApiModelProperty(value = "库存数量")
    private Integer stockQuantity;

    @ApiModelProperty(value = "上架时间")
    private LocalDateTime putOnTime;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "商品编码")
    private String productNo;

    @ApiModelProperty(value = "是否禁用（0：已禁用，1：已启用）")
    private Integer isPutOn;

    @ApiModelProperty(value = "是否是组合商品（0：单品，1：组合）")
    private Integer isCombo;

    @ApiModelProperty(value = "商品nc码")
    private String productNc;

    @ApiModelProperty(value = "商品单位")
    private String unit;

}
