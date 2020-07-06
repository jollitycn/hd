package com.insigma.ordercenter.entity.vo.sysuservo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：liuhao
 * @date ：Created in 2020/1/17
 */
@Data
@ApiModel(value = "系统用户角色返回实体VO")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleListVO extends BaseVO {

    @ApiModelProperty(value = "角色ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

}
