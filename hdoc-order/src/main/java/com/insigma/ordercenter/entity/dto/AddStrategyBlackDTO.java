package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/29 17:24
 */
 @Data
 @ApiModel("保存按商品分类拆分策略的参数配置入参实体")
public class AddStrategyBlackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "策略id", name = "strategyId", required = true)
    @NotNull(message = "策略id不能为空")
    @Min(value = 1L, message = "策略id不合法")
    @Max(value = Long.MAX_VALUE, message = "策略id不合法")
    private Long strategyId;

    @ApiModelProperty(value = "店铺id", name = "shopId", required = true)
    @NotNull(message = "店铺id不能为空")
    @Min(value = 1L, message = "店铺id不合法")
    @Max(value = Long.MAX_VALUE, message = "店铺id不合法")
    private Long shopId;

    @ApiModelProperty(value = "帐号")
    private String account;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "黑名单类型：1-账号黑名单 2-手机黑名单 3-区域黑名单", name = "blackType", required = true)
    @NotNull(message = "黑名单类型不能为空")
    @Min(value = 1, message = "黑名单类型不合法")
    @Max(value = 3, message = "黑名单类型不合法")
    private Integer blackType;
}
