package com.insigma.ordercenter.entity.query;

import com.insigma.ordercenter.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author ：Jason
 * @date ：Created in 2020/1/15
 */
@Data
@ApiModel(value = "用户创建信息实体")
@EqualsAndHashCode(callSuper = true)
public class CreateUserQuery extends BaseQuery {

    @ApiModelProperty(value = "用户帐号", name = "userAccount", required = true)
    @NotBlank(message = "用户帐号不能为空")
    @Pattern(regexp = "^.{1,20}$", message = "用户帐号不能超过20字符")
    private String userAccount;

    @ApiModelProperty(value = "用户密码", name = "userPassword", required = true)
    @NotBlank(message = "用户密码不能为空")
    private String userPassword;

    @ApiModelProperty(value = "用户名称", name = "userName", required = true)
    @NotBlank(message = "用户名称不能为空")
    private String userName;

    @ApiModelProperty(value = "联系电话", name = "mobilePhone", required = true)
    @NotBlank(message = "联系电话不能为空")
    private String mobilePhone;

    @ApiModelProperty(value = "选择角色", name = "roleIds", required = true)
    @NotNull(message = "角色不能为空")
    @Size(min = 1, message = "选择角色出错")
    private List<Long> roleIds;

    private Long userId;
}
