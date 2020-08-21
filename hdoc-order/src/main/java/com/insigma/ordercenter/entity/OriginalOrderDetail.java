package com.insigma.ordercenter.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 原始订单明细
 * </p>
 *
 * @author Youwk
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_original_order_detail")
@ApiModel(value = "OriginalOrderDetail对象", description = "原始订单明细")
public class OriginalOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "原始订单明细ID")
    @TableId(type=IdType.ID_WORKER)
    private Long originalOrderDetailId;

    @ApiModelProperty(value = "原始订单ID ")
    private Long originalOrderId;

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

    @ApiModelProperty(value = "商品分类 对应字典表id")
    private Integer productType;

    @ApiModelProperty(value = "商品编码")
    private String productNo;

    @ApiModelProperty(value = "商品nc码")
    private String productNc;

    @ApiModelProperty("付款金额")
    private BigDecimal divideOrderFee;

    @ApiModelProperty("优惠金额")
    private BigDecimal discountFee;

    public static final String ORIGINAL_ORDER_DETAI_ID = "original_order_detai_id";

    public static final String ORIGINAL_ORDER_ID = "original_order_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_SKU = "product_sku";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRODUCT_PRICE = "product_price";

    public static final String PRODUCT_SPECS = "product_specs";

    public static final String UNIT = "unit";

    public static final String QUANTITY = "quantity";

    public static final String TOTAL_PRICE = "total_price";

    public static final String PRODUCT_NO = "product_no";

    public static final String PRODUCT_NC = "product_nc";

}
