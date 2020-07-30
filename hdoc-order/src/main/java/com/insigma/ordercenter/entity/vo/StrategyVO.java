package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/28 16:06
 */
@Data
@ApiModel("策略配置返回实体VO")
@EqualsAndHashCode(callSuper = true)
public class StrategyVO extends BaseVO {
    @ApiModelProperty(value = "策略ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long strategyId;

    @ApiModelProperty(value = "策略code")
    private String strategyCode;

    @ApiModelProperty(value = "策略类型（0：自动审单，1：订单策略，2：订单拦截）")
    private Integer strategyType;

    @ApiModelProperty(value = "策略名称")
    private String strategyName;

    @ApiModelProperty(value = "策略内容")
    private String strategyContect;

    @ApiModelProperty(value = "创建时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "自动审单时间（min）")
    private Integer autoAuditTime;

    @ApiModelProperty(value = "推送物流公司时间（min）")
    private Integer expressTime;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "参数配置标识：0---不可配置参数   1---可配置参数")
    private Integer paramFlag;
}
