package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/28 19:02
 */
@Data
@ApiModel("店铺关联策略信息")
@EqualsAndHashCode(callSuper = true)
public class ShopStrategyVO extends BaseVO {
    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "店铺策略状态：1：已启用，0：已停用")
    private String strategyStatus;
}
