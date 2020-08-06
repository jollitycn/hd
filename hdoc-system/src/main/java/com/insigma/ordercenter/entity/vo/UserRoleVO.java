package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/6 14:07
 */
 @Data
public class UserRoleVO {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
