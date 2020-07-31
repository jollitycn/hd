package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色关联按钮表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role_button")
@ApiModel(value = "RoleButton对象", description = "角色关联按钮表")
public class RoleButton implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色按钮id")
    @TableId(value = "role_button_id", type = IdType.ID_WORKER)
    private Long roleButtonId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "按钮id")
    private Long buttonId;


    public static final String ROLE_BUTTON_ID = "role_button_id";

    public static final String ROLE_ID = "role_id";

    public static final String BUTTON_ID = "button_id";

}
