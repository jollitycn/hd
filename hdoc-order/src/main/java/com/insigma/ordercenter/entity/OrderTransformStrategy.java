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
    * 订单转发货单策略表
    * </p>
*
* @author panjuncai
* @since 2020-07-28
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_order_transform_strategy")
    @ApiModel(value="OrderTransformStrategy对象", description="订单转发货单策略表")
    public class OrderTransformStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "订单转发货单策略")
            @TableId(value = "order_transform_strategy_id", type = IdType.ID_WORKER)
    private Long orderTransformStrategyId;

            @ApiModelProperty(value = "策略ID")
    private Long strategyId;

            @ApiModelProperty(value = "店铺ID")
    private Long shopId;

            @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;


        public static final String ORDER_TRANSFORM_STRATEGY_ID = "order_transform_strategy_id";

        public static final String STRATEGY_ID = "strategy_id";

        public static final String SHOP_ID = "shop_id";

        public static final String IS_STOP = "is_stop";

}
