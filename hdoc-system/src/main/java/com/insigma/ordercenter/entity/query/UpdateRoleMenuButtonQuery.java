package com.insigma.ordercenter.entity.query;

import com.insigma.ordercenter.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/31 9:19
 */
@Data
@ApiModel(value = "修改角色权限入参实体")
@EqualsAndHashCode(callSuper = true)
public class UpdateRoleMenuButtonQuery extends BaseQuery {

    @ApiModelProperty(value = "角色id", name = "roleId", required = true)
    @NotNull(message = "角色id不能为空")
    @Min(value = 1L, message = "角色id不合法")
    @Max(value = Long.MAX_VALUE, message = "角色id不合法")
    private Long roleId;

    @ApiModelProperty(value = "菜单id集合", name = "menuIdList")
    private List<Long> menuIdList;

    @ApiModelProperty(value = "按钮id集合", name = "buttonIdList")
    private List<Long> buttonIdList;
}
