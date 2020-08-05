package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/5 11:12
 */
 @Data
public class GiftListVO {

    @ApiModelProperty("赠品id")
    private Long giftId;

    @ApiModelProperty("赠品的数量")
    private String giftNum;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品编码")
    private String productNo;

    @ApiModelProperty("商品规格")
    private String productSpecs;

    @ApiModelProperty("单位数量")
    private Integer unitQuantity;
}
