package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 收/发件人信息封装类
 * @author: XuChao
 * @create: 2020-08-05 16:30
 **/
@Data
public class ContactDTO {

    @ApiModelProperty(value = "收/发件人名称")
    private String receiveName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "地址")
    private String address;

}
