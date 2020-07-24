package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liuhao
 * @description：商品添加
 * @date Create in 2020/7/10
 */
@Data
@ApiModel(value = "商品添加DTO")
public class ProductAddDTO {

    @ApiModelProperty(value = "商品sku")
    private String productSku;

    @ApiModelProperty(value = "商品品牌")
    private String brand;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "折扣价格")
    private BigDecimal discountPrice;

    @ApiModelProperty(value = "商品规格")
    private String productSpecs;

    @ApiModelProperty(value = "单位")
    private Integer unit;

    @ApiModelProperty(value = "单位数量")
    private Integer unitQuantity;

    @ApiModelProperty(value = "商品类型")
    private String productType;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "物流重量")
    private Double shipWeight;

    @ApiModelProperty(value = "长宽高")
    private String lengthWidthHeight;

    @ApiModelProperty(value = "标签")
    private List<String> tagList;

    @ApiModelProperty(value = "是否启用折扣价（0：否，1：是）")
    private Integer isDiscount;

    @ApiModelProperty(value = "条形码")
    private String qrCode;

}
