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
    import java.time.LocalDateTime;

/**
* <p>
    *  发货单操作日志表
    * </p>
*
* @author LiuHao
* @since 2020-07-23
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_shipping_order_operation")
    @ApiModel(value="ShippingOrderOperation对象", description=" 发货单操作日志表")
    public class ShippingOrderOperation implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "发货单操作日志ID")
            @TableId(value = "shipping_order_operation_id", type = IdType.ID_WORKER)
    private Long shippingOrderOperationId;

            @ApiModelProperty(value = "发货单ID")
    private Long shippingOrderId;

            @ApiModelProperty(value = "订单ID ")
    private Long orderId;

            @ApiModelProperty(value = "创建人id")
    private Long createId;

            @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

            @ApiModelProperty(value = "内容")
    private String content;


        public static final String SHIPPING_ORDER_OPERATION_ID = "shipping_order_operation_id";

        public static final String SHIPPING_ORDER_ID = "shipping_order_id";

        public static final String ORDER_ID = "order_id";

        public static final String CREATE_ID = "create_id";

        public static final String CREATE_TIME = "create_time";

        public static final String CONTENT = "content";

}
