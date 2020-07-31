package com.insigma.ordercenter.entity;

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
 * 预约订单
 * </p>
 *
 * @author AH
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_period_order")
@ApiModel(value = "PeriodOrder对象", description = "预约订单")
public class PeriodOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "预约订单ID")
    @TableId(value = "period_order_id", type = IdType.ID_WORKER)
    private Long periodOrderId;

    @ApiModelProperty(value = "订单编号")
    private String periodOrderNo;

    @ApiModelProperty(value = "订单类型（0：预售，1：月度，2：季度，年度）")
    private Integer periodOrderType;

    @ApiModelProperty(value = "订单状态（0：待审核，1：待出库，2：打单出库，3：已完成，4：冻结）")
    private Integer orderStatus;

    @ApiModelProperty(value = "下单时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "收件人姓名")
    private String consumerName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "完成次数")
    private Integer finishCount;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "发货规则")
    private String rule;

    @ApiModelProperty(value = "总次数")
    private Integer totalCount;

    @ApiModelProperty(value = "下次计划发货时间")
    private LocalDateTime nextSendTime;


    public static final String PERIOD_ORDER_ID = "period_order_id";

    public static final String PERIOD_ORDER_NO = "period_order_no";

    public static final String PERIOD_ORDER_TYPE = "period_order_type";

    public static final String ORDER_STATUS = "order_status";

    public static final String CREATE_TIME = "create_time";

    public static final String SHOP_ID = "shop_id";

    public static final String CONSUMER_NAME = "consumer_name";

    public static final String MOBILE_PHONE = "mobile_phone";

    public static final String FINISH_COUNT = "finish_count";

    public static final String IS_STOP = "is_stop";

    public static final String IS_DELETED = "is_deleted";

    public static final String RULE = "rule";

    public static final String TOTAL_COUNT = "total_count";

    public static final String NEXT_SEND_TIME = "next_send_time";

}
