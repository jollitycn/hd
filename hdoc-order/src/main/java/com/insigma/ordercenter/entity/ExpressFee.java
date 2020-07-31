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
    * 物流报价表
    * </p>
*
* @author Jason
* @since 2020-07-30
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_express_fee")
    @ApiModel(value="ExpressFee对象", description="物流报价表")
    public class ExpressFee implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "物流报价ID")
            @TableId(value = "express_fee_id", type = IdType.AUTO)
    private Long expressFeeId;

            @ApiModelProperty(value = "物流公司ID")
    private Long expressCompanyId;

            @ApiModelProperty(value = "省份")
    private String province;

            @ApiModelProperty(value = "仓库ID ")
    private Long warehouseId;

            @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

            @ApiModelProperty(value = "首重重量（KG）")
    private BigDecimal firstWeight;

            @ApiModelProperty(value = "首重价格（元）")
    private BigDecimal firstPrice;

            @ApiModelProperty(value = "续重（元/KG）")
    private BigDecimal additionalPrice;

            @ApiModelProperty(value = "商品分类")
    private Long productType;


        public static final String EXPRESS_FEE_ID = "express_fee_id";

        public static final String EXPRESS_COMPANY_ID = "express_company_id";

        public static final String PROVINCE = "province";

        public static final String WAREHOUSE_ID = "warehouse_id";

        public static final String WAREHOUSE_NAME = "warehouse_name";

        public static final String FIRST_WEIGHT = "first_weight";

        public static final String FIRST_PRICE = "first_price";

        public static final String ADDITIONAL_PRICE = "additional_price";

        public static final String PRODUCT_TYPE = "product_type";

}
