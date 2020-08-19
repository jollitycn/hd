package com.insigma.ordercenter.entity;

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
 * 系统订单操作日志表
 * </p>
 *
 * @author AH
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysOperationLog对象", description = "系统订单操作日志表")
public class SysOperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "系统操作日志表")
    @TableId(value = "operation_log_id", type = IdType.ID_WORKER)
    private Long operationLogId;

    @ApiModelProperty(value = "订单ID发货单ID")
    private Long orderId;

    @ApiModelProperty(value = "订单号发货单号")
    private String orderNo;

    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "内容")
    private String content;


    public static final String OPERATION_LOG_ID = "operation_log_id";

    public static final String ORDER_ID = "order_id";

    public static final String ORDER_NO = "order_no";

    public static final String CREATE_ID = "create_id";

    public static final String CREATE_TIME = "create_time";

    public static final String CONTENT = "content";

}
