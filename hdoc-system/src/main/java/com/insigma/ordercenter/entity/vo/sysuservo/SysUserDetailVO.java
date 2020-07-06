package com.insigma.ordercenter.entity.vo.sysuservo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ：liuhao
 * @date ：Created in 2020/1/17
 */
@Data
@ApiModel(value = "系统用户详情返回实体VO")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserDetailVO extends BaseVO {

    @ApiModelProperty(value = "用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    @ApiModelProperty(value = "用户帐号")
    private String userAccount;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "联系电话")
    private String mobilePhone;

    @ApiModelProperty(value = "角色类型")
    private List<SysRoleListVO> roles;
}
