package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/6 10:09
 */
 @Data
public class AddGiftStrDTO {

    @ApiModelProperty(value = "商品id", required = true)
    private Long productId;

    @ApiModelProperty(value = "赠品的数量", required = true)
    private Long giftNum;
}
