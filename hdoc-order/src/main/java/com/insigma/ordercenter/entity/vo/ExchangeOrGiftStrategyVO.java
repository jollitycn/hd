package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/29 16:19
 */
@Data
@ApiModel("换货or赠品策略信息返回实体VO")
@EqualsAndHashCode(callSuper = true)
public class ExchangeOrGiftStrategyVO extends BaseVO {
    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "主题")
    private String theme;

    @ApiModelProperty(value = "开始日期")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate startDate;

    @ApiModelProperty(value = "结束日期")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate endDate;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;
}
