package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author AH
 * @program hdoc-parent
 * @description：订单详情列表
 * @date Create in 2020/7/23
 */
@Data
@ApiModel(value = "订单详情列表")
@EqualsAndHashCode(callSuper = true)
public class OrderDetailVO extends BaseVO{

    @ApiModelProperty(value = "订单明细ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderDetailId;

    @ApiModelProperty(value = "订单ID ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @ApiModelProperty(value = "商品ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @ApiModelProperty(value = "商品编码")
    private String productNo;

    @ApiModelProperty(value = "商品sku")
    private String productSku;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品规格")
    private String productSpecs;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "数量")
    private Integer amount;

    @ApiModelProperty(value = "小计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "是否是赠品（0：否，1：是）")
    private Integer isGift;

    @ApiModelProperty(value = "库存数量")
    private Integer quantity;


}
