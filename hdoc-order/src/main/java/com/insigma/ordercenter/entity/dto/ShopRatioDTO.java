package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 修改商品电商发货比例参数请求类
 * @author: XuChao
 * @create: 2020-07-28 16:13
 **/
@Data
public class ShopRatioDTO {

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "发货比例")
    private Integer ratio;

}
