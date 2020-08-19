package com.insigma.ordercenter.entity;

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
* @author youwk
* @since 2020-08-18
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_strategy_product_type")
    @ApiModel(value="StrategyProductType对象", description="")
    public class StrategyProductType implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "策略商品、分类表主键")
            @TableId(value = "strategy_product_type_id", type = IdType.AUTO)
    private Integer strategyProductTypeId;

            @ApiModelProperty(value = "参数类型（1-商品分类，2-商品）")
    private Integer paramType;

            @ApiModelProperty(value = "参数id")
    private Long paramId;

            @ApiModelProperty(value = "策略名称")
    private String strategyName;


        public static final String STRATEGY_PRODUCT_TYPE_ID = "strategy_product_type_id";

        public static final String PARAM_TYPE = "param_type";

        public static final String PARAM_ID = "param_id";

        public static final String STRATEGY_NAME = "strategy_name";

}
