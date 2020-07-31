package com.insigma.ordercenter.entity.vo;

import com.insigma.ordercenter.entity.SysLoginUserMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/30 19:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("角色菜单信息VO")
public class RoleMenuVO extends BaseVO {
    @ApiModelProperty(value = "菜单信息")
    private List<SysLoginUserMenu> menuList;
}
