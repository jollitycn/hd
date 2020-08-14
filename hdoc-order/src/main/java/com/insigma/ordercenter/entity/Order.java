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
 * 订单表
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
@ApiModel(value = "Order对象", description = "订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID ")
    @TableId(value = "order_id", type = IdType.ID_WORKER)
    private Long orderId;

    private Long batchNo;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单状态（0：新建状态，1：手动审核状态，2：待审核状态，3：审核异常状态，4：待出库状态，" +
            "  5：已出库状态，6：冻结状态，7：发货异常状态，8：已完成状态，9：取消状态，10：已退货状态）")
    private Integer orderStatus;

    @ApiModelProperty(value = "下单时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "收件人姓名")
    private String consumerName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "应收合计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "合单")
    private Integer isCombined;

    @ApiModelProperty(value = "拆单")
    private Integer splitOrder;

    @ApiModelProperty(value = "运费")
    private BigDecimal fee;

    @ApiModelProperty(value = "异常原因")
    private String errorReason;

    @ApiModelProperty(value = "是否是预约订单（0：否，1：是）")
    private Integer isPeriod;

    @ApiModelProperty(value = "预约订单ID")
    private Long periodOrderId;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime reviewTime;

    @ApiModelProperty("原始订单创建时间")
    public String orderTime;

    @ApiModelProperty(value = "是否是手动单（0：否，1：是）")
    private Integer isHandOrder;

    @ApiModelProperty(value = "原订单编号")
    private String originOrderNo;

    public static final String ORDER_ID = "order_id";

    public static final String ORDER_NO = "order_no";

    public static final String ORDER_STATUS = "order_status";

    public static final String CREATE_TIME = "create_time";

    public static final String SHOP_ID = "shop_id";

    public static final String CONSUMER_NAME = "consumer_name";

    public static final String MOBILE_PHONE = "mobile_phone";

    public static final String TOTAL_PRICE = "total_price";

    public static final String IS_COMBINED = "is_combined";

    public static final String FEE = "fee";

    public static final String IS_ERROR = "is_error";

    public static final String ERROR_REASON = "error_reason";

    public static final String IS_PERIOD = "is_period";

    public static final String PERIOD_ORDER_ID = "period_order_id";

    public static final String REVIEW_TIME = "review_time";

    public static final String IS_HAND_ORDER = "is_hand_order";

}
