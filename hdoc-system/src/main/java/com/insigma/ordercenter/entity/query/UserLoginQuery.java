package com.insigma.ordercenter.entity.query;

import com.insigma.ordercenter.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2019/8/23 15:44
 */
@Data
@ApiModel(value = "用户登录接收信息实体")
@EqualsAndHashCode(callSuper = true)
public class UserLoginQuery extends BaseQuery {
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号", name = "userAccount", required = true)
    @NotBlank(message = "账号不能为空")
    private String userAccount;

    /**
     * 用户密码MD5加密之后的密码
     */
    @ApiModelProperty(value = "用户密码（MD5加密之后）", name = "password", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 用户密码MD5加密之后的密码
     */
    @ApiModelProperty(value = "图形验证码", name = "randImageValidateCode", required = true)
    @NotBlank(message = "图形验证码不能为空")
    private String randImageValidateCode;
}
