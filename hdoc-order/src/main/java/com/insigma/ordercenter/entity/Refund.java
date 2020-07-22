package com.insigma.ordercenter.entity;

    import java.math.BigDecimal;
    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import java.time.LocalDateTime;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 退换货记录表
    * </p>
*
* @author LiuHao
* @since 2020-07-22
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_refund")
    @ApiModel(value="Refund对象", description="退换货记录表")
    public class Refund implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "退换货记录ID")
            @TableId(value = "refund_id", type = IdType.ID_WORKER)
    private Long refundId;

            @ApiModelProperty(value = "订单ID ")
    private Long orderId;

            @ApiModelProperty(value = "退换货单号")
    private String refundNo;

            @ApiModelProperty(value = "类型（0：退货，1：换货）")
    private Integer refundType;

            @ApiModelProperty(value = "发起类型（0：客户发起，1：客服发起）")
    private Integer sourceType;

            @ApiModelProperty(value = "发起时间")
    private LocalDateTime createTime;

            @ApiModelProperty(value = "审核状态（0：未审核，1：通过，2：不通过）")
    private Integer auditStatus;

            @ApiModelProperty(value = "审核人ID")
    private Long auditId;

            @ApiModelProperty(value = "退换理由")
    private String reason;

            @ApiModelProperty(value = "审核日期")
    private LocalDateTime auditTime;

            @ApiModelProperty(value = "退换货状态（0：未完成，1：已完成）")
    private Integer status;

            @ApiModelProperty(value = "退款金额")
    private BigDecimal refundMoney;


        public static final String REFUND_ID = "refund_id";

        public static final String ORDER_ID = "order_id";

        public static final String REFUND_NO = "refund_no";

        public static final String REFUND_TYPE = "refund_type";

        public static final String SOURCE_TYPE = "source_type";

        public static final String CREATE_TIME = "create_time";

        public static final String AUDIT_STATUS = "audit_status";

        public static final String AUDIT_ID = "audit_id";

        public static final String REASON = "reason";

        public static final String AUDIT_TIME = "audit_time";

        public static final String STATUS = "status";

        public static final String REFUND_MONEY = "refund_money";

}
