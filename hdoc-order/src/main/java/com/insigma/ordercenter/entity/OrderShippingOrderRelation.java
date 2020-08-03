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
    * 订单发货单关联表
    * </p>
*
* @author AH
* @since 2020-07-31
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_order_shipping_order_relation")
    @ApiModel(value="OrderShippingOrderRelation对象", description="订单发货单关联表")
    public class OrderShippingOrderRelation implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "订单发货单关联ID")
            @TableId(value = "order_shipping_order_relation_id", type = IdType.ID_WORKER)
    private Long orderShippingOrderRelationId;

            @ApiModelProperty(value = "订单ID ")
    private Long orderId;

            @ApiModelProperty(value = "发货单ID")
    private Long shippingOrderId;


        public static final String ORDER_SHIPPING_ORDER_RELATION_ID = "order_shipping_order_relation_id";

        public static final String ORDER_ID = "order_id";

        public static final String SHIPPING_ORDER_ID = "shipping_order_id";

}
