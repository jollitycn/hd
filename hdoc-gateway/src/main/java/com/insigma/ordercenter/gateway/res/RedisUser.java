package com.insigma.ordercenter.gateway.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * Created by MOMO on 2019/1/7.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedisUser {

    @ApiModelProperty(value = "用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    @ApiModelProperty(value = "用户帐号")
    private String userAccount;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "联系电话")
    private String mobilePhone;

    @ApiModelProperty(value = "邮件地址")
    private String email;

    @ApiModelProperty(value = "是否停用（0：正常，1：停用）")
    private Integer isStopped;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "所有角色id集合")
    private List<Long> rolesId;

    @ApiModelProperty(value = "下级菜单")
    private List<SysLoginUserMenu> menus;

    private String token;
}
