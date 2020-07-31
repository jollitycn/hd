package com.insigma.ordercenter.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/30 19:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("按钮信息VO")
public class SysButtonVO extends BaseVO {
    @ApiModelProperty(value = "按钮id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long buttonId;

    @ApiModelProperty(value = "菜单id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long menuId;

    @ApiModelProperty(value = "按钮名称")
    private String buttonName;

    @ApiModelProperty(value = "按钮接口")
    private String buttonUrl;

    @ApiModelProperty(value = "勾选状态：1---勾选 0---未勾选")
    private Integer checkFlag;
}
