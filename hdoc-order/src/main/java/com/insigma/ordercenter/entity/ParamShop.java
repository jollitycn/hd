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
    * （换货、赠品）策略参数与店铺关联表
    * </p>
*
* @author panjuncai
* @since 2020-07-30
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_param_shop")
    @ApiModel(value="ParamShop对象", description="（换货、赠品）策略参数与店铺关联表")
    public class ParamShop implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "参数店铺id")
            @TableId(value = "param_shop_id", type = IdType.ID_WORKER)
    private Long paramShopId;

            @ApiModelProperty(value = "参数id（对应t_exchange_strategy、t_gift_strategy主键）")
    private Long paramId;

            @ApiModelProperty(value = "参数类型：1-换货策略参数  2-赠品策略参数")
    private Integer paramType;

            @ApiModelProperty(value = "店铺id")
    private Long shopId;


        public static final String PARAM_SHOP_ID = "param_shop_id";

        public static final String PARAM_ID = "param_id";

        public static final String PARAM_TYPE = "param_type";

        public static final String SHOP_ID = "shop_id";

}
