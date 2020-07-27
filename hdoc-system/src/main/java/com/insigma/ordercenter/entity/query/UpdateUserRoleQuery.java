package com.insigma.ordercenter.entity.query;

import com.insigma.ordercenter.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 16:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改用户关联角色入参实体")
public class UpdateUserRoleQuery extends BaseQuery {

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    @NotNull(message = "用户id不能为空")
    @Min(value = 1L, message = "用户id不合法")
    @Max(value = Long.MAX_VALUE, message = "用户id不合法")
    private Long userId;

    @ApiModelProperty(value = "角色id集合", name = "roleList", required = true)
    @NotNull(message = "角色id集合不能为空")
    @Size(message = "角色id集合有误")
    private List<Long> roleList;
}