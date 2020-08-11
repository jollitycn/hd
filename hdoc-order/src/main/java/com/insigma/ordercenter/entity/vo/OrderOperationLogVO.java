package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.insigma.ordercenter.constant.Constant;
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
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private Long createTime;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "创建人名称")
    private String userName;
}
