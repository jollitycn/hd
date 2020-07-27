package com.insigma.ordercenter.entity.query;

import com.insigma.ordercenter.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author ：Jason
 * @date ：Created in 2020/2/18
 */
@Data
@ApiModel(value = "短信验证码验证实体")
@EqualsAndHashCode(callSuper = true)
public class VerifyMsgQuery extends BaseQuery {

    @ApiModelProperty(value = "电话号码")
    @NotBlank(message = "电话号码不能为空")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String msgCode;

}
