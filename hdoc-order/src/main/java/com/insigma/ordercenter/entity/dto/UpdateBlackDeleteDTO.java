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
@ApiModel("修改黑名单数据的删除状态入参实体")
public class UpdateBlackDeleteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "对象id", name = "id", required = true)
    @NotNull(message = "id不能为空")
    @Min(value = 1L, message = "id不合法")
    @Max(value = Long.MAX_VALUE, message = "id不合法")
    private Long id;

    @ApiModelProperty(value = "黑名单类型：1-账号黑名单 2-手机黑名单 3-区域黑名单", name = "blackType", required = true)
    @NotNull(message = "黑名单类型不能为空")
    @Min(value = 1, message = "黑名单类型不合法")
    @Max(value = 3, message = "黑名单类型不合法")
    private Integer blackType;
}
