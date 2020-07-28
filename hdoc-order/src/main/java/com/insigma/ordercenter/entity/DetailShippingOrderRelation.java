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
    * 订单详情发货单关联表
    * </p>
*
* @author AH
* @since 2020-07-24
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_detail_shipping_order_relation")
    @ApiModel(value="DetailShippingOrderRelation对象", description="订单详情发货单关联表")
    public class DetailShippingOrderRelation implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "订单详情发货单关联表")
            @TableId(value = "detail_shipping_order_relation_id", type = IdType.ID_WORKER)
    private Long detailShippingOrderRelationId;

            @ApiModelProperty(value = "订单明细ID")
    private Long orderDetailId;

            @ApiModelProperty(value = "发货单ID")
    private Long shippingOrderId;


        public static final String DETAIL_SHIPPING_ORDER_RELATION_ID = "detail_shipping_order_relation_id";

        public static final String ORDER_DETAIL_ID = "order_detail_id";

        public static final String SHIPPING_ORDER_ID = "shipping_order_id";

}
