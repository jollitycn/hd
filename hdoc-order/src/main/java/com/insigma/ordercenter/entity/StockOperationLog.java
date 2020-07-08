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
 * 库存记录操作日志表
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_stock_operation_log")
@ApiModel(value = "StockOperationLog对象", description = "库存记录操作日志表")
public class StockOperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "库存记录操作日志ID")
    @TableId(value = "stock_operation_log_id", type = IdType.ID_WORKER)
    private Long stockOperationLogId;

    @ApiModelProperty(value = "库存表ID")
    private Long warehouseProductRelationId;

    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "原数量")
    private Integer originQuantity;

    @ApiModelProperty(value = "变更后数量")
    private Integer destinationQuantity;

    @ApiModelProperty(value = "变更数量")
    private Integer changeQuantity;


}
