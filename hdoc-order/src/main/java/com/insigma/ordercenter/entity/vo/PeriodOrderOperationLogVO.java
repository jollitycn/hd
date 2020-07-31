package com.insigma.ordercenter.entity.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jason
 * @description 预约订单操作日志列表信息
 * @date Create in 2020/7/9
 */
@Data
@ApiModel(value = "预约订单操作日志列表信息返回对象")
@EqualsAndHashCode(callSuper = true)
public class PeriodOrderOperationLogVO extends BaseVO{

    @ApiModelProperty(value = "预约订单操作日志表")
    @TableId(value = "period_order_operation_log_id", type = IdType.ID_WORKER)
    private Long periodOrderOperationLogId;

    @ApiModelProperty(value = "预约订单ID ")
    private Long periodOrderId;

    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "创建人名称")
    private String userName;

}
