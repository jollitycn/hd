package com.insigma.ordercenter.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/3 16:59
 */
@Data
@ApiModel("编辑换货策略的参数实体")
public class UpdateExchangeStrategyDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "换货策略id", required = true)
    private Long exchangeStrategyId;

    @ApiModelProperty(value = "策略id", required = true)
    private Long strategyId;

    @ApiModelProperty(value = "换货主题", required = true)
    private String theme;

    @ApiModelProperty(value = "开始日期", required = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @ApiModelProperty(value = "结束日期", required = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    @ApiModelProperty(value = "原商品id", required = true)
    private Long oldProductId;

    @ApiModelProperty(value = "新商品id", required = true)
    private Long newProductId;

    @ApiModelProperty(value = "关联的店铺集合")
    private List<Long> shopIdList;
}
