package com.insigma.ordercenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AH
 * @program hdoc-parent
 * @description：订单操作日志
 * @date Create in 2020/7/27
 */
@Data
@ApiModel(value = "订单操作日志")
@EqualsAndHashCode(callSuper = true)
public class OrderOperationLogVO extends BaseVO{

    @ApiModelProperty(value = "订单操作日志表")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderOperationLogId;

    @ApiModelProperty(value = "订单ID ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @ApiModelProperty(value = "创建人id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "创建人名称")
    private String userName;
}