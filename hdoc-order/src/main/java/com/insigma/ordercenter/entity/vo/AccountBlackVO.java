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
 * @date 2020/7/29 13:38
 */
@Data
@ApiModel("账号黑名单返回实体VO")
@EqualsAndHashCode(callSuper = true)
public class AccountBlackVO extends BaseVO {
    @ApiModelProperty(value = "账户黑名单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountBlacklistStrategyId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "帐号")
    private String account;

    @ApiModelProperty(value = "原因")
    private String reason;
}
