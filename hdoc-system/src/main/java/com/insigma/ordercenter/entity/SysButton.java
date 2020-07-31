package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单按钮表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysButton对象", description = "菜单按钮表")
public class SysButton implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "按钮id")
    @TableId(value = "button_id", type = IdType.ID_WORKER)
    private Long buttonId;

    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    @ApiModelProperty(value = "按钮名称")
    private String buttonName;

    @ApiModelProperty(value = "按钮接口")
    private String buttonUrl;


    public static final String BUTTON_ID = "button_id";

    public static final String MENU_ID = "menu_id";

    public static final String BUTTON_NAME = "button_name";

    public static final String BUTTON_URL = "button_url";

}
