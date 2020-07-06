package com.insigma.ordercenter.entity.query;

import com.insigma.ordercenter.base.BaseQuery;
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
@ApiModel(value = "更新用户信息实体")
@EqualsAndHashCode(callSuper = true)
public class UpdateUserQuery extends BaseQuery {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "联系电话")
    private String mobilePhone;

    @ApiModelProperty(value = "选择角色")
    private List<Long> roleIds;
}
