package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.insigma.ordercenter.utils.DataUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jason
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

    @ApiModelProperty(value = "商品物流类型")
    private String shipType;

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
    private List<TagVO> tagList;

    @ApiModelProperty(value = "长宽高")
    private String lengthWidthHeight;

    @ApiModelProperty(value = "商品图片url")
    private String productPictureUrl;

    @ApiModelProperty(value = "是否预约发货 1是 0否")
    private Integer isReserve;

    @ApiModelProperty(value = "预约周期 1按月 2按季 3按年 4指定日期")
    private Integer cycle;

    @ApiModelProperty(value = "预约发货指定时间-yyyy-MM-DD")
    @JsonFormat(pattern = DataUtil.formatter_yyyyMMdd, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime reserveTime;

    @ApiModelProperty(value = "预约每次发送数量")
    private Integer sendNumber;

    @ApiModelProperty(value = "预约发送次数")
    private Integer sendCount;
}
