package com.insigma.ordercenter.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* <p>
    * 商品表
    * </p>
*
* @author LiuHao
* @since 2020-07-22
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product")
@ApiModel(value="Product对象", description="商品表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @TableId(value = "product_id", type = IdType.ID_WORKER)
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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否删除（0：正常，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "商品类型")
    private String productType;

    @ApiModelProperty(value = "上架时间")
    private LocalDateTime putOnTime;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "是否上架（0：未上架，1：已上架）")
    private Integer isPutOn;

    @ApiModelProperty(value = "是否是组合商品（0：单品，1：组合）")
    private Integer isCombo;

    @ApiModelProperty(value = "商品品牌")
    private String brand;

    @ApiModelProperty(value = "物流重量")
    private Double shipWeight;

    @ApiModelProperty(value = "商品编码")
    private String productNo;

    @ApiModelProperty(value = "商品nc码")
    private String productNc;

    @ApiModelProperty(value = "货主ID")
    private Long orderSourceId;

    @ApiModelProperty(value = "是否启用折扣价（0：否，1：是）")
    private Integer isDiscount;

    @ApiModelProperty(value = "单位数量")
    private Integer unitQuantity;

    @ApiModelProperty(value = "重量")
    private Double weight;

    @ApiModelProperty(value = "条形码")
    private String qrCode;

    @ApiModelProperty(value = "长宽高")
    private String lengthWidthHeight;


        public static final String PRODUCT_ID = "product_id";

        public static final String PRODUCT_SKU = "product_sku";

        public static final String PRODUCT_NAME = "product_name";

        public static final String PRODUCT_PRICE = "product_price";

        public static final String DISCOUNT_PRICE = "discount_price";

        public static final String PRODUCT_SPECS = "product_specs";

        public static final String UNIT = "unit";

        public static final String CREATE_TIME = "create_time";

        public static final String IS_DELETED = "is_deleted";

        public static final String PRODUCT_TYPE = "product_type";

        public static final String PUT_ON_TIME = "put_on_time";

        public static final String SUB_TITLE = "sub_title";

        public static final String DESCRIPTION = "description";

        public static final String IS_PUT_ON = "is_put_on";

        public static final String IS_COMBO = "is_combo";

        public static final String BRAND = "brand";

        public static final String SHIP_WEIGHT = "ship_weight";

        public static final String PRODUCT_NO = "product_no";

        public static final String PRODUCT_NC = "product_nc";

        public static final String ORDER_SOURCE_ID = "order_source_id";

        public static final String IS_DISCOUNT = "is_discount";

        public static final String UNIT_QUANTITY = "unit_quantity";

        public static final String WEIGHT = "weight";

        public static final String QR_CODE = "qr_code";

}
