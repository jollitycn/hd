package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/5 11:19
 */
@Data
@ApiModel("更新赠品数量参数实体")
public class GiftNumDTO {

    @ApiModelProperty(value = "赠品id")
    @NotNull(message = "赠品id不能为空")
    private Long giftId;

    @ApiModelProperty(value = "赠品的数量")
    @NotNull(message = "赠品的数量不能为空")
    private Integer giftNum;
}
