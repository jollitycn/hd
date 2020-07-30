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
@ApiModel("修改策略数据入参实体")
public class UpdateTimeLengthDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "策略id", name = "strategyId", required = true)
    @NotNull(message = "策略id不能为空")
    @Min(value = 1L, message = "策略id不合法")
    @Max(value = Long.MAX_VALUE, message = "策略id不合法")
    private Long strategyId;

    @ApiModelProperty(value = "要修改的数据类型：1-自动审单时间（min） 2-推送物流公司时间（min） 3-优先级", name = "dataType", required = true)
    @NotNull(message = "数据类型不能为空")
    @Min(value = 1, message = "数据类型不合法")
    @Max(value = 3, message = "数据类型不合法")
    private Integer dataType;

    @ApiModelProperty(value = "修改的数据", name = "value", required = true)
    @NotNull(message = "修改的数据不能为空")
    @Min(value = 0, message = "修改的数据不合法")
    @Max(value = Integer.MAX_VALUE, message = "修改的数据不合法")
    private Integer value;
}
