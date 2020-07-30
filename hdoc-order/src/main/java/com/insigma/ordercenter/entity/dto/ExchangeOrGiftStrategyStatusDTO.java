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
 * @date 2020/7/29 16:58
 */
@Data
@ApiModel("启用or禁用拆单策略参数or赠品策略入参实体")
public class ExchangeOrGiftStrategyStatusDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "对象id", name = "id", required = true)
    @NotNull(message = "id不能为空")
    @Min(value = 1L, message = "id不合法")
    @Max(value = Long.MAX_VALUE, message = "id不合法")
    private Long id;

    @ApiModelProperty(value = "要修改数据的类型：1-拆单参数 2-赠品参数", name = "dataType", required = true)
    @NotNull(message = "数据类型不能为空")
    @Min(value = 1, message = "数据类型不合法")
    @Max(value = 2, message = "数据类型不合法")
    private Integer dataType;
}
