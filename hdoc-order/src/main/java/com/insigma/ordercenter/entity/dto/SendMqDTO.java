package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/6 15:42
 */
 @Data
public class SendMqDTO {

    @ApiModelProperty("发送电话号码集合")
    private List<String> phoneList;

    @ApiModelProperty("发送内容")
    private String content;

    @ApiModelProperty("短信发送账号")
    private String account;

    @ApiModelProperty("短信发送密码")
    private String password;

}
