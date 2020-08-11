package com.insigma.ordercenter.entity.vo;

import com.insigma.ordercenter.entity.SysButton;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysMenu对象", description="系统菜单")
public class SysMenuVO extends BaseVO {

    @ApiModelProperty(value = "菜单ID")
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

    @ApiModelProperty(value = "显示顺序")
    private Integer showOrder;

    @ApiModelProperty(value = "菜单描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    private List<SysButton> buttons;

    private List<SysMenuVO> menuVOList;

}
