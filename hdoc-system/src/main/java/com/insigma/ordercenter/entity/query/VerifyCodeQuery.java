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
@ApiModel(value = "忘记密码发送短信验证实体")
@EqualsAndHashCode(callSuper = true)
public class VerifyCodeQuery extends BaseQuery {

//    @ApiModelProperty(value = "用户id")
//    private Long userId;

    @ApiModelProperty(value = "电话号码")
    @NotBlank(message = "电话号码不能为空")
    private String mobile;

    @ApiModelProperty(value = "图形验证码", name = "randImageValidateCode", required = true)
    @NotBlank(message = "图形验证码不能为空")
    private String randImageValidateCode;
}
