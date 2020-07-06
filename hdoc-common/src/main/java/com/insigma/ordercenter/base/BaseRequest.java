package com.insigma.ordercenter.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hd
 * @description: 请求基本参数封装类
 * @author: XuChao
 * @create: 2019-12-24 15:03
 **/
@ApiModel("请求基本参数封装类")
@Data
public class BaseRequest {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "页码编号",example="1")
    private Long pageNum=1L;

    @ApiModelProperty(value = "页码大小",example="10")
    private Long pageSize=10L;

    @ApiModelProperty(value = "搜索关键字")
    private String searchKey;

}
