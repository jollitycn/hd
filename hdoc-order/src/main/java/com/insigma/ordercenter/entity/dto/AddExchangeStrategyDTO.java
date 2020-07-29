package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/29 19:19
 */
@Data
@ApiModel("保存换货策略的参数实体")
public class AddExchangeStrategyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "策略id", required = true)
    private Long strategyId;

    @ApiModelProperty(value = "换货主题", required = true)
    private String exchangeTheme;

    @ApiModelProperty(value = "开始日期", required = true)
    private LocalDate startDate;

    @ApiModelProperty(value = "结束日期", required = true)
    private LocalDate endDate;

    @ApiModelProperty(value = "原商品id", required = true)
    private Long oldProductId;

    @ApiModelProperty(value = "新商品id", required = true)
    private Long newProductId;

    @ApiModelProperty(value = "关联的店铺（使用英文逗号分隔）")
    private String shopIds;
}
