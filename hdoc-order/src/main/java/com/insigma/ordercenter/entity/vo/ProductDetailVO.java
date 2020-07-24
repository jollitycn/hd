package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liuhao
 * @description：商品详情返回对象
 * @date Create in 2020/7/9
 */
@Data
@ApiModel(value = "商品列表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ProductDetailVO extends BaseVO {

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

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "商品类型")
    private String productType;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "商品描述")
    private String description;


    @ApiModelProperty(value = "所属货主")
    private String orderSourceName;

    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "商品代码")
    private String productNo;

    @ApiModelProperty(value = "NC代码")
    private String productNc;

    @ApiModelProperty(value = "是否启用折扣价（0：否，1：是）")
    private Integer isDiscount;

    @ApiModelProperty(value = "单位数量")
    private Integer unitQuantity;

    @ApiModelProperty(value = "重量")
    private Double weight;

    @ApiModelProperty(value = "物流重量")
    private Double shipWeight;

    @ApiModelProperty(value = "是否拆分发货 0：单品，1：组合")
    private Integer isCombo;

    @ApiModelProperty(value = "商品二维码")
    private String qrCode;

    @ApiModelProperty(value = "商品标签")
    private List<String> tagList;
}
