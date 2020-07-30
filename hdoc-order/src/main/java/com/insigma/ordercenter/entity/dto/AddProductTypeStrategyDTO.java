package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/29 17:24
 */
 @Data
 @ApiModel("保存按商品分类拆分策略的参数配置入参实体")
public class AddProductTypeStrategyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "策略id", name = "strategyId", required = true)
    @NotNull(message = "策略id不能为空")
    @Min(value = 1L, message = "策略id不合法")
    @Max(value = Long.MAX_VALUE, message = "策略id不合法")
    private Long strategyId;

    @ApiModelProperty(value = "商品分类id集合", name = "productTypeIdList", required = true)
    @NotNull(message = "商品分类id集合不能为空")
    @Size(message = "商品分类id集合有误")
    private List<Long> productTypeIdList;
}
