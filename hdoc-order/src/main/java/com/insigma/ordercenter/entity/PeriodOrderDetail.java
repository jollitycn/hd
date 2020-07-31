package com.insigma.ordercenter.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author AH
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_period_order_detail")
@ApiModel(value = "PeriodOrderDetail对象", description = "")
public class PeriodOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单明细ID")
    @TableId(value = "period_order_detail_id", type = IdType.ID_WORKER)
    private Long periodOrderDetailId;

    @ApiModelProperty(value = "订单ID ")
    private Long periodOrderId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

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
    private Integer quantity;

    @ApiModelProperty(value = "小计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "是否是赠品（0：否，1：是）")
    private Integer isGift;


    public static final String PERIOD_ORDER_DETAIL_ID = "period_order_detail_id";

    public static final String PERIOD_ORDER_ID = "period_order_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_SKU = "product_sku";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRODUCT_PRICE = "product_price";

    public static final String PRODUCT_SPECS = "product_specs";

    public static final String UNIT = "unit";

    public static final String QUANTITY = "quantity";

    public static final String TOTAL_PRICE = "total_price";

    public static final String IS_GIFT = "is_gift";

}
