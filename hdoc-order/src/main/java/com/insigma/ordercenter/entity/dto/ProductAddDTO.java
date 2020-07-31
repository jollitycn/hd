package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason
 * @description：商品添加
 * @date Create in 2020/7/10
 */
@Data
@ApiModel(value = "商品添加DTO")
public class ProductAddDTO {

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品编号")
    private String productNo;

    @ApiModelProperty(value = "商品sku")
    private String productSku;

    @ApiModelProperty(value = "商品nc")
    private String productNc;

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
    private String unit;

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

    @ApiModelProperty(value = "物流分类")
    private String shipType;

    @ApiModelProperty(value = "长宽高")
    private String lengthWidthHeight;

    @ApiModelProperty(value = "商品标签CODE")
    private List<String> tagList;

    @ApiModelProperty(value = "是否启用折扣价（0：否，1：是）")
    private Integer isDiscount;

    @ApiModelProperty(value = "条形码")
    private String qrCode;

    @ApiModelProperty(value = "货主ID")
    private Long orderSourceId;

    @ApiModelProperty(value = "重量")
    private Double weight;

    @ApiModelProperty(value = "商品图片url")
    private String productPictureUrl;

    @ApiModelProperty(value = "是否预约发货 1是 0否")
    private Integer isReserve;

    @ApiModelProperty(value = "预约周期 1按月 2按季 3按年 4指定日期")
    private Integer cycle;

    @ApiModelProperty(value = "预约发货指定时间-yyyy-MM-DD")
    private String reserveTime;

    @ApiModelProperty(value = "预约每次发送数量")
    private Integer sendNumber;

    @ApiModelProperty(value = "预约发送次数")
    private Integer sendCount;
}
