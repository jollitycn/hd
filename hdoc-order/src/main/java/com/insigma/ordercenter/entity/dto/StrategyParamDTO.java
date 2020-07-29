package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/29 11:24
 */
@Data
@ApiModel("查询策略参数配置入参实体")
public class StrategyParamDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "策略id", name = "strategyId", required = true)
    @NotNull(message = "策略id不能为空")
    @Min(value = 1L, message = "策略id不合法")
    @Max(value = Long.MAX_VALUE, message = "策略id不合法")
    private Long strategyId;

    @ApiModelProperty(value = "店铺id", name = "shopId")
    private Long shopId;

    @ApiModelProperty(value = "搜索条件", name = "searchKey")
    private String searchKey;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", name = "pageNum", example = "1")
    protected Integer pageNum;

    /**
     * 每页大小
     */
    @ApiModelProperty(value = "每页大小", name = "pageSize", example = "10")
    protected Integer pageSize;
}
