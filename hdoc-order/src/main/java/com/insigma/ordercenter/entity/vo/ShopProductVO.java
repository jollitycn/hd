package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 电商发货比例列表返回类
 * @author: XuChao
 * @create: 2020-07-28 15:55
 **/
@Data
public class ShopProductVO {

    @ApiModelProperty(value = "电商发货比例表主键id")
    private Long spid;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "分配比例")
    private Integer ratio;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "平台库存预警值（低于）")
    private Integer warningValue;

}
