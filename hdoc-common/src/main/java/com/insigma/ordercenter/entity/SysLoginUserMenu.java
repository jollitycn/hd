package com.insigma.ordercenter.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 返回前端菜单结构
 *
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/1/14 16:44
 */
@Data
@ApiModel("登录的菜单信息")
public class SysLoginUserMenu {
    @ApiModelProperty(value = "菜单ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单代码")
    private String menuCode;

    @ApiModelProperty(value = "菜单地址")
    private String menuUrl;

    @ApiModelProperty(value = "路由")
    private String route;

    @ApiModelProperty(value = "父菜单ID")
    private String parentId;

    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单等级")
    private Integer menuLevel;

    @ApiModelProperty(value = "下级菜单")
    private List<SysLoginUserMenu> subs;
}
