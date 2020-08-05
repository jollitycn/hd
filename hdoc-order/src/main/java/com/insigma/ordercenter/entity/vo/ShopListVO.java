package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/4 10:36
 */

@Data
public class ShopListVO {

    @ApiModelProperty("店铺id")
    private String shopId;

    @ApiModelProperty("店铺名称")
    private String shopName;
}
