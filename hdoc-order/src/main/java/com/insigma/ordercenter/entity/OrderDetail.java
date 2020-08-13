package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单明细
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_detail")
@ApiModel(value = "OrderDetail对象", description = "订单明细")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单明细ID")
    @TableId(value = "order_detail_id", type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderDetailId;

    @ApiModelProperty(value = "订单ID ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @ApiModelProperty(value = "商品ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @ApiModelProperty(value = "商品sku")
    private String productSku;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品规格")
    private String productSpecs;

    @ApiModelProperty(value = "商品分类 对应字典表id")
    private Long productType;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "数量")
    private Integer amount;

    @ApiModelProperty(value = "小计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "是否是赠品（0：否，1：是）")
    private Integer isGift;

    @ApiModelProperty(value = "是否是补货商品 0否 1是")
    private Integer isSupplement;

    public static final String ORDER_DETAIL_ID = "order_detail_id";

    public static final String ORDER_ID = "order_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_SKU = "product_sku";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PRODUCT_PRICE = "product_price";

    public static final String PRODUCT_SPECS = "product_specs";

    public static final String UNIT = "unit";

    public static final String AMOUNT = "amount";

    public static final String TOTAL_PRICE = "total_price";

    public static final String IS_GIFT = "is_gift";

    public static final String IS_SUPPLEMENT = "is_supplement";

}
